package com.example.justjet;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private FirebaseUser mUser;
    private static EditText emailid, password;
    private static Button loginButton;
    private static TextView signUp;
    private static LinearLayout loginLayout;


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
        final Bundle bundle = new Bundle();
        bundle.putBoolean("test_boolean", true);

        final NavController navController = Navigation.findNavController(requireActivity(), R.id.my_nav_host_fragment);
        firebaseAuth = FirebaseAuth.getInstance();
            mUser = FirebaseAuth.getInstance().getCurrentUser();
        emailid = (EditText) view.findViewById(R.id.Lemail);
        password = (EditText) view.findViewById(R.id.Lpass);
        loginButton = (Button) view.findViewById(R.id.login);
        signUp = (TextView) view.findViewById(R.id.sign);
        loginLayout = (LinearLayout) view.findViewById(R.id.loginlay);
            setListeners();
    }



    private void initViews() {


    }

    private void setListeners() {
        loginButton.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login:
                checkValidation();
                break;

            case R.id.sign:
                final NavController navController = Navigation.findNavController(requireActivity(), R.id.my_nav_host_fragment);
                final Bundle bundle = new Bundle();
                navController.navigate(R.id.action_loginFragment_to_signupFragment, bundle);
                break;
        }
    }
    private void checkValidation() {
        LoginUser();
    }

    private void LoginUser() {

        String getEmailId = emailid.getText().toString();
        String getPassword = password.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(getEmailId, getPassword).addOnCompleteListener((Activity) requireContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {   Toast.makeText(getActivity(), "Yay!", Toast.LENGTH_LONG).show();
                    final NavController navController = Navigation.findNavController(requireActivity(), R.id.my_nav_host_fragment);
                    final Bundle bundle = new Bundle();
                    navController.navigate(R.id.action_loginFragment_to_homeFragment, bundle);

                }
                else {
                    Toast.makeText(getActivity(), "Login Error", Toast.LENGTH_LONG).show();
                }

            }
        });

    }



}
