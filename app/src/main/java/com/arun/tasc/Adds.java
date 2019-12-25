package com.arun.tasc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Adds extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button button;
    String nam,rol,ad,clge;
    StudentModel studentModel;
    String n,r,a,c;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adds);

        e1=(EditText) findViewById(R.id.id3);
        e2=(EditText) findViewById(R.id.id4);
        e3=(EditText) findViewById(R.id.id5);
        e4=(EditText) findViewById(R.id.id6);
        button=(Button)findViewById(R.id.id7);
        studentModel=new StudentModel();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Students");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nam=e1.getText().toString().trim();
                rol=e2.getText().toString().trim();
                ad=e3.getText().toString().trim();
                clge=e4.getText().toString().trim();

                studentModel.setName(nam);
                studentModel.setRoll(rol);
                studentModel.setAdno(ad);
                studentModel.setCol(clge);

                n=studentModel.getName();
                r=studentModel.getRoll();
                a=studentModel.getAdno();
                c=studentModel.getCol();



                databaseReference.push().setValue(studentModel);
                Toast.makeText(getApplicationContext(),"success"+n+" "+r+" "+a+" "+c+" ",Toast.LENGTH_LONG).show();

                e1.setText("");
                e2.setText("");
                e3.setText("");
                e4.setText("");



            }
        });

    }
}
