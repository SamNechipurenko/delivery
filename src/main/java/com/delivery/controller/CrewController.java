package com.delivery.controller;

import com.delivery.exception.CostumedException;
import com.delivery.model.Crew;
import com.delivery.model.Roles;
import com.delivery.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class CrewController {

    @Autowired
    CrewService crewService;

    // добавить команду
    @PostMapping(path = "/crew")
    public ResponseEntity create(Model mav, @RequestBody Roles roles) throws Exception{
        Crew crew = crewService.createCrew(roles.getRoles());
        if (crew != null) {
            return new ResponseEntity(crew, HttpStatus.OK);
        }
        throw new CostumedException(" Bad request, please try later ", 400);
    }

    // поиск по id
    @GetMapping("/crew/{id}")
    public ResponseEntity<Crew> getVehiclesBySeats(Model mav, @PathVariable String id) throws Exception{
        Crew crew = crewService.findCrewById(Integer.parseInt(id));
        if (crew != null) {
            return new ResponseEntity<>(crew, HttpStatus.OK);
        }
        throw new CostumedException(" Crew not found ", 404);
    }

    // удаление
    @DeleteMapping("/crew/{id}")
    public ResponseEntity<Crew> deleteCrew(Model mav, @PathVariable String id) throws Exception{
        boolean deleted = crewService.delete(Integer.parseInt(id));
        if (deleted) {
            return new ResponseEntity(HttpStatus.OK);
        }

        throw new CostumedException(" Crew not found ", 404);
    }

}
