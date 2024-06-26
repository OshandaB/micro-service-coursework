package lk.ijse.gdse.highwayTicketSystem.payment_service.service.impl;


import lk.ijse.gdse.highwayTicketSystem.payment_service.dto.PaymentDTO;
import lk.ijse.gdse.highwayTicketSystem.payment_service.entity.Payment;
import lk.ijse.gdse.highwayTicketSystem.payment_service.entity.Ticket;
import lk.ijse.gdse.highwayTicketSystem.payment_service.entity.User;
import lk.ijse.gdse.highwayTicketSystem.payment_service.repository.PaymentRepository;
import lk.ijse.gdse.highwayTicketSystem.payment_service.service.PaymentService;

import lk.ijse.gdse.highwayTicketSystem.payment_service.service.exception.DuplicateRecordException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;
     @Autowired
     ModelMapper modelMapper;




    @Override
    public PaymentDTO makePayment(PaymentDTO paymentDTO, Object userDTO, Object ticketDTO) {
        String paymentDTOId = paymentDTO.getId();
        if (paymentDTOId != null && paymentRepository.existsById(paymentDTOId)) {
            throw new DuplicateRecordException("Ticket with ID " + paymentDTOId + " already exists.");
        }
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        User user = modelMapper.map(userDTO, User.class);
        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
        payment.setUser(user);
        payment.setTicket(ticket);
        paymentRepository.save(payment);
        return modelMapper.map(payment, PaymentDTO.class);
    }
}
