package lk.ijse.gdse.highwayTicketSystem.vehicle_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDTO {
    @Pattern(regexp = "^VEH-\\d{3}$", message = "ID must match the pattern USER-001")
    private String id;

    @NotBlank(message = "LicensePlate is mandatory")
    private String licensePlate;

    @NotBlank(message = "Model is mandatory")
    private String model;

    @NotBlank(message = "Capacity is mandatory")
    private Integer capacity;

    @NotBlank(message = "User is mandatory")
    private String userId;
}
