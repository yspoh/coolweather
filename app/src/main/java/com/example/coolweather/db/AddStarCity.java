package com.example.coolweather.db;

import org.litepal.crud.DataSupport;

public class AddStarCity extends DataSupport {

    private int id;

    private String city;

    private String weatherId;

    public String getWeatherId()
    {
        return weatherId;
    }

    public void setWeatherId(String weatherId)
    {
        this.weatherId = weatherId;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {

        return id;
    }

    public String getCity() {
        return city;
    }
}
