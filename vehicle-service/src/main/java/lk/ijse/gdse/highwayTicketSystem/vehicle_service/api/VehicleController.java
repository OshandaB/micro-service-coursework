package lk.ijse.gdse.highwayTicketSystem.vehicle_service.api;


import jakarta.validation.Valid;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.dto.ResponseDTO;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.dto.VehicleDTO;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/vehicle")
public class VehicleController {

     @Autowired
     VehicleService vehicleService;
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO registerVehicle(@RequestBody @Valid VehicleDTO vehicleDTO) {
        String userId = vehicleDTO.getUserId();

        vehicleService.registerVehicle(vehicleDTO);
        return new ResponseDTO(HttpStatus.CREATED, "Vehicle registered successfully", vehicleDTO);
    }

}
