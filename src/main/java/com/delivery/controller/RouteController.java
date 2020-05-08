package com.delivery.controller;

import com.delivery.exception.CostumedException;
import com.delivery.model.FormRouteModel;
import com.delivery.model.Route;
import com.delivery.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class RouteController {

    @Autowired
    RouteService routeService;

    // новый маршрут
    @PostMapping(path = "/route")
    public ResponseEntity create(Model mav, @RequestBody FormRouteModel formRouteModel) throws Exception {
        Route route = routeService.createRoute(formRouteModel.getLength()
                , formRouteModel.getCrewId(), formRouteModel.getClientId());
        if (route != null) {
            return new ResponseEntity(route, HttpStatus.OK);
        }
        throw new CostumedException(" Bad request ", 404);
    }

    // поиск
    @GetMapping("/route/{id}")
    public ResponseEntity<Route> getVehiclesBySeats(Model mav, @PathVariable String id) throws CostumedException {
        Route route = routeService.findRouteById(Integer.parseInt(id));
        if (route != null) {
            return new ResponseEntity<>(route, HttpStatus.OK);
        }
        throw new CostumedException(" Route not found ", 404);
    }

    // удаление
    @DeleteMapping("/route/{id}")
    public ResponseEntity<Route> deleteRoute(Model mav, @PathVariable String id) throws CostumedException {
        Route route = routeService.findRouteById(Integer.parseInt(id));
        if (route != null) {
            routeService.delete(route.getRouteId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new CostumedException(" Route not found ", 404);
    }
}
