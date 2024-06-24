package lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.exception;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(String message) {
        super(message);
    }
}
