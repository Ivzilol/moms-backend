package bg.mck.notificationservice.config.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration {

    @Autowired
    private MailProperties mailProperties;

    @Bean
    public JavaMailSender javaMailSender() {


        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(mailProperties.getHost());
        javaMailSender.setPort(mailProperties.getPort());
        javaMailSender.setUsername(mailProperties.getUsername());
        javaMailSender.setPassword(mailProperties.getPassword());
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setProtocol(mailProperties.getProtocol());

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "false"); // SSL/TLS
        properties.put("mail.smtp.ssl.enable", "true"); // SSL/TLS
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.port", "465");
        javaMailSender.setJavaMailProperties(properties);
        return new JavaMailSenderImpl();
    }
}
