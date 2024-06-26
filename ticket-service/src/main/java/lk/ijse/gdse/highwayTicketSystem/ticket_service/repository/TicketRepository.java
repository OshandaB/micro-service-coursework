package lk.ijse.gdse.highwayTicketSystem.ticket_service.repository;


import lk.ijse.gdse.highwayTicketSystem.ticket_service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
