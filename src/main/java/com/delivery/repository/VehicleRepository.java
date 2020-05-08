package com.delivery.repository;

import com.delivery.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    List<Vehicle> findVehiclesByNumOfSeatsAndEngagedFalse(int numOfSeats);
    List<Vehicle> findVehiclesByNumOfSeatsGreaterThanAndEngaged(int numOfSeats, boolean engaged);

}