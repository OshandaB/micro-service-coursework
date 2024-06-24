package lk.ijse.gdse.highwayTicketSystem.vehicle_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    @Id
    private String id;
    @Column(name="license_plate",unique = true)
    private String licensePlate;
    private String model;
    private Integer capacity;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
