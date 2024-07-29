package bg.mck.notificationservice.config.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private String protocol;
    private Properties properties = new Properties();

    public String getHost() {
        return host;
    }

    public MailProperties setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public MailProperties setPort(int port) {
        this.port = port;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public MailProperties setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MailProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getProtocol() {
        return protocol;
    }

    public MailProperties setProtocol(String protocol) {
        this.protocol = protocol;
        return this;
    }

    public Properties getProperties() {
        return properties;
    }

    public MailProperties setProperties(Properties properties) {
        this.properties = properties;
        return this;
    }
}
