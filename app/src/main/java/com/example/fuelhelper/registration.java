package com.example.fuelhelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.fuelhelper.user_dashboard.user_dashboard;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registration extends AppCompatActivity {
    Button registerBtn;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    private String BASE_URL = "https://fuelhelperapi.herokuapp.com";
    DBHelper db;
    String  usertypeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registerBtn = findViewById(R.id.registerButton);
        EditText nameEdit = findViewById(R.id.editTextNameReg);
        EditText emailEdit = findViewById(R.id.editTextEmailReg);
        EditText passwordEdit = findViewById(R.id.ediTextPasswordReg);
        RadioGroup usertype = findViewById(R.id.UserType);
        RadioButton userradio = findViewById(R.id.userradiobtn);
        RadioButton stationownerradio = findViewById(R.id.stationownerradiobutton);
        db = new DBHelper(this);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                int usertypeid = usertype.getCheckedRadioButtonId();

                if(name.equals("") || email.equals("") || password.equals("") || usertypeid == -1){
                    Toast.makeText(registration.this, "Please enter all the fields", Toast.LENGTH_LONG).show();
                }else{
                    Boolean checkuser = db.checkemail(email);
                    if(checkuser==false){


                        if(usertypeid == R.id.userradiobtn){
                            usertypeText = "user";
                            Boolean insert = db.insertData(name, email,  usertypeText, password);
                            if(insert == true){
                                Toast.makeText(registration.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), user_dashboard.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }else if(usertypeid == R.id.stationownerradiobutton){
                            usertypeText = "stationowner";
                            Boolean insert = db.insertData(name, email, usertypeText, password);
                            if(insert == true){
                                Toast.makeText(registration.this, "Station Owner Register successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), fuel_station_owner_dashboard.class);
                                startActivity(intent);
                            } else{
                                Toast.makeText(registration.this, "Station OnwerRegistration failed", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else{
                        Toast.makeText(registration.this, "User is already exist, Please signin", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}