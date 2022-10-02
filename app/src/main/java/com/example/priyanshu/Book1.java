package com.example.priyanshu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Book1 extends AppCompatActivity {
    int DayDiff;
    String bname,Issue_Date,Due_Date;
    TextView Bname, IssueDate, DueDate ,Due;
    Button Pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book1);
        Bname = findViewById(R.id.Bname);
        IssueDate = findViewById(R.id.IssueDate);
        DueDate = findViewById(R.id.DueDate);
        Pay = findViewById(R.id.Pay);
        Due = findViewById(R.id.Due);
        Intent intent = getIntent();
        bname = intent.getStringExtra("book name");
        Bname.setText(bname);
        bname = intent.getStringExtra("book name");
        Due_Date = intent.getStringExtra("Due date");
        Issue_Date = intent.getStringExtra("Issue Date");
        DueDate.setText(Due_Date);
        IssueDate.setText(Issue_Date);
        DayDiff = intent.getIntExtra("Date Difference",0);
        Due.setText(Integer.toString(DayDiff));
        if(DayDiff > 0) {
            Pay.setEnabled(true);
        }
    }
}