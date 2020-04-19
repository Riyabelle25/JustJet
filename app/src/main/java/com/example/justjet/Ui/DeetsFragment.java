package com.example.justjet.Ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.justjet.R;


public class DeetsFragment extends Fragment {

    private static View view;
    private static TextView Name;
    private static TextView Class;


    public DeetsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_deets, container, false);

        Name=(TextView) view.findViewById(R.id.dname);

       Class=(TextView) view.findViewById(R.id.dclass);

        Name.setText(getArguments().getString("z"));
       Class.setText(getArguments().getString("z1"));



   return view;

    }





}
