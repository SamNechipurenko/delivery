package com.delivery;

import com.delivery.model.Employee;
import com.delivery.model.Vehicle;
import com.delivery.service.EmployeeService;
import com.delivery.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EmployeeServiceTest {

    @MockBean
    EmployeeService employeeService;

    @Test()
    void shouldReturnEmployee(){
        Employee employee = new Employee();

        employee.setEmployeeId(1);
        employee.setName("Alex");
        employee.setRole("porter");
        employee.setSalary(5);


        String predictedVehicleName = "Alex";

        Mockito.when(employeeService.findEmployeeById(1)).thenReturn(employee);
        assertEquals(predictedVehicleName,employeeService.findEmployeeById(1).getName());
    }

    @Test()
    void shouldReturnEmployeeWithNumOfSeats(){
        Employee employee = new Employee();
        List<Employee> employeeList= new ArrayList<>();


        employee.setEmployeeId(1);
        employee.setName("Alex");
        employee.setRole("porter");
        employee.setSalary(5);
        employeeList.add(employee);

        String predictedEmployeeName = "Alex";

        Mockito.when(employeeService.findEmployeesByRole("porter")).thenReturn(employeeList);
        assertEquals(predictedEmployeeName, employeeService.findEmployeesByRole("porter").get(0).getName());
    }

}
