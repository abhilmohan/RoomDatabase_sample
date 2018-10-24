package com.ti.roomdatabasesample;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity {
    @BindView(R.id.recycler) RecyclerView mRecyclerView;
    LinearLayoutManager manager;
    ListAdapter madapter;
    AppDatabase db;
    @BindView(R.id.btn_back) Button mback;
    @OnClick (R.id.btn_back) void setMback(){
        startActivity(new Intent(ListActivity.this,MainActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        db= AppDatabase.getAppDatabase(ListActivity.this);
        manager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(manager);
        getdatafromdb(db);
    }

    private void getdatafromdb(AppDatabase database){
        db.muserDao().getall().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                if (users.size() > 0){
                    madapter = new ListAdapter(getApplicationContext(),users);
                    mRecyclerView.setAdapter(madapter);
                    madapter.notifyDataSetChanged();
                }
            }
        });


    }
}
