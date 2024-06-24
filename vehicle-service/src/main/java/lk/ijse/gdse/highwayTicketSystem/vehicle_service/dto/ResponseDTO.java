package lk.ijse.gdse.highwayTicketSystem.vehicle_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
@Builder
public class ResponseDTO {
    private HttpStatus code;
    private String message;
    private Object data;
}
