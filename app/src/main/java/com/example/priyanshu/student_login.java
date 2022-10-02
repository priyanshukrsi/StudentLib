package com.example.priyanshu;

import static android.view.View.VISIBLE;

import androidx.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.priyanshu.Utility.NetworkChangeListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class student_login extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private Button signIn;
    private EditText editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private TextView ErrorMessage;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        signIn = (Button) findViewById(R.id.login);
        signIn.setOnClickListener(this);
        editTextEmail = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.loading);
        mAuth = FirebaseAuth.getInstance();
        ErrorMessage = (TextView) findViewById(R.id.error_message);


    }

    @Override
    public void onClick(View v) {

        userLogin();
    }

    public void userLogin() {
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

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(),student_homepage.class);
                    intent.putExtra("email", email);
                    startActivity(intent);

                }
                else {
                        ErrorMessage.setText("Invalid credentials!! Please try again....");
                        progressBar.setVisibility(View.GONE);
                }
            }
        });

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