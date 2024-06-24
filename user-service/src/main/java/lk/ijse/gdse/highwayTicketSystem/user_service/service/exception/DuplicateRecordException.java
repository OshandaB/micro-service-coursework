package lk.ijse.gdse.highwayTicketSystem.user_service.service.exception;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String message) {
        super(message);
    }
}
