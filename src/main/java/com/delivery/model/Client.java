package com.delivery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer clientId;

    @Column(name = "name")
    private String clientName;

    @Column(name = "email")
    private String clientEmail;

    @OneToMany (mappedBy = "client", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Route> routes;

    public Client() {
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }



    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return getClientId().equals(client.getClientId()) &&
                getClientName().equals(client.getClientName()) &&
                getClientEmail().equals(client.getClientEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClientId(), getClientName(), getClientEmail());
    }
}
