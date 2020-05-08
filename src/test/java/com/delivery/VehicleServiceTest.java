package com.delivery;

import com.delivery.model.Vehicle;
import com.delivery.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class VehicleServiceTest {

    @MockBean
    VehicleService vehicleService;

    @Test()
    void shouldReturnVehicle(){
        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleId(4);
        vehicle.setNumOfSeats(4);
        vehicle.setEngaged(false);
        vehicle.setMidVelocity(40);
        vehicle.setBrand("BMW");

        String predictedVehicleBrand = "BMW";

        Mockito.when(vehicleService.findVehicleById(4)).thenReturn(vehicle);
        assertEquals(predictedVehicleBrand, vehicleService.findVehicleById(4).getBrand());
    }

    @Test()
    void shouldReturnVehicleWithNumOfSeats(){
        Vehicle vehicle = new Vehicle();
        List<Vehicle> vehicleList = new ArrayList<>();

        vehicle.setVehicleId(4);
        vehicle.setNumOfSeats(4);
        vehicle.setEngaged(false);
        vehicle.setMidVelocity(40);
        vehicle.setBrand("BMW");
        vehicleList.add(vehicle);

        String predictedVehicleBrand = "BMW";

        Mockito.when(vehicleService.findVehicleByNumOfSeats(4)).thenReturn(vehicleList);
        assertEquals(predictedVehicleBrand, vehicleService
                .findVehicleByNumOfSeats(4).get(0).getBrand());
    }


}
