package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.room.Room;
import com.example.myapplication.dao.AppDatabase;
import com.example.myapplication.dao.User;
import com.example.myapplication.dao.UserDAO;

public class NumberReceiver extends BroadcastReceiver {
    public static final String NUMBER_EXTRA = "NUMBER_EXTRA";
    public static final String USER_NAME_EXTRA = "USER_NAME_EXTRA";

    private final UserDAO userDAO;

    public NumberReceiver(Context applicationContext) {
        userDAO = Room.databaseBuilder(applicationContext, AppDatabase.class, "person_db")
                .build()
                .userDao();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("NumberReceiver", "Received message");
        int number = intent.getIntExtra(NUMBER_EXTRA, 0);
        String user = intent.getStringExtra(USER_NAME_EXTRA);

        Log.d("USER: ",  user);
        Log.d("COUNTER: ",  "" + number);


        Thread otherThread = new Thread(() -> {

            User userDTO = new User();

            userDTO.setUid(12);
            userDTO.setNumber(2121);
            userDTO.setUserName("SIEMA");
            userDAO.insert(userDTO);


            Log.d("USERs: ",  userDAO.getAll().toString());
        });

        otherThread.start();
    }
}
