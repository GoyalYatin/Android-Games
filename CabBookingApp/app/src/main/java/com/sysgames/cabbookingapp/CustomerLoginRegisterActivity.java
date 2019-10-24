package com.sysgames.cabbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class CustomerLoginRegisterActivity extends AppCompatActivity {

    private Button buttonCustomerLogin;
    private Button buttonCustomerRegisteration;
    private TextView textViewCustomerRegistrationLink;
    private TextView textViewCustomerLogin;
    private EditText editTextCustomerEmail;
    private EditText editTextCustomerPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;
    private static final String REGISTER_CUSTOMER = "Register Customer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login_register);

        mAuth = FirebaseAuth.getInstance();

        buttonCustomerLogin = findViewById(R.id.button_customer_login);
        buttonCustomerRegisteration = findViewById(R.id.button_customer_registration);
        textViewCustomerLogin = findViewById(R.id.text_view_customer_login);
        textViewCustomerRegistrationLink = findViewById(R.id.text_view_customer_registration_link);
        editTextCustomerEmail = findViewById(R.id.edit_text_customer_email);
        editTextCustomerPassword = findViewById(R.id.edit_text_customer_password);
        loadingBar = new ProgressDialog(this);

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

        buttonCustomerRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextCustomerEmail.getText().toString();
                String password = editTextCustomerPassword.getText().toString();

                registerCustomer(email, password);
            }
        });

        buttonCustomerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextCustomerEmail.getText().toString();
                String password = editTextCustomerPassword.getText().toString();

                loginCustomer(email, password);
            }
        });
    }

    private void loginCustomer(String email, String password) {

    }

    private void registerCustomer(String email, String password) {

    }
}
