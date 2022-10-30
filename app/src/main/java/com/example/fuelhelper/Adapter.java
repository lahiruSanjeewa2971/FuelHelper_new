package com.example.fuelhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context mcontext;
    private List<FuelStation> stations;

    public Adapter(Context mcontext, List<FuelStation> stations) {
        this.mcontext = mcontext;
        this.stations = stations;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontext);
        v = layoutInflater.inflate(R.layout.new_activity_single_fuel_station_view, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.stationname.setText(stations.get(position).getStationname());
        holder.location.setText(stations.get(position).getLocation());
        holder.petrolamount.setText(String.valueOf(stations.get(position).getPetrolamount()));
        holder.dieselamount.setText(String.valueOf(stations.get(position).getDieselamount()));
        holder.petrolqueue.setText(String.valueOf(stations.get(position).getPetrolqueue()));
        holder.dieselqueue.setText(String.valueOf(stations.get(position).getDieselqueue()));


    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView stationname, location, petrolamount, dieselamount, petrolqueue, dieselqueue;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            stationname = itemView.findViewById(R.id.stationname);
            location = itemView.findViewById(R.id.location);
            petrolamount = itemView.findViewById(R.id.petrolamount);
            dieselamount = itemView.findViewById(R.id.dieselamount);
            petrolqueue = itemView.findViewById(R.id.petrolqueue);
            dieselqueue = itemView.findViewById(R.id.dieselqueue);
        }
    }

}
