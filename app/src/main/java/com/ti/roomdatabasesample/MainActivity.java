package com.ti.roomdatabasesample;

import android.annotation.SuppressLint;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.et_name) EditText metname;
    @BindView(R.id.et_age) EditText metage;
    @BindView(R.id.et_des) EditText metdes;
    @BindView(R.id.btn_submit) Button mbutton;
    @BindView(R.id.btn_list) Button btn_list;
    AppDatabase db;
    String mname,mage,mdes;
    @OnClick (R.id.btn_submit) void submit(){
      mname = metname.getText().toString();
        mage = metage.getText().toString();
        mdes = metdes.getText().toString();
        adddatatodb(mname,mage,mdes,db);
    }
    @OnClick (R.id.btn_list) void viewlist(){
     startActivity(new Intent(MainActivity.this,ListActivity.class));
    }

    private void adddatatodb(String mname, String mage, String mdes,AppDatabase db) {
        User user = new User();
        user.setUsername(mname);
        user.setAge(mage);
        user.setDesignation(mdes);
        adduser(user);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        db = AppDatabase.getAppDatabase(MainActivity.this);
    }

    private void adduser(final User user){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                db.muserDao().Insertall(user);
                return null;
            }
        }.execute();
    }
}
