package com.example.myapplication.modelclass;

import android.icu.text.Transliterator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel  extends ViewModel {
   public String Submit  = "Post Data";

   public String readdata = "Read Data";
  public  MutableLiveData<Integer> position =  new MutableLiveData<Integer>();
  public LiveData<Integer>  getPosition(){return  position;}
}
