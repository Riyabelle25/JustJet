package com.example.justjet;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class dialog extends DialogFragment {
    private EditText editTextname;
    private EditText editTextClass;
    private EditText editTextId;
    private DatabaseReference DemoRef,Demoref1,Demoref2;
  //  private ExampleDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_xml, null);
        DemoRef= FirebaseDatabase.getInstance().getReference();
        builder.setView(view)
                .setTitle("Add new students")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String name = editTextname.getText().toString();
                        String Class = editTextClass.getText().toString();
                        String id = editTextId.getText().toString();

                        applyTexts(name, Class, id);
                    }
                });

        editTextname = view.findViewById(R.id.edit_name);
        editTextClass = view.findViewById(R.id.edit_class);
        editTextId = view.findViewById(R.id.edit_id);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }


  public  void applyTexts(String name, String Class,String Id){
      Log.i("r", "120");

      DemoRef.child("Students").push().child("Name").setValue(name);
      DemoRef.child("Students").push().child("Class").setValue(Class);
      DemoRef.child("Students").child("Id").setValue(Id);
  }



}
