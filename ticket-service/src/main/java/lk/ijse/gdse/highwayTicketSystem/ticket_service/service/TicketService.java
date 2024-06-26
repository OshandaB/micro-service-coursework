package lk.ijse.gdse.highwayTicketSystem.ticket_service.service;


import lk.ijse.gdse.highwayTicketSystem.ticket_service.dto.TicketDTO;

import java.util.List;

public interface TicketService {

    public List<TicketDTO> getAllTickets();
    public TicketDTO createTicket(TicketDTO ticketDTO, Object userDTO,Object vehicleDTO);
    public TicketDTO updateTicket(TicketDTO ticketDTO, Object userDTO,Object vehicleDTO);
    public boolean updateStatus(String id, String status);

    TicketDTO findUser(String id);
}
