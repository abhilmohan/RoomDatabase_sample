package com.ti.roomdatabasesample;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase Instance;
    public abstract userDao muserDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (Instance == null) {
            Instance =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return Instance;
    }

    public static void destroyInstance() {
        Instance = null;
    }

}
