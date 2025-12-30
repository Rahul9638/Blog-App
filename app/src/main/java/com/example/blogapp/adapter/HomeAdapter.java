package com.example.blogapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.blogapp.BlogModel;
import com.example.blogapp.R;
import com.example.blogapp.databinding.BlogItemBinding;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    
    private final List<BlogModel> modelList;
    private final Context context;

    public HomeAdapter(List<BlogModel> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.blog_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BlogModel blogModel = modelList.get(position);
        holder.itemBinding.setModel(blogModel);
        Glide.with(context)
                .load(blogModel.getImage())
                .placeholder(R.drawable.ic_launcher_background) // Optional
                .into(holder.itemBinding.blogImg);
    }
    @Override
    public int getItemCount() {
        if(modelList!= null){
            return  modelList.size();
        }
        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
       private final BlogItemBinding itemBinding;
       public MyViewHolder(BlogItemBinding itemBinding) {
           super(itemBinding.getRoot());
           this.itemBinding = itemBinding;
       }
   }
}
