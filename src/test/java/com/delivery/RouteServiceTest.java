package com.delivery;

import com.delivery.model.Crew;
import com.delivery.model.Employee;
import com.delivery.model.Route;
import com.delivery.service.CrewService;
import com.delivery.service.RouteService;
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
public class RouteServiceTest {

    @MockBean
    RouteService routeService;

    @Test()
    void shouldReturnRoute(){

        Employee employee = new Employee();
        List<Employee> employeeList= new ArrayList<>();


        employee.setEmployeeId(1);
        employee.setName("Alex");
        employee.setRole("porter");
        employee.setSalary(5);
        employee.setEngaged(true);
        employeeList.add(employee);

        Crew crew = new Crew();
        crew.setCrewId(1);
        crew.setEmployeeList(employeeList);

        Route route = new Route();
        route.setRouteId(2);
        route.setCrew(crew);
        route.setLength(10);
        route.setPrice(5);

        boolean flag = true;

        Mockito.when(routeService.findRouteById(2)).thenReturn(route);
        assertEquals(flag, routeService.findRouteById(2).getCrew().getEmployeeList().get(0).isEngaged());
    }

}
