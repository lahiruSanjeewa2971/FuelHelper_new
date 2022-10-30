package com.example.fuelhelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class view_all_fuel_stations extends AppCompatActivity {
    CardView cardView01 , cardView02, cardView03;
    Button editFuelArrivalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_fuel_stations);

        cardView01 = findViewById(R.id.listCardView01);
        cardView02 = findViewById(R.id.listCardView02);

        editFuelArrivalButton = findViewById(R.id.updateFuelArrivalButton);

        //cardView01.setBackgroundResource(R.drawable.box01);
        cardView01.setBackgroundResource(R.drawable.box01);
        cardView02.setBackgroundResource(R.drawable.box01);

        editFuelArrivalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view_all_fuel_stations.this, station_owner_update_fuel_arrival.class);
                startActivity(intent);
            }
        });
    }
}