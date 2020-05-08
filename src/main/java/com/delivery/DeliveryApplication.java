package com.delivery;

import com.delivery.model.Client;
import com.delivery.model.Crew;
import com.delivery.model.Employee;
import com.delivery.model.Vehicle;
import com.delivery.repository.CrewRepository;
import com.delivery.repository.EmployeeRepository;
import com.delivery.service.ClientService;
import com.delivery.service.CrewService;
import com.delivery.service.EmployeeService;
import com.delivery.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DeliveryApplication {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    CrewRepository crewRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    CrewService crewService;

    static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplication.class, args);

    }

    @PostConstruct
    public void method(){
//        crewService.delete(2);
//
////        client.setClientEmail("j_blaze@gmail.com");
////        clientService.create(client);
    }

}
