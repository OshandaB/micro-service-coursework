package lk.ijse.gdse.highwayTicketSystem.vehicle_service.api;


import jakarta.validation.Valid;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.dto.ResponseDTO;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.dto.VehicleDTO;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.VehicleService;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

     @Autowired
     VehicleService vehicleService;
    @Autowired
    ResponseDTO responseDTO;
    @Autowired
    private RestTemplate restTemplate;
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO registerVehicle(@RequestBody @Valid  VehicleDTO vehicleDTO) {
        System.out.println("hrllo");
        String userId = vehicleDTO.getUserId();
        if (userId != null) {
            try{
                ResponseDTO responseDTO = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/findUser/" + userId, ResponseDTO.class);
                Object userDto = responseDTO.getData();
                System.out.println(userDto);
                vehicleService.registerVehicle(vehicleDTO,userDto);
                return new ResponseDTO(HttpStatus.CREATED, "Vehicle registered successfully", vehicleDTO);


            }catch (Exception e){
                throw new NotFoundException("User not found with ID: " + e.getLocalizedMessage());

            }

        }
        return new ResponseDTO(HttpStatus.valueOf(404), "Vehicle registered Not successfully", vehicleDTO);

//        vehicleService.registerVehicle(vehicleDTO);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO updateVehicle(@RequestBody @Valid  VehicleDTO vehicleDTO) {
        String userId = vehicleDTO.getUserId();
        if (userId != null) {
            ResponseDTO responseDTO = restTemplate.getForObject("http://USER-SERVICE/api/v1/user/findUser/" + userId, ResponseDTO.class);
            Object userDto = responseDTO.getData();
            System.out.println(userDto);
            VehicleDTO updateVehicle = vehicleService.updateVehicle(vehicleDTO, userDto);
            return new ResponseDTO(HttpStatus.CREATED, "Vehicle updated successfully", updateVehicle);

        }
        return new ResponseDTO(HttpStatus.valueOf(404), "Vehicle updated Not successfully", vehicleDTO);

//        vehicleService.registerVehicle(vehicleDTO);
    }


    @GetMapping(value = "/getAllVehicles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> getAllVehicles() {

        try {
            List<VehicleDTO> allVehicles = vehicleService.getAllVehicles();
            if (allVehicles == null) {
                responseDTO.setCode(HttpStatus.BAD_GATEWAY);
                responseDTO.setMessage("No Data");
                responseDTO.setData(null);
                return new ResponseEntity<>(responseDTO, HttpStatus.BAD_GATEWAY);

            }
            responseDTO.setCode(HttpStatus.OK);
            responseDTO.setMessage("Success");
            responseDTO.setData(allVehicles);
            return new ResponseEntity<>(responseDTO, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            responseDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR);
            responseDTO.setMessage(e.getMessage());
            responseDTO.setData(e);
            return new ResponseEntity<>(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/findUser/{id}")
    public ResponseDTO findUser(@PathVariable String id) {
        VehicleDTO vehicleDTO = vehicleService.findUser(id);
        return new ResponseDTO(HttpStatus.valueOf(200), "User found successfully", vehicleDTO);
    }

}
