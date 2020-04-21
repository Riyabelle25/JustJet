package com.example.justjet.Ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.justjet.Adapter.MyAdapter;
import com.example.justjet.Models.Student;
import com.example.justjet.R;
import com.example.justjet.Models.ViewModel;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;
import com.example.justjet.Models.Student;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter mAdapter;

    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayList<String> arrayList2 = new ArrayList<>();
    List<Student> students=new ArrayList<>();


    private static View view;
    private static Button Add;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.list);

        Add = (Button) view.findViewById(R.id.add);


        final ViewModel viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        LiveData<DataSnapshot> liveData = viewModel.getDataSnapshotLiveData();

        liveData.observe(requireActivity(), new Observer<DataSnapshot>() {
            @Override
            public void onChanged(@Nullable final DataSnapshot dataSnapshot) {
                if (dataSnapshot != null) {
                    arrayList.clear();
                    arrayList1.clear();
                    students.clear();
                    arrayList2.clear();

                    Log.i("r", "61");

                    for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        if ((snapshot.hasChild("Name")) || (snapshot.hasChild("Id")) || snapshot.hasChild("Class")){
                            if ((snapshot.hasChild("Name"))){
                                Student student = new Student("NAME:" + snapshot.child("Name").getValue().toString());
                                students.add(student);
                                arrayList.add("NAME:" + snapshot.child("Name").getValue().toString());
                            }

                            if((snapshot.hasChild("Class"))){
                                arrayList1.add(snapshot.child("Class").getValue().toString());
                            }

                            if(snapshot.hasChild("Id")){
                                arrayList2.add("ENROLL.NO:" + snapshot.child("Id").getValue().toString());
                            }
                        }
                    }
                    mAdapter = new MyAdapter(students);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(mAdapter);
                    mAdapter.setOnItemClickListener(onItemClickListener);


                    Log.i("hi","90");
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

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();  Log.i("hi","13");


            final NavController navController = Navigation.findNavController(requireActivity(), R.id.my_nav_host_fragment);

            final Bundle bundle = new Bundle();
            bundle.putString("z", arrayList.get(position));
            bundle.putString("z1", "CLASS: " + arrayList1.get(position));
            bundle.putString("z2", arrayList2.get(position));
            navController.navigate(R.id.action_homeFragment_to_deetsFragment, bundle);

            Log.i("hi","130");
        }



    };


}
