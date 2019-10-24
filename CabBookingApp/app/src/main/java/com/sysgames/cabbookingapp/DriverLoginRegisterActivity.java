package com.sysgames.cabbookingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginRegisterActivity extends AppCompatActivity {

    private Button buttonDriverLogin;
    private Button buttonDriverRegisteration;
    private TextView textViewDriverRegistrationLink;
    private TextView textViewDriverLogin;
    private EditText editTextDriverEmail;
    private EditText editTextDriverPassword;
    private FirebaseAuth mAuth;
    private ProgressDialog loadingBar;

    private static final String REGISTER_DRIVER = "Register Driver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login_register);

        mAuth = FirebaseAuth.getInstance();

        buttonDriverLogin = findViewById(R.id.button_driver_login);
        buttonDriverRegisteration = findViewById(R.id.button_driver_registration);
        textViewDriverLogin = findViewById(R.id.text_view_driver_login);
        textViewDriverRegistrationLink = findViewById(R.id.text_view_driver_registration_link);
        editTextDriverEmail = findViewById(R.id.edit_text_driver_email);
        editTextDriverPassword = findViewById(R.id.edit_text_driver_password);
        loadingBar = new ProgressDialog(this);

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

        buttonDriverRegisteration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextDriverEmail.getText().toString();
                String password = editTextDriverPassword.getText().toString();

                registerDriver(email, password);
            }
        });

        buttonDriverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextDriverEmail.getText().toString();
                String password = editTextDriverPassword.getText().toString();

                loginDriver(email, password);
            }
        });
    }

    private void registerDriver(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(DriverLoginRegisterActivity.this,
                    "Please write your Eamil!!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(DriverLoginRegisterActivity.this,
                    "Please write your Password!!", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Driver Registration");
            loadingBar.setMessage("Please wait, while we are registering you");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(DriverLoginRegisterActivity.this,
                                        "Registered Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            } else {
                                Toast.makeText(DriverLoginRegisterActivity.this,
                                        "Registeration UnSuccessful, try again",
                                        Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }

    private void loginDriver(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(DriverLoginRegisterActivity.this,
                    "Please write your Eamil!!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(DriverLoginRegisterActivity.this,
                    "Please write your Password!!", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Driver Login");
            loadingBar.setMessage("Please wait, while we are logging in you");
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(DriverLoginRegisterActivity.this,
                                        "Logged in Successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            } else {
                                Toast.makeText(DriverLoginRegisterActivity.this,
                                        "Log in is UnSuccessful, try again",
                                        Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }
}
