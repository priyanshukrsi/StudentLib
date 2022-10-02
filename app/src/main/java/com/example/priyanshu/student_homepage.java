package com.example.priyanshu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class student_homepage extends AppCompatActivity implements View.OnClickListener{
    private TextView welcome;
    private Button library,curricula,timetable;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_homepage);
        welcome = (TextView) findViewById(R.id.welcome_text);
        library = (Button) findViewById(R.id.librar);
        curricula = (Button) findViewById(R.id.curricula);
        timetable = (Button) findViewById(R.id.time_table);
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                      Intent intent = new Intent(getApplicationContext(),TimeTable.class);
                      startActivity(intent);
            }
        });
                curricula.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), Curricula.class);
                        startActivity(intent);
                    }
                });
        library.setOnClickListener(this);
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getApplicationContext(),Library_Activity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}