package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class WeatherAdapter extends ArrayAdapter<WeatherDay> {

    private Context mContext;
    private int mResource;

    public WeatherAdapter(Context context, int resource, ArrayList<WeatherDay> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String day = getItem(position).getDay();
        String status = getItem(position).getStatus();
        String minTemp = getItem(position).getMinTemp();
        String maxTemp = getItem(position).getMaxTemp();
        String image = getItem(position).getImage();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvDay = convertView.findViewById(R.id.tvnextDay);
        TextView tvStatus = convertView.findViewById(R.id.tvnextDayStatus);
        ImageView imgStatus = convertView.findViewById(R.id.imageStatus);
        TextView tvMinTemp = convertView.findViewById(R.id.tvnextDayMin);
        TextView tvMaxTemp = convertView.findViewById(R.id.tvnextDayMax);

        tvDay.setText(day);
        tvStatus.setText(status);
        Picasso.get().load("https://openweathermap.org/img/w/" + image + ".png").into(imgStatus);
        tvMinTemp.setText(minTemp);
        tvMaxTemp.setText(maxTemp);

        return convertView;
    }
}