package com.example.fuelhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.example.fuelhelper.user_dashboard.user_dashboard;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class add_new_fuel_station extends AppCompatActivity {

    Button addnewfuelstationBtn;
    EditText stationName, location, petrolAmount, dieselAmount;
    SharedPreferenceClass sharedPreferenceClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_fuel_station);

        sharedPreferenceClass = new SharedPreferenceClass(this);
        addnewfuelstationBtn = findViewById(R.id.btnAddFuelStation);

        addnewfuelstationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewFuelStation();
            }
        });
    }
    public void addNewFuelStation(){
        stationName = findViewById(R.id.etFuelstationName);
        location = findViewById(R.id.etLocation);
        petrolAmount = findViewById(R.id.etPetrolAmount);
        dieselAmount = findViewById(R.id.etDieselAmount);

        String stationLocation = location.getText().toString();
        String fuelStationName = stationName.getText().toString();
        //int petrol_Amount = Integer.parseInt(petrolAmount.getText().toString());
        //int diesel_Amount = Integer.parseInt(dieselAmount.getText().toString());
        String petrol_Amount = petrolAmount.getText().toString();
        String diesel_Amount = dieselAmount.getText().toString();

        if (!stationLocation.isEmpty()){
            addTask(fuelStationName, stationLocation, petrol_Amount, diesel_Amount);
            Toast.makeText(this, "Entered data.", Toast.LENGTH_SHORT).show();
            System.out.println(stationName);
        }else{
            Toast.makeText(this, "Enter fuel station location.", Toast.LENGTH_SHORT).show();
        }
    }

    private void addTask(String fuelStationName, String stationLocation, String petrol_amount, String diesel_amount) {
        String url = "https://fuelhelperapi.herokuapp.com/api/fuelstations";
        HashMap<String, String> body = new HashMap<>();
        body.put("stationname", fuelStationName);
        body.put("location", stationLocation);
        body.put("petrolamount", petrol_amount);
        body.put("dieselamount", diesel_amount);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url, new JSONObject(body), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getBoolean("success")) {
                        Toast.makeText(add_new_fuel_station.this, "Added success.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                NetworkResponse response = error.networkResponse;
                if(error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers,  "utf-8"));
                        JSONObject obj = new JSONObject(res);
                        Toast.makeText(add_new_fuel_station.this, obj.getString("msg"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException | UnsupportedEncodingException je) {
                        je.printStackTrace();
                    }
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");

                return headers;
            }
        };

        // set retry policy
        int socketTime = 3000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTime,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jsonObjectRequest.setRetryPolicy(policy);

        // request add
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }

}