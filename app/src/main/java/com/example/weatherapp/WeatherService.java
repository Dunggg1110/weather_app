package com.example.weatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WeatherService {
    private RequestQueue queue;

    public WeatherService(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void getCurrentWeather(String city, final WeatherCallback callback) {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=efd9eb0134c663e597af2d371b1fc272&lang=vi";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject mainObject = response.getJSONObject("main");
                            String temp = mainObject.getString("temp");
                            String tempMax = mainObject.getString("temp_max");
                            String tempMin = mainObject.getString("temp_min");

                            JSONArray weatherArray = response.getJSONArray("weather");
                            JSONObject weatherObject = weatherArray.getJSONObject(0);
                            String status = weatherObject.getString("description");


                            callback.onSuccess(new WeatherData(temp, status, tempMax, tempMin));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                });

        queue.add(jsonObjectRequest);
    }

    public void getForecast(String city, final ForecastCallback callback) {
        String url = "https://api.openweathermap.org/data/2.5/forecast?q="+city+"&units=metric&appid=efd9eb0134c663e597af2d371b1fc272&lang=vi";
//        String url = "https://api.openweathermap.org/data/2.5/forecast/daily?q="+city+"&units=metric&cnt=7&appid=efd9eb0134c663e597af2d371b1fc272&lang=vi";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ArrayList<WeatherDay> weatherDays = new ArrayList<>();
                            JSONArray list = response.getJSONArray("list");
                            for (int i = 0; i < list.length(); i +=4) {
                                JSONObject dayObject = list.getJSONObject(i);
                                JSONObject mainObject = dayObject.getJSONObject("main");
                                JSONArray weatherArray = dayObject.getJSONArray("weather");
                                JSONObject weatherObject = weatherArray.getJSONObject(0);

                                String dateString = dayObject.getString("dt_txt");
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                                Date date = sdf.parse(dateString);
                                DateFormatSymbols vietnameseSymbols = new DateFormatSymbols(new Locale("vi"));
                                SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE \n HH:mm \ndd MMMM yyyy", vietnameseSymbols);
                                String day = dayFormat.format(date);

                                String status = weatherObject.getString("description");
                                String image = weatherObject.getString("icon");
                                double minTemp = mainObject.getDouble("temp_min");
                                double maxTemp = mainObject.getDouble("temp_max");


                                weatherDays.add(new WeatherDay(day, status,image, String.valueOf(minTemp), String.valueOf(maxTemp)));
                            }
                            callback.onSuccess(weatherDays);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            callback.onError(e.getMessage());
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                });

        queue.add(jsonObjectRequest);
    }
}
