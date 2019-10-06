package com.sysgames.cabbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomerLoginRegisterActivity extends AppCompatActivity {

    private Button buttonCustomerLogin;
    private Button buttonCustomerRegisteration;
    private TextView textViewCustomerRegistrationLink;
    private TextView textViewCustomerLogin;
    private static final String REGISTER_CUSTOMER = "Register Customer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        buttonCustomerLogin = findViewById(R.id.button_customer_login);
        buttonCustomerRegisteration = findViewById(R.id.button_customer_registration);
        textViewCustomerLogin = findViewById(R.id.text_view_customer_login);
        textViewCustomerRegistrationLink = findViewById(R.id.text_view_customer_registration_link);

        buttonCustomerRegisteration.setVisibility(View.INVISIBLE);
        buttonCustomerRegisteration.setEnabled(false);

        textViewCustomerRegistrationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonCustomerLogin.setVisibility(View.INVISIBLE);
                textViewCustomerRegistrationLink.setVisibility(View.INVISIBLE);

                textViewCustomerLogin.setText(REGISTER_CUSTOMER);

                buttonCustomerRegisteration.setVisibility(View.VISIBLE);
                buttonCustomerRegisteration.setEnabled(true);
            }
        });
    }
}
