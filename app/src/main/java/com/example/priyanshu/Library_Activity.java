package com.example.priyanshu;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Library_Activity extends AppCompatActivity {

    FirebaseFirestore db;
    TextView id1, id2, id3, book1, book2, book3, date1, date2, date3;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        db = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        email = intent.getStringExtra("email");

        id1 = (TextView) findViewById(R.id.id1);
        id2 = (TextView) findViewById(R.id.id2);
        id3 = (TextView) findViewById(R.id.id3);
        book1 = (TextView) findViewById(R.id.book1);
        book2 = (TextView) findViewById(R.id.book2);
        book3 = (TextView) findViewById(R.id.book3);
        date1 = (TextView) findViewById(R.id.date1);
        date2 = (TextView) findViewById(R.id.date2);
        date3 = (TextView) findViewById(R.id.date3);
        TextView[] r1 = {id1, book1, date1};
        TextView[] r2 = {id2, book2, date2};
        TextView[] r3 = {id3, book3, date3};
        String[] dId = {"book1", "book2", "book3"};

        fetchData(email, dId[0],r1);
        fetchData(email, dId[1],r2);
        fetchData(email, dId[2],r3);


    }

        public void fetchData(String email, String docId,TextView[] r) {

        DocumentReference document = db.collection(email).document(docId);
            document.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>(){
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot){
                    if(documentSnapshot.exists()){
                        int num=documentSnapshot.getLong("Accn").intValue();
                        String bname = documentSnapshot.getString("Title Details");
                        r[0].setText(Integer.toString(num));
                        r[1].setText(bname);
                        Timestamp timestamp2 = (Timestamp) documentSnapshot.getData().get("Issue date");
                        Date Issue_date = timestamp2.toDate();
                        Date dueDate = addDay(Issue_date,10);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String Ddate = sdf.format(dueDate);
                        String DueDate = DateFormat.getDateInstance().format(dueDate);
                        String I_date = DateFormat.getDateInstance().format(Issue_date);
                        int Diff = getDateDiffFromNow(Ddate);
                        r[1].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getApplicationContext(),Book1.class);
                                intent.putExtra("book name", bname);
                                intent.putExtra("Due date",DueDate);
                                intent.putExtra("Issue Date",I_date);
//                                intent.putExtra("Date Difference", Integer.toString(Diff));
                                if(Diff > 0) {
                                    intent.putExtra("Date Difference", Diff);
                                }
                                startActivity(intent);
                            }
                        });
                        r[2].setText(DueDate);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Data not available in row",Toast.LENGTH_LONG).show();
                        r[0].setVisibility(View.INVISIBLE);
                        r[1].setVisibility(View.INVISIBLE);
                        r[2].setVisibility(View.INVISIBLE);
                    }
                }
            })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to fetch data", Toast.LENGTH_LONG).show();
                    }
                });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static Date addDay(Date date, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, i);
        return cal.getTime();
    }
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    public static String dateDiff(String prev){
//        String FinalDate= prev;                           //previous date
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//        Date today = Calendar.getInstance().getTime();
//        String CurrentDate = sdf.format(today);      //current date
//        Date date2;
//        SimpleDateFormat dates = new SimpleDateFormat("MM/dd/yyyy");
//        today = dates.parse(today);
//        date2 = dates.parse(FinalDate);
//        long difference = Math.abs(today.getTime() - date2.getTime());
//        long differenceDates = difference / (24 * 60 * 60 * 1000);
//        String dayDifference = Long.toString(differenceDates);
//        return dayDifference;
//    }
    public int getDateDiffFromNow(String date){
        int days = 0;
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            long diff = new Date().getTime() - sdf.parse(date).getTime();
            long seconds = diff / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            days = ((int) (long) hours / 24);
        }catch (Exception e){
            e.printStackTrace();
        }
        return days;
    }
}