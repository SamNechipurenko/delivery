package com.delivery;

import com.delivery.model.Crew;
import com.delivery.model.Employee;
import com.delivery.model.Vehicle;
import com.delivery.service.CrewService;
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
public class CrewServiceTest {

    @MockBean
    CrewService crewService;

    @Test()
    void shouldReturnVCrew(){

        Employee employee = new Employee();
        List<Employee> employeeList= new ArrayList<>();


        employee.setEmployeeId(1);
        employee.setName("Alex");
        employee.setRole("porter");
        employee.setSalary(5);
        employeeList.add(employee);

        Crew crew = new Crew();


        crew.setCrewId(1);
        crew.setEmployeeList(employeeList);

        String predictedName = "Alex";

        Mockito.when(crewService.findCrewById(1)).thenReturn(crew);
        assertEquals(predictedName,
                crewService.findCrewById(1).getEmployeeList().get(0).getName());
    }


}
