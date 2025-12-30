package com.example.blogapp;

import android.content.Intent;
import android.os.Bundle;

import com.example.blogapp.adapter.HomeAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogapp.databinding.ActivityHomeBinding;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    
    List<BlogModel> blogList = new ArrayList<>();
    FirebaseFirestore firestore;
    FloatingActionButton fabButton;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.homeRecycler);
        homeAdapter = new HomeAdapter(blogList, this);
        recyclerView.setAdapter(homeAdapter);
        firestore = FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listenCollection();
        fabButton = findViewById(R.id.homeFabButton);
        
        fabButton.setOnClickListener( v ->{
            Intent i = new Intent(HomeActivity.this, AddBlog.class);
            startActivity(i);
        });
        
    }
    
    private void listenCollection(){
        firestore.collection("blogs").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    return;
                }
                blogList.clear();
                for(DocumentSnapshot doc : value.getDocuments()){
                    BlogModel blog = doc.toObject(BlogModel.class);
                    blogList.add(blog);
                }
                homeAdapter.notifyDataSetChanged();
            }
        });
    }
   
}