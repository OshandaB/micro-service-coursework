package lk.ijse.gdse.highwayTicketSystem.payment_service.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lk.ijse.gdse.highwayTicketSystem.payment_service.entity.Ticket;
import lk.ijse.gdse.highwayTicketSystem.payment_service.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {



    @Pattern(regexp = "^PAY-\\d{3}$", message = "ID must match the pattern USER-001")
    private String id;
    @NotNull(message = "Date is mandatory")
    private LocalDate paymentDate;
    @NotBlank(message = "PayMethod is mandatory")

    private String paymentMethod;
    @NotNull(message = "Amount is mandatory")

    private double amount;
    @NotBlank(message = "User is mandatory")
    private String userId;
    @NotBlank(message = "Ticket is mandatory")
    private String ticketId;
}
