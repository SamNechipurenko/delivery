package com.delivery.service;

import com.delivery.model.Employee;
import com.delivery.model.Vehicle;
import com.delivery.repository.EmployeeRepository;
import com.delivery.repository.VehicleRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public VehicleService(){
    }


    // поиск
    public Vehicle findVehicleById(int id){
        return vehicleRepository.findById(id).get();
    }

    // редактировать
    public Vehicle edit(int id, Vehicle vehicle){
        Vehicle newVehicle = findVehicleById(id);
        newVehicle.setVehicleId(vehicle.getVehicleId());
        newVehicle.setNumOfSeats(vehicle.getNumOfSeats());
        newVehicle.setBrand(vehicle.getBrand());
        newVehicle.setMidVelocity(vehicle.getMidVelocity());
        newVehicle.setEngaged(vehicle.isEngaged());
        vehicleRepository.save(vehicle);
        return vehicle;
    }


    // удаление
    public boolean delete(int id){
        Vehicle vehicle = findVehicleById(id);
        if (vehicle!=null) {
            vehicleRepository.delete(vehicle);
            return true;
        }
        return false;
    }

    // поиск авто с кол-вом седений больше numOfSeats
    public List<Vehicle> findVehiclesByNumOfSeatsGreaterThan(int numOfSeats){
        return vehicleRepository
                .findVehiclesByNumOfSeatsGreaterThanAndEngaged(numOfSeats-1, false);

    }

    // список авто с кол-вом седений numOfSeats
    public List<Vehicle> findVehicleByNumOfSeats(int numOfSeats){
        return vehicleRepository.findVehiclesByNumOfSeatsAndEngagedFalse(numOfSeats);
    }

    // создать новое авто
    public Vehicle create(@NotNull Vehicle vehicle){
        Vehicle newVehicle = new Vehicle();
        newVehicle.setNumOfSeats(vehicle.getNumOfSeats());
        newVehicle.setBrand(vehicle.getBrand());
        newVehicle.setMidVelocity(vehicle.getMidVelocity());
        newVehicle.setEngaged(false);
        vehicleRepository.save(newVehicle);
        return newVehicle;
    }



}
