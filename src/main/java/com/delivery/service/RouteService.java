package com.delivery.service;

import com.delivery.model.Client;
import com.delivery.model.Crew;
import com.delivery.model.Employee;
import com.delivery.model.Route;
import com.delivery.repository.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RouteService {


    RouteRepository routeRepository;
    ClientRepository clientRepository;
    CrewRepository crewRepository;

    public RouteService(RouteRepository routeRepository, ClientRepository clientRepository,
            CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
        this.clientRepository = clientRepository;
        this.routeRepository = routeRepository;
    }

    // поиск
    public Route findRouteById(int id) {
        return routeRepository.findById(id).get();
    }

    // создать новый маршрут
    public Route createRoute(float length, int crewId, int clientId){

        Route route = new Route();

        Client client = clientRepository.findById(clientId).get();
        Crew crew = crewRepository.findById(crewId).get();

        List<Route> routeList = client.getRoutes();
        List<Employee> employeeList = crew.getEmployeeList();

        float totalPrice = 0;
        for (Employee employee : employeeList) totalPrice += employee.getSalary();

        route.setLength(length);
        route.setPrice(totalPrice);
        route.setDate(new Date());
        route.setClient(client);
        route.setCrew(crew);

        routeList.add(route);
        client.setRoutes(routeList);

        crew.setRoute(route);

        crewRepository.save(crew);
        routeRepository.save(route);
        clientRepository.save(client);

        return route;
    }


    // удаление
    public boolean delete (int id){
        Route route = findRouteById(id);
        if (route !=null ) {
            routeRepository.delete(route);
            return true;
        }
        return false;
    }


}
