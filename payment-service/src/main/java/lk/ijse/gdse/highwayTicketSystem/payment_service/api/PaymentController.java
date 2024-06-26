package lk.ijse.gdse.highwayTicketSystem.payment_service.api;


import jakarta.validation.Valid;
import lk.ijse.gdse.highwayTicketSystem.payment_service.dto.PaymentDTO;
import lk.ijse.gdse.highwayTicketSystem.payment_service.dto.ResponseDTO;
import lk.ijse.gdse.highwayTicketSystem.payment_service.service.PaymentService;
import lk.ijse.gdse.highwayTicketSystem.payment_service.service.exception.NotFoundException;
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
@RequestMapping("/api/v1/payment")
public class PaymentController {

     @Autowired
     PaymentService paymentService;
    @Autowired
    ResponseDTO responseDTO;
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO createTicket(@RequestBody @Valid PaymentDTO paymentDTO) {
        System.out.println("hrllo");
        String userId = paymentDTO.getUserId();
        String ticketId = paymentDTO.getTicketId();
        String status = "Paid";
        if (userId != null) {
            try{
                ResponseDTO userDTO = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/findUser/" + userId, ResponseDTO.class);
                Object userDto = userDTO.getData();
                ResponseDTO ticketDTO = restTemplate.getForObject("http://TICKET-SERVICE/api/v1/ticket/findTicket/" + ticketId, ResponseDTO.class);
                Object ticketDTOData = ticketDTO.getData();
                paymentService.makePayment(paymentDTO,userDto,ticketDTOData);
                restTemplate.put("http://TICKET-SERVICE/api/v1/ticket/updateStatus/"+status+"/"+ticketId,ResponseDTO.class);
                return new ResponseDTO(HttpStatus.CREATED, "Payment  successfully Completed", paymentDTO);


            }catch (Exception e){
                throw new NotFoundException("Not found with ID: " + extractMessage(e.getLocalizedMessage()));

            }

        }
        return new ResponseDTO(HttpStatus.valueOf(404), "Payment Not successfully", paymentDTO);

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

}
