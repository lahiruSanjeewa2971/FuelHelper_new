package com.example.fuelhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class list_fuel_stations_user_view_2 extends AppCompatActivity {
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "https://fuelhelperapi.herokuapp.com";
    RecyclerView recyclerView;
    List<FuelStation> fuelStationslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_fuel_stations_user_view2);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);
        recyclerView = findViewById(R.id.recycleview2);
        fuelStationslist = new ArrayList<>();

        Call<List<FuelStation>> call = retrofitInterface.getFuelstations();

        call.enqueue(new Callback<List<FuelStation>>() {
            @Override
            public void onResponse(Call<List<FuelStation>> call, Response<List<FuelStation>> response) {

                List<FuelStation> fuelStations = response.body();
                for(FuelStation fuelStation : fuelStations){
//                   String fuelstationname = fuelStation.getStationname();
//                   String location = fuelStation.getLocation();
//                   int petrolamount = fuelStation.getPetrolamount();
//                   int dieselamount = fuelStation.getDieselamount();
//                   int petrolqueue = fuelStation.getPetrolqueue();
//                   int dieselqueue = fuelStation.getDieselqueue();

                    fuelStationslist.add(fuelStation);

                }
                putDataIntoRecyclerView(fuelStations);
            }



            @Override
            public void onFailure(Call<List<FuelStation>> call, Throwable t) {

            }
        });
    }
    private void putDataIntoRecyclerView(List<FuelStation> fuelStations) {
        Adapter adapter = new Adapter(this, fuelStations);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}