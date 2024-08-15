package bg.mck.usercommandservice.application.exceptions;

public class ResetPasswordAlreadySendException extends RuntimeException{
    public ResetPasswordAlreadySendException() {
        super("The password reset request has been already send! Please check your email!");
    }
}
