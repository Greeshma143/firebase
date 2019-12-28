package com.arun.tasc.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.squareup.picasso.Picasso;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final EditText ed1,ed3,ed4,ed5;
        final Button button;
        final TextView tt1,tt2,tt3;
        final String s;
        final ImageView im;
        final DatabaseReference databaseReference;
        ed1=(EditText)root.findViewById(R.id.e1);
        button=(Button)root.findViewById(R.id.e2);
        ed3=(EditText)root.findViewById(R.id.e3);
        ed4=(EditText)root.findViewById(R.id.e4);
        ed5=(EditText)root.findViewById(R.id.e5);
        tt1=(TextView)root.findViewById(R.id.t1);
        tt2=(TextView)root.findViewById(R.id.t2);
        tt3=(TextView)root.findViewById(R.id.t3);
        im=(ImageView) root.findViewById(R.id.imgv);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Students");

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String s;
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
                                String img=studentModel.propic;

                                ed3.setText(sname);
                                ed4.setText(sroll);
                                ed5.setText(scol);
                                Picasso.with(getActivity()).load(img).into(im);
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

                    Toast.makeText(getActivity(),s+" ",Toast.LENGTH_SHORT).show();

                }
            });



            }
        });
        return root;
    }
}