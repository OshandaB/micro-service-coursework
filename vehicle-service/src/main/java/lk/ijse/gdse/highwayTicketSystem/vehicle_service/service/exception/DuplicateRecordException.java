package lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.exception;

public class DuplicateRecordException extends RuntimeException{
    public DuplicateRecordException(String message) {
        super(message);
    }
}
