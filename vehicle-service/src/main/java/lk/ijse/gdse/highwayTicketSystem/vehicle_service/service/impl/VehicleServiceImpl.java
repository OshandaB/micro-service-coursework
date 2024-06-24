package lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.impl;




import lk.ijse.gdse.highwayTicketSystem.vehicle_service.dto.VehicleDTO;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.entity.Vehicle;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.repository.VehicleRepository;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.VehicleService;
import lk.ijse.gdse.highwayTicketSystem.vehicle_service.service.exception.DuplicateRecordException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
     @Autowired
     ModelMapper modelMapper;

    @Override
    public List<VehicleDTO> getAllVehicles() {
        List<Vehicle> all = vehicleRepository.findAll();
        return modelMapper.map(all,new TypeToken<List<VehicleDTO>>(){}.getType());
    }

    @Override
    public VehicleDTO registerVehicle(VehicleDTO vehicleDTO) {
        String vehicleDTOId = vehicleDTO.getId();
        if (vehicleDTOId != null && vehicleRepository.existsById(vehicleDTOId)) {
            throw new DuplicateRecordException("Vehicle with ID " + vehicleDTOId + " already exists.");
        }
        Vehicle vehicle = modelMapper.map(vehicleDTO, Vehicle.class);
        vehicleRepository.save(vehicle);
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    @Override
    public VehicleDTO updateVehicle(VehicleDTO vehicleDTO) {
        return null;
    }
}
