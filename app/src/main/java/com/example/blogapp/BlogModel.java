package com.example.blogapp;

import androidx.databinding.BaseObservable;

import java.util.Observable;

public class BlogModel extends BaseObservable {
     private String title;
     private String description;
     private String image;
     
     private String uuid;

    public String getTitle() {
        return title;
    }
    
    public BlogModel(){
        
    }

    public BlogModel(String title, String description, String image, String uuid) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.uuid = uuid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
        
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
