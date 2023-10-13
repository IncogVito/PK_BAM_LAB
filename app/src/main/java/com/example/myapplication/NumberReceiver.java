package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.myapplication.dao.AppDatabase;

public class NumberReceiver extends BroadcastReceiver {
    public static final String NUMBER_EXTRA = "NUMBER_EXTRA";
    public static final String USER_NAME_EXTRA = "USER_NAME_EXTRA";

    public NumberReceiver() {
        Room.databaseBuilder(, AppDatabase.class, "person_db");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("NumberReceiver", "Received message");
        int number = intent.getIntExtra(NUMBER_EXTRA, 0);
        String user = intent.getStringExtra(USER_NAME_EXTRA);

        Log.d("USER: ",  user);
        Log.d("COUNTER: ",  "" + number);

    }
}
