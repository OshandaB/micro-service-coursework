package lk.ijse.gdse.highwayTicketSystem.ticket_service.api;


import jakarta.validation.Valid;

import lk.ijse.gdse.highwayTicketSystem.ticket_service.dto.ResponseDTO;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.dto.TicketDTO;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.service.TicketService;
import lk.ijse.gdse.highwayTicketSystem.ticket_service.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/ticket")
public class TicketController {

     @Autowired
     TicketService ticketService;
    @Autowired
    ResponseDTO responseDTO;
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createTicket(@RequestBody @Valid TicketDTO ticketDTO) {
        System.out.println("hrllo");
        String userId = ticketDTO.getUserId();
        String vehicleId = ticketDTO.getVehicleId();
        if (userId != null) {
            try{
                ResponseDTO userDTO = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/findUser/" + userId, ResponseDTO.class);
                Object userDto = userDTO.getData();
                ResponseDTO VehicleDTO = restTemplate.getForObject("http://VEHICLE-SERVICE/api/v1/vehicle/findUser/" + vehicleId, ResponseDTO.class);
                Object vehicleDTOData = VehicleDTO.getData();
                ticketService.createTicket(ticketDTO,userDto,vehicleDTOData);
                return new ResponseDTO(HttpStatus.CREATED, "Vehicle registered successfully", ticketDTO);


            }catch (Exception e){
                throw new NotFoundException("Not found with ID: " + extractMessage(e.getLocalizedMessage()));

            }

        }
        return new ResponseDTO(HttpStatus.valueOf(404), "Vehicle registered Not successfully", ticketDTO);

//        vehicleService.registerVehicle(vehicleDTO);
    }
    private String extractMessage(String localizedMessage) {
        // Example input: 404 : "{\"code\":\"NOT_FOUND\",\"message\":\"Vehicle not found with ID: VEH-005\",\"data\":null}"
        String regex = "\"message\":\"([^\"]+)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(localizedMessage);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return localizedMessage;
    }
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO updateTicket(@RequestBody @Valid  TicketDTO ticketDTO) {
        String userId = ticketDTO.getUserId();
        String vehicleId = ticketDTO.getVehicleId();
        if (userId != null) {
            try{
                ResponseDTO userDTO = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/findUser/" + userId, ResponseDTO.class);
                Object userDto = userDTO.getData();
                ResponseDTO VehicleDTO = restTemplate.getForObject("http://VEHICLE-SERVICE/api/v1/vehicle/findUser/" + vehicleId, ResponseDTO.class);
                Object vehicleDTOData = VehicleDTO.getData();
                ticketService.createTicket(ticketDTO,userDto,vehicleDTOData);
                return new ResponseDTO(HttpStatus.CREATED, "Ticket updated successfully", ticketDTO);


            }catch (Exception e){
                throw new NotFoundException("Not found with ID: " + e.getMessage());

            }

        }
        return new ResponseDTO(HttpStatus.valueOf(404), "Ticket update Not successfully", ticketDTO);

//        vehicleService.registerVehicle(vehicleDTO);
    }


    @GetMapping(value = "/getAllTickets", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllTickets() {

        try {
            List<TicketDTO> allTickets = ticketService.getAllTickets();
            if (allTickets == null) {
                responseDTO.setCode(HttpStatus.BAD_GATEWAY);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(HttpStatus.OK);
            responseDTO.setMessage("Success");
            responseDTO.setData(allTickets);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @PutMapping(value = "/updateStatus/{status}/{id}")
   public ResponseDTO updateStatus(@PathVariable String id, @PathVariable String status) {
       boolean updated = ticketService.updateStatus(id, status);
       if (updated){
           return new ResponseDTO(HttpStatus.CREATED, "Status updated successfully",null);
       }
       return new ResponseDTO(HttpStatus.valueOf(500), "Status updated Not successfully",null);
   }

    @GetMapping(value = "/findTicket/{id}")
    public ResponseDTO findTicker(@PathVariable String id) {
        TicketDTO ticketDTO = ticketService.findUser(id);
        return new ResponseDTO(HttpStatus.valueOf(200), "User found successfully", ticketDTO);
    }
}
