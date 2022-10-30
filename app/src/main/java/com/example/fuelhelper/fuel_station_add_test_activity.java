package com.example.fuelhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class fuel_station_add_test_activity extends AppCompatActivity {
    Button newFuelStationAddButton;
    EditText stationName, location, petrolAmount, dieselAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_station_add_test);

        newFuelStationAddButton = findViewById(R.id.newFuelStationButton);

        stationName = findViewById(R.id.fuelStationNameET);
        location = findViewById(R.id.locationET);
        petrolAmount = findViewById(R.id.petrolAmountET);
        dieselAmount = findViewById(R.id.dieselAmountET);

        newFuelStationAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFuelStationAdd();
            }
        });
    }
    private void newFuelStationAdd(){
        String URL = "https://fuelhelperapi.herokuapp.com/api/fuelstations";
        String stationNameGet = stationName.getText().toString();
        String locationGet = location.getText().toString();
        int petrolAmountGet = Integer.parseInt(petrolAmount.getText().toString());
        int dieselAmountGet = Integer.parseInt(dieselAmount.getText().toString());

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();

        try {
            postData.put("stationname", stationNameGet);
            postData.put("location", locationGet);
            postData.put("petrolamount", petrolAmountGet);
            postData.put("dieselamount", dieselAmountGet);
        }catch (Exception e){
            e.printStackTrace();
        }

        String url = "https://fuelhelperapi.herokuapp.com/api/fuelstations";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, postData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("___Responce___"+response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        error.printStackTrace();
                        System.out.println("___Responce___"+error.toString());
                    }
        });
        requestQueue.add(jsonObjectRequest);
    }
}