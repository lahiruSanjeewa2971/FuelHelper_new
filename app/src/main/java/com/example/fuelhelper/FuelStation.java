package com.example.fuelhelper;

public class FuelStation {
    String stationname;
    String location;
    Integer petrolamount;
    Integer dieselamount;
    Integer petrolqueue;
    Integer dieselqueue;

    public String getStationname() {
        return stationname;
    }

    public void setStationname(String stationname) {
        this.stationname = stationname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getPetrolamount() {
        return petrolamount;
    }

    public void setPetrolamount(Integer petrolamount) {
        this.petrolamount = petrolamount;
    }

    public Integer getDieselamount() {
        return dieselamount;
    }

    public void setDieselamount(Integer dieselamount) {
        this.dieselamount = dieselamount;
    }

    public Integer getPetrolqueue() {
        return petrolqueue;
    }

    public void setPetrolqueue(Integer petrolqueue) {
        this.petrolqueue = petrolqueue;
    }

    public Integer getDieselqueue() {
        return dieselqueue;
    }

    public void setDieselqueue(Integer dieselqueue) {
        this.dieselqueue = dieselqueue;
    }
}
