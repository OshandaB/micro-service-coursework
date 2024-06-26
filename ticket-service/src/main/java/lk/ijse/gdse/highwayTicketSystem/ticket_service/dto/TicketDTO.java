package lk.ijse.gdse.highwayTicketSystem.ticket_service.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.entity.User;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.entity.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    @Pattern(regexp = "^TIC-\\d{3}$", message = "ID must match the pattern USER-001")
    private String id;
    private LocalDate issueDate;
    private LocalTime issueTime;
    @NotBlank(message = "Location is mandatory")
    private String entryLocation;
    @NotBlank(message = "Location is mandatory")
    private String exitLocation;
    private String status;
    @NotNull(message = "Amount is mandatory")
    private double amount;
    @NotBlank(message = "User is mandatory")
    private String userId;
    @NotBlank(message = "Vehicle is mandatory")
    private String vehicleId;
}
