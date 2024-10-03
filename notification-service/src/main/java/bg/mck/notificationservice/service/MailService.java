package bg.mck.notificationservice.service;

import bg.mck.notificationservice.dto.ForgotPasswordEmailDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final ObjectMapper objectMapper;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${email.resetlink}")
    private String resetLink;

    public MailService(JavaMailSender mailSender, ObjectMapper objectMapper) {
        this.mailSender = mailSender;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String order) throws JsonProcessingException, MessagingException {
        JsonNode rootNode = objectMapper.readTree(order);
        String orderNumber = getNodeText(rootNode, "orderNumber");
        String email = getNodeText(rootNode, "email");
        String orderDate = formatDate(getNodeText(rootNode, "orderDate"));
        String deliveryDate = formatDate(getNodeText(rootNode, "deliveryDate"));
        String orderDescription = getNodeText(rootNode, "orderDescription");
        String orderStatus = getNodeText(rootNode, "orderStatus");
        String constructionSiteName = getNodeText(rootNode, "constructionSiteName");
        String constructionSiteNumber = getNodeText(rootNode, "constructionSiteNumber");
        String specificationFileUrl = getNodeText(rootNode, "specificationFileUrl");
        boolean isNewOrder = rootNode.path("newOrder").asBoolean();
        String fullName = getNodeText(rootNode, "fullName");
        JsonNode materialsNode = rootNode.path("materials");

        Set<String> columnNames = extractColumnNames(materialsNode);
        StringBuilder materialsHtml = buildMaterialsHtml(columnNames, materialsNode);

        String orderStatusText = isNewOrder ? "създадена" : "променена";
        String message = buildOrderMessage(orderStatusText, fullName, orderDescription, orderNumber, orderDate, deliveryDate,
                constructionSiteName, constructionSiteNumber, orderStatus, specificationFileUrl, materialsHtml);
        sendMail(email, "Вашата поръчка е " + orderStatusText, message);
        String modMessage = buildModOrderMessage(orderStatusText, fullName, orderDescription, orderNumber, orderDate, deliveryDate,
                constructionSiteName, constructionSiteNumber, orderStatus, specificationFileUrl, materialsHtml);
        sendMail("purchase@mck.bg", "Поръчка с номер: " + orderNumber + " е " + orderStatusText, modMessage);
    }

    private String getNodeText(JsonNode node, String fieldName) {
        return node.path(fieldName).asText("");
    }

    private String formatDate(String dateStr) {
        ZonedDateTime dateTime = ZonedDateTime.parse(dateStr);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    private Set<String> extractColumnNames(JsonNode materialsNode) {
        Set<String> columnNames = new HashSet<>();
        for (JsonNode materialNode : materialsNode) {
            Iterator<Map.Entry<String, JsonNode>> fields = materialNode.fields();
            while (fields.hasNext()) {
                columnNames.add(fields.next().getKey());
            }
        }
        return columnNames;
    }

    private StringBuilder buildMaterialsHtml(Set<String> columnNames, JsonNode materialsNode) {
        StringBuilder materialsHtml = new StringBuilder();
        materialsHtml.append("<tr>");
        for (String columnName : columnNames) {
            if (!"_id".equals(columnName)) {
                materialsHtml.append("<th style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white;\">")
                        .append(columnName)
                        .append("</th>");
            }
        }
        materialsHtml.append("</tr>");

        for (JsonNode materialNode : materialsNode) {
            materialsHtml.append("<tr>");
            for (String columnName : columnNames) {
                if (!"_id".equals(columnName)) {
                    String value = materialNode.path(columnName).asText("Няма");
                    materialsHtml.append("<td style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #f2f2f2;\">");
                    if ("specificationFileUrl".equals(columnName)) {
                        materialsHtml.append("<a href=\"").append(value).append(value.isBlank() || value.equals("null") ?"\">Няма файл.</a>" : "\">Изтегли</a>");
                    } else if("materialStatus".equals(columnName)){
                        materialsHtml.append(value.equals("NOT_APPROVED") ? "Не одобрен" : "Одобрен");
                    } else {
                        materialsHtml.append(value);
                    }
                    materialsHtml.append("</td>");
                }
            }
            materialsHtml.append("</tr>");
        }
        return materialsHtml;
    }

    private void sendMail(String email, String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

        messageHelper.setFrom(from);
        messageHelper.setTo(email);
        messageHelper.setSubject(subject);
        messageHelper.setText(message, true);

        mailSender.send(mimeMessage);
    }

    private static String buildOrderMessage(String orderStatusText, String fullName, String orderDescription, String orderNumber,
                                            String orderDate, String deliveryDate, String constructionSiteName,
                                            String constructionSiteNumber, String orderStatus, String specificationFileUrl,
                                            StringBuilder materialsHtml) {

        StringBuilder messageBuilder = new StringBuilder();

        switch (orderStatus) {
            case "CREATED" -> orderStatus = "Създадена";
            case "PENDING" -> orderStatus = "Изчакване";
            case "APPROVED" -> orderStatus = "Одобрена";
            case "DELIVERY_IN_PROGRESS" -> orderStatus = "В процес на доставяне";
            case "COMPLETED" -> orderStatus = "Завършена";
            case "CANCELLED" -> orderStatus = "Отхвърлена";
            case "UPDATED" -> orderStatus = "Променена";
        }

        messageBuilder.append("<!DOCTYPE html>")
                .append("<html lang=\"bg\">")
                .append("<head>")
                .append("    <meta charset=\"UTF-8\">")
                .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
                .append("    <title>Поръчка номер: ").append(orderNumber).append("</title>")
                .append("</head>")
                .append("<body style=\"font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4;\">")
                .append("    <div style=\"width: 100%; max-width: 800px; margin: 0 auto; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">")
                .append("        <div style=\"background-color: #007bff; color: white; padding: 15px; text-align: center; border-radius: 8px 8px 0 0;\">")
                .append("            <h1 style=\"margin: 0; font-size: 24px;\">Вашата поръчка е ").append(orderStatusText).append("</h1>")
                .append("        </div>")
                .append("        <div style=\"padding: 20px;\">")
                .append("            <p>С удоволствие ви информираме, че вашата поръчка е ").append(orderStatusText).append(". По-долу са подробностите за вашата поръчка:</p>")
                .append("            <table style=\"width: 100%; border-collapse: collapse; margin-bottom: 20px;\">")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Номер на поръчката:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(orderNumber).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Създател на поръчката:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(fullName).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Описание:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(orderDescription).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Дата на създаване:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(orderDate).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Дата на доставка:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(deliveryDate).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Име на строителния обект:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(constructionSiteName).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Номер на строителния обект:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(constructionSiteNumber).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Статус на поръчката:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(orderStatus).append("</td>")
                .append("                </tr>");

        if (specificationFileUrl != null && !specificationFileUrl.isEmpty()) {
            messageBuilder.append("                <tr>")
                    .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Файл със спецификации:</th>")
                    .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\"><a href=\"")
                    .append(specificationFileUrl)
                    .append("\" style=\"color: #007bff; text-decoration: none;\">Изтегли</a></td>")
                    .append("                </tr>");
        }

        messageBuilder.append("                <tr>")
                .append("                    <th colspan=\"2\" style=\"padding: 12px; text-align: center; border: 1px solid #ddd; background-color: #007bff; color: white;\">Материали</th>")
                .append("                </tr>")
                .append("            </table>")
                .append("            <div style=\"overflow-x: auto;\">")
                .append("                <table style=\"width: 100%; border-collapse: collapse; margin: 0 auto;\">")
                .append(materialsHtml)
                .append("                </table>")
                .append("            </div>")
                .append("            <p style=\"margin-top: 20px;\">Ако имате въпроси или се нуждаете от допълнителна помощ, не се колебайте да се свържете с нас.</p>")
                .append("            <p>Благодарим ви!</p>")
                .append("        </div>")
                .append("        <div style=\"background-color: #f8f9fa; padding: 10px; text-align: center; font-size: 12px; color: #777;\">")
                .append("            <p>").append(LocalDateTime.now().getYear()).append("</p>")
                .append("        </div>")
                .append("    </div>")
                .append("</body>")
                .append("</html>");

        return messageBuilder.toString();
    }

    public void sendResetPasswordMessage(ForgotPasswordEmailDTO dto) throws MessagingException {
        String subject = "Промяна на парола!";
        String content = buildResetPasswordMessage(dto);

        sendMail(dto.getEmail(), subject, content);
    }

    private String buildModOrderMessage(String orderStatusText, String fullName, String orderDescription, String orderNumber, String orderDate, String deliveryDate, String constructionSiteName, String constructionSiteNumber, String orderStatus, String specificationFileUrl, StringBuilder materialsHtml) {
        StringBuilder messageBuilder = new StringBuilder();

        switch (orderStatus) {
            case "CREATED" -> orderStatus = "Създадена";
            case "PENDING" -> orderStatus = "Изчакване";
            case "APPROVED" -> orderStatus = "Одобрена";
            case "DELIVERY_IN_PROGRESS" -> orderStatus = "В процес на доставяне";
            case "COMPLETED" -> orderStatus = "Завършена";
            case "CANCELLED" -> orderStatus = "Отхвърлена";
            case "UPDATED" -> orderStatus = "Променена";
        }

        messageBuilder.append("<!DOCTYPE html>")
                .append("<html lang=\"bg\">")
                .append("<head>")
                .append("    <meta charset=\"UTF-8\">")
                .append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">")
                .append("    <title>Поръчка номер: ").append(orderNumber).append("</title>")
                .append("</head>")
                .append("<body style=\"font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4;\">")
                .append("    <div style=\"width: 100%; max-width: 800px; margin: 0 auto; padding: 20px; background-color: #ffffff; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">")
                .append("        <div style=\"background-color: #007bff; color: white; padding: 15px; text-align: center; border-radius: 8px 8px 0 0;\">")
                .append("            <h1 style=\"margin: 0; font-size: 24px;\">Поръчка е ").append(orderStatusText).append("</h1>")
                .append("        </div>")
                .append("        <div style=\"padding: 20px;\">")
                .append("            <p>С удоволствие ви информираме, че поръчката е ").append(orderStatusText).append(". По-долу са подробностите за поръчката:</p>")
                .append("            <table style=\"width: 100%; border-collapse: collapse; margin-bottom: 20px;\">")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Номер на поръчката:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(orderNumber).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Създател на поръчката:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(fullName).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Описание:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(orderDescription).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Дата на създаване:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(orderDate).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Дата на доставка:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(deliveryDate).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Име на строителния обект:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(constructionSiteName).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Номер на строителния обект:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(constructionSiteNumber).append("</td>")
                .append("                </tr>")
                .append("                <tr>")
                .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Статус на поръчката:</th>")
                .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\">").append(orderStatus).append("</td>")
                .append("                </tr>");

        if (specificationFileUrl != null && !specificationFileUrl.isEmpty()) {
            messageBuilder.append("                <tr>")
                    .append("                    <th style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white; width: 30%;\">Файл със спецификации:</th>")
                    .append("                    <td style=\"padding: 12px; text-align: left; border: 1px solid #ddd; background-color: #f9f9f9; width: 70%; word-wrap: break-word;\"><a href=\"")
                    .append(specificationFileUrl)
                    .append("\" style=\"color: #007bff; text-decoration: none;\">Изтегли</a></td>")
                    .append("                </tr>");
        }

        messageBuilder.append("                <tr>")
                .append("                    <th colspan=\"2\" style=\"padding: 12px; text-align: center; border: 1px solid #ddd; background-color: #007bff; color: white;\">Материали</th>")
                .append("                </tr>")
                .append("            </table>")
                .append("            <div style=\"overflow-x: auto;\">")
                .append("                <table style=\"width: 100%; border-collapse: collapse; margin: 0 auto;\">")
                .append(materialsHtml)
                .append("                </table>")
                .append("            </div>")
                .append("        </div>")
                .append("        <div style=\"background-color: #f8f9fa; padding: 10px; text-align: center; font-size: 12px; color: #777;\">")
                .append("            <p>").append(LocalDateTime.now().getYear()).append("</p>")
                .append("        </div>")
                .append("    </div>")
                .append("</body>")
                .append("</html>");

        return messageBuilder.toString();
    }

    private String buildResetPasswordMessage(ForgotPasswordEmailDTO dto) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"bg\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Заявка за смяна на парола</title>\n" +
                "</head>\n" +
                "<body style=\"font-family: Arial, sans-serif; color: #333; background-color: #f4f4f4; margin: 0; padding: 0;\">\n" +
                "    <div style=\"width: 100%; max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n" +
                "        <div style=\"background-color: #007bff; color: #ffffff; padding: 20px; text-align: center;\">\n" +
                "            <h1 style=\"margin: 0; font-size: 24px;\">Искане за нулиране на парола</h1>\n" +
                "        </div>\n" +
                "        <div style=\"padding: 20px;\">\n" +
                "            <h2 style=\"font-size: 18px; margin-top: 0;\">Здравейте, " + dto.getEmail().split("@")[0] + "</h2>\n" +
                "            <p style=\"line-height: 1.6;\">Получихме заявка за промяна на вашата парола. Моля, кликнете върху линка по-долу, за да създадете нова парола:</p>\n" +
                "            <div style=\"margin-top: 20px; padding: 10px; background-color: #f9f9f9; border: 1px solid #ddd; border-radius: 5px;\">\n" +
                "                <p style=\"margin: 0; font-size: 16px;\">Вашият код за смяна на паролата е:</p>\n" +
                "                <br>\n" +
                "                <p style=\"margin: 0; font-size: 16px;\"><strong>" + dto.getUuid() + "</strong></p>\n" +
                "                <br>\n" +
                "                <p style=\"margin: 0; font-size: 16px;\">Моля, копирайте този код и го поставете в обозначеното поле на страницата за смяна на паролата.</p>\n" +
                "            </div>\n" +
                "            <a href=\"" + resetLink + "\" style=\"display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color: #007bff; text-decoration: none; border-radius: 5px; text-align: center; margin-top: 20px;\">Смяна на парола</a>\n" +
                "            <p style=\"line-height: 1.6;\">Ако не сте поискали тази промяна, моля, игнорирайте този имейл.</p>\n" +
                "            <p style=\"line-height: 1.6;\">С уважение,<br>Екипът на MCK</p>\n" +
                "        </div>\n" +
                "        <div style=\"background-color: #f4f4f4; text-align: center; padding: 10px; font-size: 14px;\">\n" +
                "            <p style=\"margin: 0;\">" + LocalDateTime.now().getYear() + "</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
