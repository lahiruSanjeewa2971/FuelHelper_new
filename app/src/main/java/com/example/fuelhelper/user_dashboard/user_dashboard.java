package com.example.fuelhelper.user_dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fuelhelper.MainActivity;
import com.example.fuelhelper.R;
import com.example.fuelhelper.register_new_vehicle_user;
import com.example.fuelhelper.list_fuel_stations_user_view;


public class user_dashboard extends AppCompatActivity {
    Button registerVehicleBtn, checkFuelBtn, userLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        registerVehicleBtn = findViewById(R.id.registerVehiclebutton);
        checkFuelBtn = findViewById(R.id.checkFuelStationBtn);
        userLogoutBtn = findViewById(R.id.userLogoutBtn);

        registerVehicleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(user_dashboard.this, register_new_vehicle_user.class);
                startActivity(i);
            }
        });

        userLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(user_dashboard.this, "Log out successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(user_dashboard.this, MainActivity.class);
                startActivity(i);
            }
        });

        checkFuelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(user_dashboard.this, list_fuel_stations_user_view.class);
                startActivity(intent);
            }
        });
    }
}