package bg.mck.notificationservice.service;

import bg.mck.notificationservice.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MailService {

    private final JavaMailSender mailSender;
    private final ObjectMapper objectMapper;

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
        boolean isNewOrder = rootNode.path("isNewOrder").asBoolean();
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
                materialsHtml.append("<th>").append(columnName).append("</th>");
            }
        }
        materialsHtml.append("</tr>");

        for (JsonNode materialNode : materialsNode) {
            materialsHtml.append("<tr>");
            for (String columnName : columnNames) {
                if (!"_id".equals(columnName)) {
                    materialsHtml.append("<td>")
                            .append(materialNode.path(columnName).asText())
                            .append("</td>");
                }
            }
            materialsHtml.append("</tr>");
        }

        String orderStatusText = isNewOrder ? "създадена" : "променена";

        String message = getMessage(orderStatusText, orderNumber, orderDate, deliveryDate, constructionSiteName, constructionSiteNumber, orderStatus, specificationFileUrl, materialsHtml);
        sendMail(email, "Вашата поръчка е" + orderStatusText, message);

    }

    private void sendMail(String email,String subject, String message) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");

        messageHelper.setTo(email);
        messageHelper.setSubject(subject);
        messageHelper.setText(message, true);

        mailSender.send(mimeMessage);
    }

    private static String getMessage(String orderStatusText, String orderNumber, String orderDate, String deliveryDate, String constructionSiteName, String constructionSiteNumber, String orderStatus, String specificationFileUrl, StringBuilder materialsHtml) {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <style>" +
                "        .email-content { font-family: Arial, sans-serif; }" +
                "        .email-header { background-color: #f8f9fa; padding: 20px; text-align: center; }" +
                "        .email-body { padding: 20px; }" +
                "        .email-footer { background-color: #f8f9fa; padding: 10px; text-align: center; font-size: 12px; }" +
                "        .order-details { margin: 20px 0; width: 100%; border-collapse: collapse; }" +
                "        .order-details th, .order-details td { padding: 10px; text-align: left; border: 1px solid #ddd; }" +
                "        .order-details th { background-color: #007bff; color: white; }" +
                "        .order-details td { background-color: #f2f2f2; }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class=\"email-content\">" +
                "        <div class=\"email-header\">" +
                "            <h1>Вашата поръчка е " + orderStatusText + "</h1>" +
                "        </div>" +
                "        <div class=\"email-body\">" +
                "            <p>С удоволствие ви информираме, че вашата поръчка е " + orderStatusText + ". По-долу са подробностите за вашата поръчка:</p>" +
                "            <table class=\"order-details\">" +
                "                <tr><th>Номер на поръчката:</th><td>" + orderNumber + "</td></tr>" +
                "                <tr><th>Дата на поръчката:</th><td>" + orderDate + "</td></tr>" +
                "                <tr><th>Дата на доставка:</th><td>" + deliveryDate + "</td></tr>" +
                "                <tr><th>Име на строителния обект:</th><td>" + constructionSiteName + "</td></tr>" +
                "                <tr><th>Номер на строителния обект:</th><td>" + constructionSiteNumber + "</td></tr>" +
                "                <tr><th>Статус на поръчката:</th><td>" + orderStatus + "</td></tr>" +
                "                <tr><th>Файл със спецификации:</th><td><a href=\"" + specificationFileUrl + "\">Изтегли</a></td></tr>" +
                "                <tr><th colspan=\"2\">Материали</th></tr>" +
                materialsHtml +
                "            </table>" +
                "            <p>Ако имате въпроси или се нуждаете от допълнителна помощ, не се колебайте да се свържете с нас.</p>" +
                "            <p>Благодарим ви!</p>" +
                "        </div>" +
                "        <div class=\"email-footer\">" +
                "            <p>&copy; 2024 MCK</p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
    }
}
