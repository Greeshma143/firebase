package com.arun.tasc.ui.notifications;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        final Button button1,button2,button3;
        final EditText editText1,editText3,editText4,editText5;
        final TextView textView1,textView2,textView3;

        final StudentModel studentModel;
        final DatabaseReference databaseReference;



        editText1=(EditText)root.findViewById(R.id.e1);
        editText3=(EditText)root.findViewById(R.id.e3);
        editText4=(EditText)root.findViewById(R.id.e4);
        editText5=(EditText)root.findViewById(R.id.e5);
        textView1=(TextView)root.findViewById(R.id.t1);
        textView2=(TextView)root.findViewById(R.id.t2);
        textView3=(TextView)root.findViewById(R.id.t3);
        button1=(Button)root.findViewById(R.id.e2);
        button2=(Button)root.findViewById(R.id.up);

        button3=(Button)root.findViewById(R.id.del);

        final String  s1;
        s1=editText1.getText().toString().trim();

        studentModel=new StudentModel();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Students");

        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {



                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Query query=databaseReference.orderByChild("adno").equalTo(s1);
                            query.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot del:dataSnapshot.getChildren())
                                    {
                                        del.getRef().removeValue();
                                        Toast.makeText(getContext(),"data succesfully deleted",Toast.LENGTH_SHORT).show();


                                    }
                                    textView1.setVisibility(View.INVISIBLE);
                                    textView2.setVisibility(View.INVISIBLE);
                                    textView3.setVisibility(View.INVISIBLE);
                                    editText5.setVisibility(View.INVISIBLE);
                                    editText3.setVisibility(View.INVISIBLE);
                                    editText4.setVisibility(View.INVISIBLE);



                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });

                        }
                    });
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            final String nwname,nwnroll,nwncoll;
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
                                    Toast.makeText(getActivity(),"data succesfully updated",Toast.LENGTH_SHORT).show();

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
                            String s1;
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
                            Toast.makeText(getActivity(),s1+" ",Toast.LENGTH_SHORT).show();

                        }
                    });
            }
        });
        return root;
    }
}