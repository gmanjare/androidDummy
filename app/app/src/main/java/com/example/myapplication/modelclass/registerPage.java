package com.example.myapplication.modelclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registerPage extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private String TAG = registerPage.class.getName().toString();

    /**
     *
     */
    EditText email_, password_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        email_ = findViewById(R.id.idEdtUser);
        password_ = findViewById(R.id.idEdtPass);
        Button register = findViewById(R.id.idRegi);
        mAuth = FirebaseAuth.getInstance();
// ...
// Initialize Firebase Auth
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_.getText().toString();
                String password = password_.getText().toString();
                setData(email, password);

            }
        });


    }

    public void setData(String email, String password) {
        Log.d(TAG, "email :" + email);
        Log.d(TAG, "password :" + password);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());


                        }
                    }
                });
    }

    @Override
    protected void onStart() {

        super.onStart();

    }


}