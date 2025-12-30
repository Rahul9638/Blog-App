package com.example.blogapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.blogapp.databinding.BlogItemBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddBlog extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore firestore;
    TextView blogTitleText , blogDescText, imgUrlText;
    
    BlogItemBinding dataBinding;
    Button addBlogButton;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_blog);
        mAuth= FirebaseAuth.getInstance();
        blogTitleText = findViewById(R.id.addBlogTitle);
        blogDescText = findViewById(R.id.addBlogDesc);
        imgUrlText = findViewById(R.id.addBlogImgUrl);
        addBlogButton = findViewById(R.id.addBlogSubmit);
       
        firestore = FirebaseFirestore.getInstance();
        addBlogButton.setOnClickListener(v->{
            String title = blogTitleText.getText().toString();
            String desc = blogDescText.getText().toString();
            String imgUrl = imgUrlText.getText().toString();
            addBlogRequested(title, desc, imgUrl);
        });
    }
    
    private void addBlogRequested(String title, String desc, String imgUrl){
        BlogModel model = new BlogModel(title, desc, imgUrl, mAuth.getUid());
                firestore
                .collection("blogs").
                add(model).
                addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Intent i = new Intent(AddBlog.this, HomeActivity.class);
                            startActivity(i);
                            Toast.makeText(AddBlog.this, "Blog Added successfully", Toast.LENGTH_SHORT).show();
                        }
                }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddBlog.this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
    
                        }
                });
        
    }
}