package lk.ijse.gdse.highwayTicketSystem.ticket_service.service.exception;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String message) {
        super(message);
    }
}
