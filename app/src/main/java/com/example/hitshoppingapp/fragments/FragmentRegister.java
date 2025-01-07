package com.example.hitshoppingapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.hitshoppingapp.R;
import com.example.hitshoppingapp.models.Item;
import com.example.hitshoppingapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentRegister extends Fragment {

    private FirebaseAuth mAuth;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentThree.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentRegister newInstance(String param1, String param2) {
        FragmentRegister fragment = new FragmentRegister();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private boolean success;
    public void funcReg(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Register Okay", Toast.LENGTH_LONG).show();
                            success = true;
                        } else {
                            Toast.makeText(getContext(), "Register Fail", Toast.LENGTH_LONG).show();
                            success = false;
                        }
                    }
                });
    }

    public void addData(String userName, String phone)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://hitshoppingapp-default-rtdb.firebaseio.com/");
        DatabaseReference myRef = database.getReference("users").child(phone);

        User user = new User(userName, phone);
        myRef.setValue(user);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance();

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        Button button3 = view.findViewById(R.id.buttonAfterRegister);
        TextView passwordTextView = view.findViewById(R.id.textRegisterPassword);
        TextView passwordAgainTextView = view.findViewById(R.id.textRegisterPasswordAgain);
        TextView phoneNumberTextView = view.findViewById(R.id.textRegisterPhone);
        TextView emailTextView = view.findViewById(R.id.textRegisterEmailAddress);

        button3.setOnClickListener(view1 -> {

            if(passwordTextView.getText().toString().length() < 6 ||
                    !passwordTextView.getText().toString().equals(passwordAgainTextView.getText().toString()) ||
                    emailTextView.getText().length() == 0 ||
                    phoneNumberTextView.getText().length() == 0
            ) return;

            funcReg(emailTextView.getText().toString(), passwordTextView.getText().toString());

            addData(emailTextView.getText().toString(), phoneNumberTextView.getText().toString());
            Navigation.findNavController(view1).navigate(R.id.action_fragmentRegister_to_fragmentLogin);
        });

        return view;
    }
}