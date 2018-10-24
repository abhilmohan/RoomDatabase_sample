package com.ti.roomdatabasesample;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface userDao {

    @Insert
    Long Insertall(User user);

    @Query("SELECT * FROM user WHERE username LIKE :username AND age LIKE :age AND designation LIKE :des")
    User findByName(String username, String age, String des);

    @Query("SELECT * FROM user")
    LiveData<List<User>> getall();

    @Delete
    void Delete (User user);

}
