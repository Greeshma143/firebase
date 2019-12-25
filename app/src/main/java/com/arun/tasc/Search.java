package com.arun.tasc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Search extends AppCompatActivity {
EditText ed1,ed3,ed4,ed5;
Button button;
TextView tt1,tt2,tt3,tt4;
 String s;
 DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ed1=(EditText)findViewById(R.id.e1);
        button=(Button)findViewById(R.id.e2);
        ed3=(EditText)findViewById(R.id.e3);
        ed4=(EditText)findViewById(R.id.e4);
        ed5=(EditText)findViewById(R.id.e5);
        tt1=(TextView)findViewById(R.id.t1);
        tt2=(TextView)findViewById(R.id.t2);
        tt3=(TextView)findViewById(R.id.t3);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Students");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s=ed1.getText().toString().trim();
                Query query=databaseReference.orderByChild("adno").equalTo(s);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot stu:dataSnapshot.getChildren())
                        {
                        StudentModel studentModel=stu.getValue(StudentModel.class);

                        String sname=studentModel.name;
                        String sroll=studentModel.roll;
                        String scol=studentModel.col;

                         ed3.setText(sname);
                         ed4.setText(sroll);
                         ed5.setText(scol);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                 tt1.setVisibility(View.VISIBLE);
                 tt2.setVisibility(View.VISIBLE);
                 tt3.setVisibility(View.VISIBLE);
                 ed3.setVisibility(View.VISIBLE);
                 ed4.setVisibility(View.VISIBLE);
                 ed5.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),s+" ",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
