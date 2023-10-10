package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.FragmentBlankBinding;
import com.example.myapplication.fragmentmanager.BlankFragment;
import com.example.myapplication.fragmentmanager.registerForm;
import com.example.myapplication.interfaces.fragmentCommunication;
import com.example.myapplication.interfaces.mid;
import com.example.myapplication.modelclass.LoginViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.annotation.Target;

public class MainActivity extends AppCompatActivity implements mid {

    ActivityMainBinding mActivityMainBinding;
    public LoginViewModel loginViewModel;
    public static String TAG = "FIREBASE";
    private ValueEventListener mValueEventListener;
    private DatabaseReference mRef;
    String Tag  =  MainActivity.class.getName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Users");
        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        loginViewModel =  new ViewModelProvider(this).get(LoginViewModel.class);
        mActivityMainBinding.setLoginViewModel(loginViewModel);
        BlankFragment blankFragment  = new BlankFragment(this);

//        mActivityMainBinding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG,"clicked");
//
//                HashMap<String,Object> updateData = new HashMap<>();
//                updateData.put("/Users/86668902024/name","new");
//                mRef.updateChildren(updateData);
//
//            }
//        });
//        mValueEventListener  = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//              Map<String , Object> data  =  (Map<String, Object>) snapshot.getValue();
//              String name = data.get("name").toString();
//              mActivityMainBinding.editTextText2.setText(name);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };
//
//
//        mActivityMainBinding.SubmitData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//               String number = mActivityMainBinding.editTextText.getText().toString();
//                String name = mActivityMainBinding.editTextText2.getText().toString();
//                 Map<String,Object> data =  new HashMap<>() ;
//                 data.put("name",name);
//                mRef.child(number).setValue(data);
//                mActivityMainBinding.editTextText.setText("");
//                mActivityMainBinding.editTextText2.setText("");
//            }
//        });
//        mActivityMainBinding.readData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String number = mActivityMainBinding.editTextText.getText().toString();
//                mRef.child(number).addListenerForSingleValueEvent(mValueEventListener);
//            }
//        });
//    }

        loginViewModel.getPosition().observe(this , integer -> {
            Log.d(Tag,"getViewLifecycleOwner()");
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            registerForm registerFormfragment  =  new registerForm();

            if (integer == 2) {
                Log.d(Tag,"set id "+2);
                transaction.replace(R.id.fragmentLayout, registerFormfragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }

        });
    }


    /**
     * @param i
     */
    @Override
    public void setData(int i) {
        Log.d(Tag,"get mid");
    }
}
