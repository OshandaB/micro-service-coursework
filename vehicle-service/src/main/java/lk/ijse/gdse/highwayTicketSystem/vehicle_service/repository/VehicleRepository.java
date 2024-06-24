package lk.ijse.gdse.highwayTicketSystem.vehicle_service.repository;


import lk.ijse.gdse.highwayTicketSystem.vehicle_service.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {
}
