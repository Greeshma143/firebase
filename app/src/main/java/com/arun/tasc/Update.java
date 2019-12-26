package com.arun.tasc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Update extends AppCompatActivity {
Button button1,button2;
EditText editText1,editText3,editText4,editText5;
TextView textView1,textView2,textView3;
String  s1,nwname,nwncoll,nwnroll;
StudentModel studentModel;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        editText1=(EditText)findViewById(R.id.e1);
        editText3=(EditText)findViewById(R.id.e3);
        editText4=(EditText)findViewById(R.id.e4);
        editText5=(EditText)findViewById(R.id.e5);
        textView1=(TextView)findViewById(R.id.t1);
        textView2=(TextView)findViewById(R.id.t2);
        textView3=(TextView)findViewById(R.id.t3);
        button1=(Button)findViewById(R.id.e2);
        button2=(Button)findViewById(R.id.up);
        studentModel=new StudentModel();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Students");
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 nwname=editText3.getText().toString().trim();
                 nwnroll=editText4.getText().toString().trim();
                 nwncoll=editText5.getText().toString().trim();
                Query query=databaseReference.orderByChild("adno").equalTo(s1);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot stu:dataSnapshot.getChildren())
                        {
                            stu.getRef().child("name").setValue(nwname);
                            stu.getRef().child("roll").setValue(nwnroll);
                            stu.getRef().child("col").setValue(nwncoll);
                        }
                        Toast.makeText(getApplicationContext(),"data succesfully updated",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=editText1.getText().toString().trim();
                Query query=databaseReference.orderByChild("adno").equalTo(s1);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot stu:dataSnapshot.getChildren())
                        {
                            StudentModel studentModel=stu.getValue(StudentModel.class);

                            String sname=studentModel.name;
                            String sroll=studentModel.roll;
                            String scol=studentModel.col;

                            editText3.setText(sname);
                            editText4.setText(sroll);
                            editText5.setText(scol);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });




                textView1.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                editText3.setVisibility(View.VISIBLE);
                editText4.setVisibility(View.VISIBLE);
                editText5.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),s1+" ",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
