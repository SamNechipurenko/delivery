package com.delivery.controller;

import com.delivery.exception.CostumedException;
import com.delivery.model.Vehicle;
import com.delivery.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    Vehicle vehicle;


    // поиск
    @GetMapping("/vehicle/{id}")
    public ResponseEntity<Vehicle> main(Model mav, @PathVariable String id){
        vehicle = vehicleService.findVehicleById(Integer.parseInt(id));
        if (vehicle!=null) {
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // удаление
    @DeleteMapping(path = "/vehicle/{id}")
    public ResponseEntity<Vehicle> delete(Model mav,
                                           @PathVariable String id){
        boolean deleted = vehicleService.delete(Integer.parseInt(id));
        if (deleted) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // изменение
    @PutMapping(path = "/vehicle/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> edit(Model mav,
                                        @PathVariable String id,
                                        @RequestBody Vehicle vehicle) throws CostumedException {
        if (vehicle!=null) {
            vehicle = vehicleService.edit(Integer.parseInt(id), vehicle);
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        }
        throw new CostumedException(" Vehicle not found ", 404);
    }



    // поиск
    @GetMapping("/vehicles")
    public ResponseEntity<List<Vehicle>> getVehiclesBySeats(Model mav,
                                                            @RequestParam("numOfSeats")
                                                                    int numOfSeats) throws CostumedException {
        List<Vehicle> vehicleList = vehicleService.findVehicleByNumOfSeats(numOfSeats);
        if (vehicleList.size()!=0) {
            return new ResponseEntity<>(vehicleList, HttpStatus.OK);
        }
        throw new CostumedException(" Vehicles not found ", 404);
    }


    // создать
    @PostMapping(path = "/vehicle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> addVehicle (Model mav,
                                               @RequestBody Vehicle vehicle) throws CostumedException {
        if (vehicle.getBrand() != null && vehicle.getMidVelocity() != 0
                && vehicle.getNumOfSeats() != 0) {
            vehicle = vehicleService.create(vehicle);
            return new ResponseEntity<>(vehicle, HttpStatus.OK);
        }
        throw new CostumedException(" bad request", 400);

    }
}
