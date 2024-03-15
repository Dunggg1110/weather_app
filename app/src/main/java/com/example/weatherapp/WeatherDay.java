package com.example.weatherapp;

public class WeatherDay {
    private String day;
    private String status;
    private String minTemp;
    private String maxTemp;
    private String image;

    public WeatherDay(String day, String status, String image, String minTemp, String maxTemp) {
        this.day = day;
        this.status = status;
        this.minTemp = minTemp+"°C";
        this.maxTemp = maxTemp+"°C";
        this.image = image;
    }

    public String getDay() {
        return day;
    }

    public String getStatus() {
        return status;
    }

    public String getMinTemp() {
        return minTemp;
    }

    public String getMaxTemp() {
        return maxTemp;
    }

    public String getImage() {
        return image;
    }
}
