package com.example.weatherapp;

import java.util.ArrayList;

public interface ForecastCallback {
    void onSuccess(ArrayList<WeatherDay> weatherDays);

    void onError(String message);
}
