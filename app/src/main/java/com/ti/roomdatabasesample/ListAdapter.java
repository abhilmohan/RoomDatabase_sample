package com.ti.roomdatabasesample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.myViewHolder> {
    Context mcontext;
    List<User> muserModels;

    public ListAdapter(Context context, List<User> userModels) {
        this.mcontext = context;
        this.muserModels = userModels;
    }

    @NonNull
    @Override
    public ListAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View itemview = LayoutInflater.from(parent.getContext())
              .inflate(R.layout.list_item,parent,false);
        return new myViewHolder(itemview);

    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.myViewHolder holder, int position) {
        User userModel = muserModels.get(position);
        holder.muser.setText(userModel.getUsername());
        holder.mage.setText(userModel.getAge());
        holder.mdes.setText(userModel.getDesignation());
    }

    @Override
    public int getItemCount() {
        return muserModels.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView muser;
        TextView mage;
        TextView mdes;

        public myViewHolder(View itemView) {
            super(itemView);
            muser = (TextView)itemView.findViewById(R.id.tv_username);
            mage = (TextView)itemView.findViewById(R.id.tv_age);
            mdes = (TextView)itemView.findViewById(R.id.tv_des);
        }


    }
}
