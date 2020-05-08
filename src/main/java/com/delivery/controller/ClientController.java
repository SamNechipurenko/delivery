package com.delivery.controller;

import com.delivery.exception.CostumedException;
import com.delivery.model.Client;
import com.delivery.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    Client client;
    @Autowired
    ClientService clientService;

    // поиск
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> main(Model mav, @PathVariable String id) throws CostumedException {
        client = clientService.findClientById(Integer.parseInt(id));
        if (client!=null) {
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        throw new CostumedException(" Client not found ", 404);
    }

    //добавление
    @PostMapping(path = "/client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> addClient (Model mav, @RequestBody Client client) throws CostumedException {
        if (client.getClientName() != null && client.getClientEmail() != null) {
            client = clientService.create(client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        throw new CostumedException(" Bad request, please try again ", 403);
    }

    //удаление
    @DeleteMapping(path = "/client/{id}")
    public ResponseEntity<Client> delete(Model mav,
                                           @PathVariable String id) throws CostumedException {
        boolean deleted = clientService.delete(Integer.parseInt(id));
        if (deleted) {
            return new ResponseEntity(HttpStatus.OK);
        }
        throw new CostumedException(" Client not found ", 404);
    }

    // изменение
    @PutMapping(path = "/client/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> editEngagement(Model mav,
                                                 @PathVariable String id,
                                                 @RequestBody Client client) throws CostumedException {
        if (client!=null) {
            client = clientService.edit(Integer.parseInt(id), client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        }
        throw new CostumedException(" Client not found ", 404);
    }



}
