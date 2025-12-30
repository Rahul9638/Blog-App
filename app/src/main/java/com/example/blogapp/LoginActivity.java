package com.example.blogapp;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.example.blogapp.Utils.FormValidation.emailRegExp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    TextView email, password, createAccount;
    Button loginButton;
    ProgressBar progressBar;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailAddress);
        password = findViewById(R.id.passwordText);
        loginButton = findViewById(R.id.loginSubmit);
        progressBar = findViewById(R.id.progressBar);
        createAccount = findViewById(R.id.createAccount);
        
        mAuth = FirebaseAuth.getInstance();
        progressBar.setVisibility(GONE);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();
                
                if(!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()){
                    email.setError("Enter a valid email");
                     return;
                }
                if(passwordText.isEmpty()){
                    password.setError("Password required");
                    return;
                }
                email.setError(null);
                password.setError(null);
                userLoginRequested(emailText, passwordText);
            }
        });
        
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });
        
    }

    private void userLoginRequested(String email, String password) {
        progressBar.setVisibility(VISIBLE);
       mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
           if(task.isSuccessful()){
               Intent i = new Intent(LoginActivity.this, HomeActivity.class);
               startActivity(i);
           }else{
               Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
               Toast.makeText(this, "No user exist, Pls try creating account", Toast.LENGTH_SHORT).show();
           }
           progressBar.setVisibility(GONE);
       }) ;
    }

}