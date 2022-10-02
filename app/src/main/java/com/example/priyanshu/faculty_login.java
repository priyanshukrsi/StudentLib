package com.example.priyanshu;

import static android.view.View.VISIBLE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.priyanshu.Utility.NetworkChangeListener;
import com.google.firebase.auth.FirebaseAuth;

public class faculty_login extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button signIn;
    private EditText editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_login);

        signIn = (Button) findViewById(R.id.login);
        signIn.setOnClickListener(this);
        editTextEmail = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.loading);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {

        userLogin();
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        signIn.setEnabled(true);

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            if(progressBar.getVisibility() == VISIBLE)
                progressBar.setVisibility(View.GONE);
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email!!");
            editTextEmail.requestFocus();
            if(progressBar.getVisibility() == VISIBLE)
                progressBar.setVisibility(View.GONE);
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is Required!");
            editTextPassword.requestFocus();
            if(progressBar.getVisibility() == VISIBLE)
                progressBar.setVisibility(View.GONE);
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum password length is 6 character!! Please try again.");
            editTextPassword.requestFocus();
            if(progressBar.getVisibility() == VISIBLE)
                progressBar.setVisibility(View.GONE);
            return;
        }

        progressBar.setVisibility(VISIBLE);

    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

}