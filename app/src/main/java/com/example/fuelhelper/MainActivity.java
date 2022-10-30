package com.example.fuelhelper;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuelhelper.user_dashboard.user_dashboard;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Button logBtn;
    TextView signBtn;
    private Retrofit retrofit;
    private RetrofitInterface retrofitInterface;
    DBHelper db;
    private String BASE_URL = "https://fuelhelperapi.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logBtn = findViewById(R.id.loginButton);
        signBtn = findViewById(R.id.signupButton);
        EditText emailEdit = findViewById(R.id.editTextEmailLogin);
        EditText passwordEdit = findViewById(R.id.editTextPasswordLogin);
        db = new DBHelper(this);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        //Login method - User & Fuel station owner
        //with mongodb database
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();

                if(email.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkemailandpassword = db.checkemailandpassword(email, password);
                    if(checkemailandpassword==true){
                        Boolean checkuser = db.checkUser(email, password);
                        if(checkuser==true){

                            Toast.makeText(MainActivity.this, "Sign in Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), user_dashboard.class);

                            startActivity(intent);
                        }else if(checkuser ==false) {

                            Toast.makeText(MainActivity.this, "Sign in Successfull", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), fuel_station_owner_dashboard.class);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, registration.class);
                startActivity(i);
            }
        });
    }
    public Boolean[] isUser() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        Call<User> call2 = retrofitInterface.executeUserInfo();
        final Boolean[] isUser = new Boolean[1];
        call2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User result = response.body();

                if(result.getRole() == 0) {
                    isUser[0] = true;
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
        return isUser;
    };
}