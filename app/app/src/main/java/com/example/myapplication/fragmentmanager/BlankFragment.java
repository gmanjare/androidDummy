package com.example.myapplication.fragmentmanager;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentBlankBinding;
import com.example.myapplication.interfaces.fragmentCommunication;
import com.example.myapplication.interfaces.mid;
import com.example.myapplication.modelclass.LoginViewModel;
import com.example.myapplication.modelclass.registerPage;
import com.example.myapplication.platformActivityDetailsDatabaseOwner;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    FragmentBlankBinding fragmentBlankBinding;

    String TAG = BlankFragment.class.getName().toString();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button mButton;
    LoginViewModel loginViewModel;
    mid m;
    fragmentCommunication listner;

    EditText mEmail, mPassword;

    private FirebaseAuth mAuth;

    public BlankFragment(fragmentCommunication listner) {
        // Required empty public constructor
        Log.d(TAG, "listner connected ");
        this.listner = listner;
    }

    public BlankFragment(mid l) {
        m = l;
    }

    public BlankFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        mEmail  = (EditText) getActivity().findViewById(R.id.idEdtUserName);
        mPassword = (EditText) getActivity().findViewById(R.id.idEdtPassword);
    }


    @Override
    public void onStart() {
        super.onStart();

        if (mAuth != null) {
            FirebaseUser user = mAuth.getCurrentUser();
            // Use currentUser as needed
            if (null != user) {
                Intent intent = new Intent(getActivity(), platformActivityDetailsDatabaseOwner.class);
                startActivity(intent);

            }
        } else {
            // Handle the case where FirebaseAuth is not properly initialized
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentBlankBinding.idRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), registerPage.class);
                startActivity(intent);

            }
        });
       fragmentBlankBinding.idBtnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String  email =  fragmentBlankBinding.idEdtUserName.getText().toString();
               String password  = fragmentBlankBinding.idEdtPassword.getText().toString();
               signIn(email, password);

           }
       });
       fragmentBlankBinding.idSkip.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent  intent  =  new Intent(getActivity() , platformActivityDetailsDatabaseOwner.class);
               startActivity(intent);
           }
       });

    }

    public void signIn(String  email ,String  password){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

// Example: Signing in with email and password
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Authentication was successful
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Handle the authenticated user
                            Intent intent =  new Intent(getActivity() , platformActivityDetailsDatabaseOwner.class);
                            startActivity(intent);
                        } else {
                            // Authentication failed
                            Exception exception = task.getException();
                            // Handle the failure, e.g., show an error message
                        }
                    }
                });


    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentBlankBinding = (FragmentBlankBinding) DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false);
        return fragmentBlankBinding.getRoot();
    }

    public BlankFragment(FragmentBlankBinding fragmentBlankBinding) {
        this.fragmentBlankBinding = fragmentBlankBinding;
    }

    public BlankFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    public void registerListner(fragmentCommunication listner) {
        Log.d(TAG, "register communication");
        this.listner = listner;
    }
}