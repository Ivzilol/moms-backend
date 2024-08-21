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

        String orderNumber = rootNode.path("orderNumber").asText();
        String email = rootNode.path("email").asText();
        String orderDate = rootNode.path("orderDate").asText();
        String deliveryDate = rootNode.path("deliveryDate").asText();
        String orderStatus = rootNode.path("orderStatus").asText();
        String constructionSiteName = rootNode.path("constructionSiteName").asText();
        String constructionSiteNumber = rootNode.path("constructionSiteNumber").asText();
        String specificationFileUrl = rootNode.path("specificationFileUrl").asText();
        boolean isNewOrder = rootNode.path("newOrder").asBoolean();
        JsonNode materialsNode = rootNode.path("materials");

        Set<String> columnNames = new HashSet<>();
        for (JsonNode materialNode : materialsNode) {
            Iterator<Map.Entry<String, JsonNode>> fields = materialNode.fields();
            while (fields.hasNext()) {
                columnNames.add(fields.next().getKey());
            }
        }

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
                    String value = materialNode.path(columnName).asText(null);
                    materialsHtml.append("<td style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #f2f2f2;\">");
                    if ("specificationFileUrl".equals(columnName)) {
                        materialsHtml.append("<a href=\"").append(value).append("\">Изтегли</a>");
                    } else {
                        materialsHtml.append(value != null ? value : "Няма");
                    }
                    materialsHtml.append("</td>");
                }
            }
            materialsHtml.append("</tr>");
        }

        String orderStatusText = isNewOrder ? "създадена" : "променена";

        String message = getMessage(orderStatusText, orderNumber, orderDate, deliveryDate, constructionSiteName, constructionSiteNumber, orderStatus, specificationFileUrl, materialsHtml);
        sendMail(email, "Вашата поръчка е " + orderStatusText, message);

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

    private static String getMessage(String orderStatusText, String orderNumber, String orderDate, String deliveryDate, String constructionSiteName, String constructionSiteNumber, String orderStatus, String specificationFileUrl, StringBuilder materialsHtml) {
        StringBuilder messageBuilder = new StringBuilder();
        ZonedDateTime timeOfCreation = ZonedDateTime.parse(orderDate);
        ZonedDateTime timeOfDelivery = ZonedDateTime.parse(deliveryDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        orderDate = timeOfCreation.format(formatter);
        deliveryDate = timeOfDelivery.format(formatter);

        messageBuilder.append("<!DOCTYPE html>")
                .append("<html>")
                .append("<body style=\"font-family: Arial, sans-serif; margin: 0; padding: 0;\">")
                .append("    <div style=\"background-color: #f8f9fa; padding: 20px; text-align: center;\">")
                .append("        <h1>Вашата поръчка е ").append(orderStatusText).append("</h1>")
                .append("    </div>")
                .append("    <div style=\"padding: 20px;\">")
                .append("        <p>С удоволствие ви информираме, че вашата поръчка е ").append(orderStatusText).append(". По-долу са подробностите за вашата поръчка:</p>")
                .append("        <table style=\"margin: 20px 0; width: 100%; border-collapse: collapse;\">")
                .append("            <tr>")
                .append("                <th style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white;\">Номер на поръчката:</th>")
                .append("                <td style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #f2f2f2;\">").append(orderNumber).append("</td>")
                .append("            </tr>")
                .append("            <tr>")
                .append("                <th style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white;\">Дата на поръчката:</th>")
                .append("                <td style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #f2f2f2;\">").append(orderDate).append("</td>")
                .append("            </tr>")
                .append("            <tr>")
                .append("                <th style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white;\">Дата на доставка:</th>")
                .append("                <td style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #f2f2f2;\">").append(deliveryDate).append("</td>")
                .append("            </tr>")
                .append("            <tr>")
                .append("                <th style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white;\">Име на строителния обект:</th>")
                .append("                <td style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #f2f2f2;\">").append(constructionSiteName).append("</td>")
                .append("            </tr>")
                .append("            <tr>")
                .append("                <th style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white;\">Номер на строителния обект:</th>")
                .append("                <td style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #f2f2f2;\">").append(constructionSiteNumber).append("</td>")
                .append("            </tr>")
                .append("            <tr>")
                .append("                <th style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white;\">Статус на поръчката:</th>")
                .append("                <td style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #f2f2f2;\">").append(orderStatus).append("</td>")
                .append("            </tr>");

        if (specificationFileUrl != null && !specificationFileUrl.equals("null") && !specificationFileUrl.isEmpty()) {
            messageBuilder.append("            <tr>")
                    .append("                <th style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white;\">Файл със спецификации:</th>")
                    .append("                <td style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #f2f2f2;\"><a href=\"")
                    .append(specificationFileUrl)
                    .append("\">Изтегли</a></td>")
                    .append("            </tr>");
        }

        messageBuilder.append("            <tr>")
                .append("                <th colspan=\"2\" style=\"padding: 10px; text-align: left; border: 1px solid #ddd; background-color: #007bff; color: white;\">Материали</th>")
                .append("            </tr>")
                .append(materialsHtml)
                .append("        </table>")
                .append("        <p>Ако имате въпроси или се нуждаете от допълнителна помощ, не се колебайте да се свържете с нас.</p>")
                .append("        <p>Благодарим ви!</p>")
                .append("    </div>")
                .append("    <div style=\"background-color: #f8f9fa; padding: 10px; text-align: center; font-size: 12px;\">")
                .append("        <p>&copy; ").append(LocalDateTime.now().getYear()).append(" MCK</p>")
                .append("    </div>")
                .append("</body>")
                .append("</html>");

        return messageBuilder.toString();
    }

    public void sendResetPasswordMessage(ForgotPasswordEmailDTO dto) throws MessagingException {
        String subject = "Промяна на парола!";
        String content = "<!DOCTYPE html>\n" +
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
                "            <h2 style=\"font-size: 18px; margin-top: 0;\">Здравейте, "+ dto.getEmail().split("@")[0] + "</h2>\n" +
                "            <p style=\"line-height: 1.6;\">Получихме заявка за промяна на вашата парола. Моля, кликнете върху линка по-долу, за да създадете нова парола:</p>\n" +
                "            <div style=\"margin-top: 20px; padding: 10px; background-color: #f9f9f9; border: 1px solid #ddd; border-radius: 5px;\">\n" +
                "                <p style=\"margin: 0; font-size: 16px;\">Вашият код за смяна на паролата е:</p>\n" +
                "                <br>\n" +
                "                <p style=\"margin: 0; font-size: 16px;\"><strong>"+ dto.getUuid() + "</strong></p>\n" +
                "                <br>\n" +
                "                <p style=\"margin: 0; font-size: 16px;\">Моля, копирайте този код и го поставете в обозначеното поле на страницата за смяна на паролата.</p>\n" +
                "            </div>\n" +
                "            <a href=\""+ resetLink  +"\" style=\"display: inline-block; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color: #007bff; text-decoration: none; border-radius: 5px; text-align: center; margin-top: 20px;\">Смяна на парола</a>\n" +
                "            <p style=\"line-height: 1.6;\">Ако не сте поискали тази промяна, моля, игнорирайте този имейл.</p>\n" +
                "            <p style=\"line-height: 1.6;\">С уважение,<br>Екипът на MCK</p>\n" +
                "        </div>\n" +
                "        <div style=\"background-color: #f4f4f4; text-align: center; padding: 10px; font-size: 14px;\">\n" +
                "            <p style=\"margin: 0;\">&copy; "+ LocalDateTime.now().getYear() +" MCK. Всички права запазени.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

        messageHelper.setFrom(from);
        messageHelper.setTo(dto.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);

        mailSender.send(mimeMessage);
    }
}