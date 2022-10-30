package com.example.fuelhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class list_all_fuel_stations_test_try extends AppCompatActivity {

    TextView fuelStationLocation;
    Button listAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_fuel_stations_test_try);

        fuelStationLocation = findViewById(R.id.fuelStationLocationTV);
        listAll = findViewById(R.id.listAllButton);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        listAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String listURL = "https://fuelhelperapi.herokuapp.com/api/fuelstations";
                //String listURL = "http://localhost:8000/api/fuelstations";

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, listURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject = jsonArray.getJSONObject(i);

                                String mLocation = jsonObject.getString("location");

                                fuelStationLocation.append(mLocation + "\n");
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(list_all_fuel_stations_test_try.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}