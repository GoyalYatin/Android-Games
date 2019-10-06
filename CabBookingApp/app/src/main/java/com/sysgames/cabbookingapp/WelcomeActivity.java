package com.sysgames.cabbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button button_driver;
    private Button button_customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        button_customer = findViewById(R.id.button_customer);
        button_driver = findViewById(R.id.button_driver);

        button_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent customerLoginRegisterIntent = new Intent(
                        WelcomeActivity.this,
                        CustomerLoginRegisterActivity.class);
                startActivity(customerLoginRegisterIntent);
            }
        });

        button_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent driverLoginRegisterIntent = new Intent(
                        WelcomeActivity.this,
                        DriverLoginRegisterActivity.class);
                startActivity(driverLoginRegisterIntent);
            }
        });
    }
}
