package lk.ijse.gdse.highwayTicketSystem.payment_service.service.exception;

public class InvalidCredentialException extends RuntimeException{
    public InvalidCredentialException(String message) {
        super(message);
    }
}
