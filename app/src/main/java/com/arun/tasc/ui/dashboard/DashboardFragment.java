package com.arun.tasc.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.arun.tasc.R;
import com.arun.tasc.StudentModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
       final EditText e1,e2,e3,e4;
       final Button button;

        final StudentModel studentModel;

        final DatabaseReference databaseReference;


        e1=(EditText) root.findViewById(R.id.id3);
        e2=(EditText) root.findViewById(R.id.id4);
        e3=(EditText) root.findViewById(R.id.id5);
        e4=(EditText) root.findViewById(R.id.id6);
        button=(Button)root.findViewById(R.id.id7);
        studentModel=new StudentModel();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Students");

        dashboardViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                button.setOnClickListener(new View.OnClickListener()
                {
                @Override
                public void onClick(View view) {
                    final String nam,rol,ad,clge;
                    nam=e1.getText().toString().trim();
                    rol=e2.getText().toString().trim();
                    ad=e3.getText().toString().trim();
                    clge=e4.getText().toString().trim();


                    if(nam.isEmpty())
                    {
                        e1.setError("Name is required");
                        e1.requestFocus();
                    }
                    else if(rol.isEmpty())
                    {
                        e2.setError(" RollNo is required");
                        e2.requestFocus();
                    }
                    else if (ad.isEmpty())
                    {
                        e3.setError("Admission Number is required");
                        e3.requestFocus();
                    }
                    else if (clge.isEmpty())
                    {
                        e4.setError("College is required");
                        e4.requestFocus();
                    }

                    studentModel.setName(nam);
                    studentModel.setRoll(rol);
                    studentModel.setAdno(ad);
                    studentModel.setCol(clge);
                  final   String n,r,a,c;

                    n=studentModel.getName();
                    r=studentModel.getRoll();
                    a=studentModel.getAdno();
                    c=studentModel.getCol();



                    databaseReference.push().setValue(studentModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getActivity(),"success"+n+" "+r+" "+a+" "+c+" ",Toast.LENGTH_LONG).show();

                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Error",Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");



                }
            });


            }
        });
        return root;
    }
}