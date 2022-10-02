package com.example.priyanshu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.DragStartHelper;

import android.content.ContentProvider;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.priyanshu.Utility.NetworkChangeListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button student,faculty,guest;
    NetworkChangeListener networkChangeListener = new NetworkChangeListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        student = (Button) findViewById(R.id.student);
        student.setOnClickListener(this);
        faculty = (Button) findViewById(R.id.faculty);
        faculty.setOnClickListener(this);
        guest = (Button) findViewById(R.id.guest);
        guest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.student:
                startActivity(new Intent(this,student_login.class));
                break;
            case R.id.faculty:
                startActivity(new Intent(this,faculty_login.class));
                break;
            case R.id.guest:
                startActivity(new Intent(this,guest_login.class));
                break;
        }
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