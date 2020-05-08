package com.delivery.model;

import javax.persistence.*;

@Entity
@Table(name = "vehicles")
public class Vehicle{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int vehicleId;

    @Column(name = "numOfSeats")
    private int numOfSeats;

    @Column(name = "midVelocity")
    private float midVelocity;

    @Column(name = "brand")
    private String brand;

    @Column(name = "engaged")
    private boolean engaged;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crew_id")
    private Crew crew;

    public Vehicle() {
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getNumOfSeats() {
        return numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public float getMidVelocity() {
        return midVelocity;
    }

    public void setMidVelocity(float midVelocity) {
        this.midVelocity = midVelocity;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isEngaged() {
        return engaged;
    }

    public void setEngaged(boolean engaged) {
        this.engaged = engaged;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }
}