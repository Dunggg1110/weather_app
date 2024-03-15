package com.example.weatherapp;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvTemp, tvStatus, tvTempMax, tvTempMin;
    private ListView listViewNextDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTemp = findViewById(R.id.tvTemp);
        tvStatus = findViewById(R.id.tvStatus);
        tvTempMax = findViewById(R.id.tvTempMax);
        tvTempMin = findViewById(R.id.tvTempMin);
        listViewNextDays = findViewById(R.id.listViewNextDays);

        WeatherService weatherService = getWeatherService();

        // Lấy dữ liệu thời tiết cho 7 ngày tiếp theo
        weatherService.getForecast("hanoi", new ForecastCallback() {
            @Override
            public void onSuccess(ArrayList<WeatherDay> weatherDays) {
                WeatherAdapter adapter = new WeatherAdapter(MainActivity.this, R.layout.nextday_listview, weatherDays);
                listViewNextDays.setAdapter((ListAdapter) adapter);
            }

            @Override
            public void onError(String message) {
                // Xử lý khi gặp lỗi
            }
        });
    }

    @NonNull
    private WeatherService getWeatherService() {
        WeatherService weatherService = new WeatherService(this);

        // Lấy dữ liệu thời tiết cho ngày hiện tại
        weatherService.getCurrentWeather("hanoi", new WeatherCallback() {
            @Override
            public void onSuccess(WeatherData data) {
                tvTemp.setText(String.valueOf(data.getTemp()));
                tvStatus.setText(data.getStatus());
                tvTempMax.setText(String.valueOf(data.getMaxTemp()));
                tvTempMin.setText(String.valueOf(data.getMinTemp()));
            }

            @Override
            public void onError(String message) {
                // Xử lý khi gặp lỗi
            }
        });
        return weatherService;
    }
}


