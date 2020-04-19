package com.example.justjet.Ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.justjet.R;
import com.example.justjet.Models.ViewModel;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private ListView Listt;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    private static View view;
    private static Button Add;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        Listt = (ListView) view.findViewById(R.id.list);
        //final ArrayAdapter<String>  arrayAdapter= new ArrayAdapter<String>(requireActivity() ,android.R.layout.simple_list_item_1,arrayList);

        Add = (Button) view.findViewById(R.id.add);


        final ViewModel viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        liveData.observe(requireActivity(), new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable final DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    arrayList.clear();
                    arrayList1.clear();
                    Log.i("r", "61");

                    for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Log.i("r", "64");
                        if ((snapshot.hasChild("Name")) && (snapshot.hasChild("ID")) && snapshot.hasChild("Class")){

                            arrayList.add("NAME:" + snapshot.child("Name").getValue().toString() + " " + "ENROLL.NO:" + snapshot.child("ID").getValue().toString()
                            );
                            arrayList1.add(snapshot.child("Class").getValue().toString());
                        }
                        arrayAdapter = new ArrayAdapter<String>(requireActivity(), R.layout.list_item, R.id.name, arrayList);
                        Listt.setAdapter(arrayAdapter);



                        Listt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        final NavController navController = Navigation.findNavController(requireActivity(), R.id.my_nav_host_fragment);
        final Bundle bundle = new Bundle();
        bundle.putString("z", arrayList.get(position));

        bundle.putString("z1", "CLASS: " + arrayList1.get(position));
navController.navigate(R.id.action_homeFragment_to_deetsFragment, bundle);}
                        });
                    }
                }




            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        return view;
    }

    public void openDialog() {
         dialog exampleDialog = new dialog();
        exampleDialog.show(getParentFragmentManager(), "example com.example.justjet.Ui.dialog");
    }






}
