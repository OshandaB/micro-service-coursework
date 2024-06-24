package lk.ijse.gdse.highwayTicketSystem.vehicle_service.advisor;

import lk.ijse.gdse.highwayTicketSystem.vehicle_service.dto.ResponseDTO;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.exception.DuplicateRecordException;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.exception.InvalidCredentialException;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.exception.InvalidPasswordException;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerClass {
    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseDTO duplicateErrorHandle(DuplicateRecordException e){
        return new ResponseDTO(HttpStatus.CONFLICT, e.getMessage(), null);
    }
    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDTO notFoundErrorHandle(NotFoundException e){
        return new ResponseDTO(HttpStatus.NOT_FOUND, e.getMessage(), null);
    }

    @ExceptionHandler({InvalidCredentialException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDTO invalidCredentialsErrorHandle(InvalidCredentialException e){
        return new ResponseDTO(HttpStatus.UNAUTHORIZED, e.getMessage(), null);
    }
    @ExceptionHandler({InvalidPasswordException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDTO invalidPasswordErrorHandle(InvalidPasswordException e){
        return new ResponseDTO(HttpStatus.NOT_FOUND, e.getMessage(), null);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleDataValidationException(MethodArgumentNotValidException methodArgumentNotValidException){

        ArrayList<Map<String,Object>> errorList = new ArrayList<>();

        for (FieldError fieldError : methodArgumentNotValidException.getFieldErrors()) {
            LinkedHashMap<String, Object> errorMap = new LinkedHashMap<>();
            errorMap.put("field",fieldError.getField());
            errorMap.put("message",fieldError.getDefaultMessage());
            errorMap.put("rejected",fieldError.getRejectedValue());
            errorList.add(errorMap);
        }
        return new ResponseDTO(HttpStatus.BAD_REQUEST, "Data Validation Failed!!!", errorList);


    }


}
