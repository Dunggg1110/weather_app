package com.example.weatherapp;

// Interface để xử lý callback từ WeatherService
public interface WeatherCallback {
    void onSuccess(WeatherData data);

    void onError(String message);
}
