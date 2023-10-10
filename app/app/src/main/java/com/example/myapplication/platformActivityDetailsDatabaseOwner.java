package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.modelclass.LocationTrack;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.Manifest;

import java.util.List;

public class platformActivityDetailsDatabaseOwner extends AppCompatActivity {
    private DatabaseReference mRef;
    LocationManager locationManager;
    LocationListener locationListener;

    private static final int CAMERA_PERMISSION_CODE = 100;
    private static final int STORAGE_PERMISSION_CODE = 101;

    private static final int LOCATION_PERMISSION_CODE = 102;

    private static final int INTERNET_PERMISSION_CODE = 103;

    TextView  cordinate  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform_details_database_owner);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mRef = database.getReference("Users");
        cordinate =  findViewById(R.id.Gpstest);
      FirebaseAuth  mAuth = FirebaseAuth.getInstance();
        FirebaseUser user  =  mAuth.getCurrentUser();
        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION,102);
       LocationTrack locationTrack = new LocationTrack(this);


        if (locationTrack.canGetLocation()) {


            double longitude = locationTrack.getLongitude();
            double latitude = locationTrack.getLatitude();
            String latandlong =  ""+longitude+""+latitude;
            mRef.child("user1").child("name").setValue("ganesh");
            mRef.child("user1").child("seat").setValue(3);
            mRef.child("user1").child("x").setValue(latitude);
            mRef.child("user1").child("y").setValue(longitude);
            Toast.makeText(getApplicationContext(), "Longitude:" + Double.toString(longitude) + "\nLatitude:" + Double.toString(latitude), Toast.LENGTH_SHORT).show();
        } else {

            locationTrack.showSettingsAlert();
        }
    }

    public void checkPermission(String permission, int requestCode)
    {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] { permission }, requestCode);
        }
        else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     *
     */


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CAMERA_PERMISSION_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Showing the toast message
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == LOCATION_PERMISSION_CODE){
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "LOCATION Permission Granted", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "LOCATION Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    /**
     *
     */
    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}