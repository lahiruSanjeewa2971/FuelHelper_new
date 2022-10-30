package com.example.fuelhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class user_check_fuel_station extends AppCompatActivity {
    CardView cardView01;
    Button joinFuelStationQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_check_fuel_station);

        cardView01 = findViewById(R.id.selectCardView01);
        joinFuelStationQueue = findViewById(R.id.joinFuelStationButton);

        cardView01.setBackgroundResource(R.drawable.box01);

        joinFuelStationQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_check_fuel_station.this, user_join_fuel_station.class);
                startActivity(intent);
            }
        });
    }
}