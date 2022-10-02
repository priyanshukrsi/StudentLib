package com.example.priyanshu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Curricula extends AppCompatActivity {
    Button C_Chem, C_Petro;
    String[] urls = {"https://firebasestorage.googleapis.com/v0/b/priyanshu-c8e40.appspot.com/o/documents%2FCE%20Syllabus%20_%202021-22.pdf?alt=media&token=8f69fe8f-3411-4462-bcea-42c9150d0729","https://firebasestorage.googleapis.com/v0/b/priyanshu-c8e40.appspot.com/o/documents%2FPE%20Syllabus%20_%202021-22.pdf?alt=media&token=c3213787-85d1-47fc-be83-a58bd93b4163"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curricula);
        C_Chem = findViewById(R.id.Chem);
        C_Petro = findViewById(R.id.Petro);
        C_Chem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DisplayCurricula.class);
                intent.putExtra("fileurl",Uri.parse(urls[0]));
                startActivity(intent);
            }
        });
    }
}