package lk.ijse.gdse.highwayTicketSystem.payment_service.repository;


import lk.ijse.gdse.highwayTicketSystem.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
