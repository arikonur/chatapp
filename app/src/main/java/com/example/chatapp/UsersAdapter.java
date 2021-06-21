package com.example.chatapp;

import  android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chatapp.databinding.RowConservationBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class  UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder>{

    Context context;
    ArrayList<User> users;

    public UsersAdapter(Context context,ArrayList<User> users){
        this.context= context;
        this.users = users;

    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_conservation,parent,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UsersAdapter.UsersViewHolder holder, int position) {
        User user=users.get(position);

        holder.binding.username.setText(user.getName());

        Glide.with(context).load(user.getProfileImage())
                .placeholder(R.drawable.avatar)
                .into(holder.binding.profile);

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{

        RowConservationBinding binding;
        public UsersViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding=RowConservationBinding.bind(itemView);
        }
    }
}
