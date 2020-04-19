package com.example.justjet.Models;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.justjet.Services.FirebaseQueryLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewModel extends androidx.lifecycle.ViewModel {
  //  private final LiveData< List<DataSnapshot>> liveData;
    private static final DatabaseReference dbref =
            FirebaseDatabase.getInstance().getReference().child("Students");



    private final FirebaseQueryLiveData liveData = new FirebaseQueryLiveData(dbref);



    @NonNull
    public LiveData< DataSnapshot> getDataSnapshotLiveData() {
        Log.i("r","27");

        return liveData;
    }
}
