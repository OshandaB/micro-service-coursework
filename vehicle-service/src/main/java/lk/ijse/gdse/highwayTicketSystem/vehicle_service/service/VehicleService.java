package lk.ijse.gdse.highwayTicketSystem.vehicle_service.service;

import lk.ijse.gdse.highwayTicketSystem.vehicle_service.api.UserDTO;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {

    public List<VehicleDTO> getAllVehicles();
    public  VehicleDTO registerVehicle(VehicleDTO vehicleDTO, Object userDTO);
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO,Object userDTO);

    VehicleDTO findUser(String id);
}
