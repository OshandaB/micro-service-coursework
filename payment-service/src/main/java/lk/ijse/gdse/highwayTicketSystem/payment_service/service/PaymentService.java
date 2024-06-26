package lk.ijse.gdse.highwayTicketSystem.payment_service.service;


import lk.ijse.gdse.highwayTicketSystem.payment_service.dto.PaymentDTO;


import java.util.List;

public interface PaymentService {

    public PaymentDTO makePayment(PaymentDTO paymentDTO, Object userDTO, Object ticketDTO);


}
