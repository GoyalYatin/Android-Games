package com.sysgames.cabbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DriverLoginRegisterActivity extends AppCompatActivity {

    private Button buttonDriverLogin;
    private Button buttonDriverRegisteration;
    private TextView textViewDriverRegistrationLink;
    private TextView textViewDriverLogin;
    private static final String REGISTER_DRIVER = "Register Driver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);

        buttonDriverLogin = findViewById(R.id.button_driver_login);
        buttonDriverRegisteration = findViewById(R.id.button_driver_registration);
        textViewDriverLogin = findViewById(R.id.text_view_driver_login);
        textViewDriverRegistrationLink = findViewById(R.id.text_view_driver_registration_link);

        buttonDriverRegisteration.setVisibility(View.INVISIBLE);
        buttonDriverRegisteration.setEnabled(false);

        textViewDriverRegistrationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonDriverLogin.setVisibility(View.INVISIBLE);
                textViewDriverRegistrationLink.setVisibility(View.INVISIBLE);

                textViewDriverLogin.setText(REGISTER_DRIVER);

                buttonDriverRegisteration.setVisibility(View.VISIBLE);
                buttonDriverRegisteration.setEnabled(true);
            }
        });
    }
}
