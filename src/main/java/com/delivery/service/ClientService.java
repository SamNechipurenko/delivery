package com.delivery.service;

import com.delivery.model.Client;
import com.delivery.model.Employee;
import com.delivery.model.Route;
import com.delivery.repository.ClientRepository;
import com.delivery.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientService(){
    }
    // поиск
    public Client findClientById(int id){
        return clientRepository.findById(id).get();
    }

    // новый клиент
    public Client create(Client client){
        Client newClient = new Client();
        List<Route> routeList = new ArrayList<>();
        newClient.setClientName(client.getClientName());
        newClient.setClientEmail(client.getClientEmail());
        newClient.setRoutes(routeList);
        clientRepository.save(newClient);
        return newClient;
    }

    // удаление
    public boolean delete(int id){
        Client client = findClientById(id);
        if (client!=null) {
            clientRepository.delete(client);
            return true;
        }
        return false;
    }

    // редактировать
    public Client edit(int id, Client client){
        Client newClient = findClientById(id);
        client.setClientId(newClient.getClientId());
        clientRepository.save(client);
        return client;
    }


}
