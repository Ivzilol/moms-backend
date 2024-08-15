package bg.mck.usercommandservice.application.exceptions;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException() {
        super("Email not found");
    }
}
