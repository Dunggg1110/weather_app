package com.example.weatherapp;

public class WeatherData {
    private String temp;
    private String status;
    private String maxTemp;
    private String minTemp;

    public WeatherData(String temp, String status, String maxTemp, String minTemp) {
        this.temp = temp+"°C";
        this.status = status;
        this.maxTemp = "C:"+maxTemp+"°C";
        this.minTemp = "T:"+minTemp+"°C";
    }

    public String getTemp() {
        return temp;
    }

    public String getStatus() {
        return status;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getMinTemp() {
        return minTemp;
    }
}
