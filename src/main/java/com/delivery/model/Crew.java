package com.delivery.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "crews")
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer crewId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="vehicles_id")
    private Vehicle vehicle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="routes_id")
    private Route route;

    @OneToMany (mappedBy = "crew", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Employee> employeeList;


    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Crew addEmployee(Employee employee) {
        employeeList = new ArrayList<>();
        this.employeeList.add(employee);
        employee.setCrew(this);
        return this;
    }

    public Crew() {
    }

}