package lk.ijse.gdse.highwayTicketSystem.ticket_service.service.impl;




import lk.ijse.gdse.highwayTicketSystem.ticket_service.dto.TicketDTO;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.entity.Ticket;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.entity.User;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.entity.Vehicle;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.repository.TicketRepository;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.service.TicketService;

import lk.ijse.gdse.highwayTicketSystem.ticket_service.service.exception.DuplicateRecordException;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    @Autowired
    TicketRepository ticketRepository;
     @Autowired
     ModelMapper modelMapper;



    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> all = ticketRepository.findAll();
        return modelMapper.map(all,new TypeToken<List<TicketDTO>>(){}.getType());
    }

    @Override
    public TicketDTO createTicket(TicketDTO ticketDTO, Object userDTO, Object vehicleDTO) {
        String ticketDTOId = ticketDTO.getId();
        if (ticketDTOId != null && ticketRepository.existsById(ticketDTOId)) {
            throw new DuplicateRecordException("Ticket with ID " + ticketDTOId + " already exists.");
        }
        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
        User user = modelMapper.map(userDTO, User.class);
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        ticket.setUser(user);
        ticket.setVehicle(vehicle);
        ticketRepository.save(ticket);
        return modelMapper.map(ticket, TicketDTO.class);
    }

    @Override
    public TicketDTO updateTicket(TicketDTO ticketDTO, Object userDTO, Object vehicleDTO) {
        String ticketDTOId = ticketDTO.getId();
        if (ticketDTOId != null && !ticketRepository.existsById(ticketDTOId)) {
            throw new NotFoundException("Ticket with ID " + ticketDTOId + " does not exist.");
        }
        Ticket ticket = modelMapper.map(ticketDTO, Ticket.class);
        User user = modelMapper.map(userDTO, User.class);
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        ticket.setUser(user);
        ticket.setVehicle(vehicle);
        ticketRepository.save(ticket);
        return modelMapper.map(ticket, TicketDTO.class);
    }

    @Override
    public boolean updateStatus(String id, String status) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) {
            throw new NotFoundException("Ticket not found with ID: " + id);
        }
        Ticket ticket1 = ticket.get();
        ticket1.setStatus(status);
        Ticket saved = ticketRepository.save(ticket1);
        return true;
    }

    @Override
    public TicketDTO findUser(String id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isEmpty()) {
            throw new NotFoundException("Ticket not found with ID: " + id);
        }
        return modelMapper.map(ticket.get(), TicketDTO.class);
    }
}
