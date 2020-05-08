package com.delivery.service;

import com.delivery.model.Crew;
import com.delivery.model.Employee;
import com.delivery.model.Vehicle;
import com.delivery.repository.CrewRepository;
import com.delivery.repository.EmployeeRepository;
import com.delivery.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrewService {

    private CrewRepository crewRepository;

    private EmployeeRepository employeeRepository;


    private VehicleRepository vehicleRepository;

    private EmployeeService employeeService;

    private VehicleService vehicleService;

// пример добавления новой команды тела запроса для
// {
//"roles":[ "porter", "driver"]
//}

    public CrewService(EmployeeRepository employeeRepository,
                       CrewRepository crewRepository, EmployeeService employeeService,
                       VehicleService vehicleService, VehicleRepository vehicleRepository) {
        this.employeeRepository = employeeRepository;
        this.vehicleService = vehicleService;
        this.crewRepository = crewRepository;
        this.employeeService = employeeService;
        this.vehicleRepository = vehicleRepository;
    }

    // поиск
    public Crew findCrewById(int id) {
        return crewRepository.findById(id).get();
    }

    // удаление
    public boolean delete(int id){
        Crew crew = findCrewById(id);
        if (crew!=null) {
            crewRepository.delete(crew);
            return true;
        }
        return false;
    }

    @Transactional
    public Crew createCrew(List<String> roles) throws RuntimeException {
        int numOfSeats = roles.size();

        Crew crew = new Crew();
        Vehicle vehicle;
        // Ищем транспорт
        if (vehicleRepository.findVehiclesByNumOfSeatsAndEngagedFalse(numOfSeats)!= null
                && vehicleRepository.findVehiclesByNumOfSeatsAndEngagedFalse(numOfSeats).size() != 0) {
            vehicle = vehicleService.findVehicleByNumOfSeats(numOfSeats).get(0);
        } else if (vehicleService.findVehiclesByNumOfSeatsGreaterThan(numOfSeats) != null
                && vehicleService.findVehiclesByNumOfSeatsGreaterThan(numOfSeats).size() !=0 ) {
            vehicle = vehicleService.findVehiclesByNumOfSeatsGreaterThan(numOfSeats).get(0);

        } else {
            throw new RuntimeException("All vehicles are busy");
        }

        vehicle.setEngaged(true);
        vehicle.setCrew(crew);
        //vehicleService.edit(vehicle.getVehicleId(), vehicle);
        vehicleRepository.save(vehicle);
        crew.setVehicle(vehicle);

        //ищем сотрудников
        List<Employee> employeeList = new ArrayList<>();
        for (String role : roles) {
            Employee emp = addEmployeeToCrew(crew,role);
            crew.addEmployee(emp);
            crewRepository.save(crew);
        }
        return crewRepository.findById(crew.getCrewId()).get();
    }

    // добавить сотрудника в команду
    private Employee addEmployeeToCrew(Crew crew, String role) {
        Employee employee = employeeRepository.findFirstByRoleAndEngagedFalse(role);
        if (employee == null) {
            throw new  RuntimeException("busy");
        }
        employee.setCrew(crew);
        employee.setEngaged(true);
        return employeeRepository.save(employee);
    }



}
