package com.example.blogapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
       mAuth = FirebaseAuth.getInstance();
       
      FirebaseUser user = mAuth.getCurrentUser();
      
      if(user == null){
          Intent i = new Intent(this, LoginActivity.class);
          startActivity(i);
      }else{
          Intent i = new Intent(this, HomeActivity.class);
          startActivity(i);
      }
       
    }
}