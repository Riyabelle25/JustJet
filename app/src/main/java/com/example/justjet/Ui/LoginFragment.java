package com.example.justjet.Ui;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justjet.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;


public class LoginFragment extends Fragment implements View.OnClickListener {
    public LoginFragment() {
        // Required empty public constructor
    }
    FirebaseAuth firebaseAuth;
    final Bundle bundle = new Bundle();    private FirebaseUser user;
    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView signUp;
    private static LinearLayout loginLayout;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            // User is signed in

            NavOptions navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.loginFragment, true)
                    .build();
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment, bundle, navOptions);

            // No user is signed in
        }
        

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                          @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
            user = FirebaseAuth.getInstance().getCurrentUser();
        emailid = (EditText) view.findViewById(R.id.Lemail);
        password = (EditText) view.findViewById(R.id.Lpass);
        loginButton = (Button) view.findViewById(R.id.login);
        signUp = (TextView) view.findViewById(R.id.sign);
        loginLayout = (LinearLayout) view.findViewById(R.id.loginlay);
            setListeners();
    }

    private void setListeners() {
        loginButton.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:

                LoginUser();
                break;

            case R.id.sign:
                Toast.makeText(getActivity(), "Site under construction!", Toast.LENGTH_LONG).show();
                break;
        }
    }


    private void LoginUser() {

        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();


        if (getEmailId.equals("") || getEmailId.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {

            Toast.makeText(getActivity(), "Fill text entries please", Toast.LENGTH_LONG).show();
        }
        else {

            firebaseAuth.signInWithEmailAndPassword(getEmailId, getPassword).addOnCompleteListener((Activity) requireContext(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getActivity(), "Yay!", Toast.LENGTH_LONG).show();
                        NavOptions navOptions = new NavOptions.Builder()
                                .setPopUpTo(R.id.loginFragment, true)
                                .build();
                        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment, bundle, navOptions);
                    } else {
                        Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_LONG).show();
                    }

                }
            });

        }
    }


}
