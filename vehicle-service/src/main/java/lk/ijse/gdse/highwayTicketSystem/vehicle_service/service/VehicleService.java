package lk.ijse.gdse.highwayTicketSystem.vehicle_service.service;

import lk.ijse.gdse.highwayTicketSystem.vehicle_service.dto.VehicleDTO;

import java.util.List;

public interface VehicleService {

    public List<VehicleDTO> getAllVehicles();
    public  VehicleDTO registerVehicle(VehicleDTO vehicleDTO);
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO);

}
