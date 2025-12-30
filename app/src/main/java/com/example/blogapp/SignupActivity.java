package com.example.blogapp;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
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

public class SignupActivity extends AppCompatActivity {
    
    TextView emailText, passwordText;
    Button signupButton;
    ProgressBar progressBar;
    
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        // Initialising the Components
        emailText = findViewById(R.id.signupEmail);
        passwordText = findViewById(R.id.signupPassword);
        signupButton = findViewById(R.id.signupButton);
        progressBar = findViewById(R.id.signupProgressBar);
        progressBar.setVisibility(GONE);
        mAuth = FirebaseAuth.getInstance();
        
        
        signupButton.setOnClickListener( v ->{
            
            String email = emailText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailText.setError("Enter a valid email");
                return;
            }
            if (password.isEmpty()) {
                passwordText.setError("Password required");
                return;
            }
            emailText.setError(null);
            passwordText.setError(null);
            
            createAccountRequested(email, password);
            
        });
        
    }
    
    // Creating account on firebase. 

    private void createAccountRequested(String email, String password) {
        progressBar.setVisibility(VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Intent i = new Intent(SignupActivity.this, HomeActivity.class);
                startActivity(i);
            }else{
                Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                Toast.makeText(this, "Error : " + task.getException(), Toast.LENGTH_SHORT).show();
            }
            progressBar.setVisibility(GONE);
        });
    }
}