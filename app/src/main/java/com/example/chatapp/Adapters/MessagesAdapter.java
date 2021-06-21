package com.example.chatapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.Models.Message;
import com.example.chatapp.R;
import com.example.chatapp.databinding.ItemReceiveBinding;
import com.example.chatapp.databinding.ItemSentBinding;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class  MessagesAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Message> messages;

    final int ITEM_SENT=1;
    final int ITEM_RECEIVE=2;


    public MessagesAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages= messages;


    }

    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SENT){
            View view = LayoutInflater.from(context).inflate(R.layout.item_sent,parent,false);
            return new SentViewHolder(view);

        }else {
            View view =LayoutInflater.from(context).inflate(R.layout.item_receive,parent , false);
            return new recieverViewHolder(view);
        }


    }

    @Override
    public int getItemViewType(int position) {

        Message message= messages.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderId())) {
            return ITEM_SENT;
        }else {
            return ITEM_RECEIVE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);


        if (holder.getClass() ==SentViewHolder.class){
            SentViewHolder viewHolder =(SentViewHolder)holder;
            viewHolder.binding.message.setText(message.getMessage());
            
        }else {
            recieverViewHolder viewHolder =(recieverViewHolder) holder;
            viewHolder.binding.message.setText(message.getMessage());

        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class SentViewHolder extends RecyclerView.ViewHolder {

        ItemSentBinding binding;
        public SentViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding=ItemSentBinding.bind(itemView);
        }
    }

    public class recieverViewHolder extends  RecyclerView.ViewHolder{


        ItemReceiveBinding binding;

        public recieverViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding=ItemReceiveBinding.bind(itemView);
        }
    }

}
