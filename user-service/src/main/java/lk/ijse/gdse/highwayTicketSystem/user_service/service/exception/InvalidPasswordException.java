package lk.ijse.gdse.highwayTicketSystem.user_service.service.exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(String message) {
        super(message);
    }
}
