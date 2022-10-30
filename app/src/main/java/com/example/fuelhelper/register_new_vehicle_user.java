package com.example.fuelhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fuelhelper.user_dashboard.user_dashboard;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class register_new_vehicle_user extends AppCompatActivity {

    Button addnewvehicle, userLogoutBtn;;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "https://fuelhelperapi.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_new_vehicle_user);

        addnewvehicle = findViewById(R.id.btnRegisterVehicle);
        EditText vehicleNoEdit = findViewById(R.id.etVehicleNo);
        EditText vehicleModelEdit = findViewById(R.id.etVehicleModel);
        EditText vehicleTypeEdit = findViewById(R.id.etVehicleType);
        EditText fuelTypeEdit = findViewById(R.id.etFuelType);
        userLogoutBtn = findViewById(R.id.btnuserlogout);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        addnewvehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HashMap<String, String> map = new HashMap<>();

                map.put("vehicleno", vehicleNoEdit.getText().toString());
                map.put("model", vehicleModelEdit.getText().toString());
                map.put("vehicletype", vehicleTypeEdit.getText().toString());
                map.put("fueltype", fuelTypeEdit.getText().toString());


                Call<Void> call = retrofitInterface.executeAddVehicle(map);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200){
                            Toast.makeText(register_new_vehicle_user.this, "Vehicle registered successfully", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(register_new_vehicle_user.this, user_dashboard.class);
                            startActivity(i);
                        }else if (response.code() == 400){
                            Toast.makeText(register_new_vehicle_user.this, "This vehicle already registered", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(register_new_vehicle_user.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        userLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(register_new_vehicle_user.this, "Log out successfully", Toast.LENGTH_LONG).show();
                Intent i = new Intent(register_new_vehicle_user.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}