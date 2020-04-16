package com.example.justjet;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;

import java.util.Locale;
import java.util.Objects;


public class HomeFragment extends Fragment{
    private TextView Name;
    private TextView ID;
    private static View view;
    private static Button Add;
    private FirebaseAuth mAuth;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        Name =(TextView) view.findViewById(R.id.name);
        ID = (TextView) view.findViewById(R.id.id);
        Add=(Button)view.findViewById(R.id.add);

        // Obtain a new or prior instance of HotStockViewModel from the
        // ViewModelProviders utility class.
        ViewModel viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        liveData.observe(requireActivity(), new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    // update the UI here with values in the snapshot
                    String ticker = dataSnapshot.child("1").child("Name").getValue(String.class);
                    Name.setText(ticker);
                    String price = dataSnapshot.child("iD").getValue(String.class);
                    if(price!=null)
                    ID.setText(price);
                    else
                    ID.setText("none entered!");
                }
            }
        });
        return view;
    }

}
