package com.example.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.room.Room;
import com.example.myapplication.dao.AppDatabase;

public class UserActivity extends AppCompatActivity {


    public static String NUMBER_RECEIVER_ACTION = "NUMBER_RECEIVER_ACTION";
    private IntentFilter filter = new IntentFilter(NUMBER_RECEIVER_ACTION);
    private NumberReceiver numberReceiver;

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(numberReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(numberReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        numberReceiver= new NumberReceiver(this.getApplicationContext());

        Intent intent = getIntent();
        TextView mEdit = findViewById(R.id.textView2);
        mEdit.setText(intent.getStringExtra("name"));

//        Service handling

        Button startButton = findViewById(R.id.startButton);
        Button stopButton = findViewById(R.id.stopButton);

        startButton.setOnClickListener( (event) -> {
            Intent serviceIntent = new Intent(this, UserService.class);
            startService(serviceIntent);
        });

        stopButton.setOnClickListener( (event) -> {
            Intent serviceIntent = new Intent(this, UserService.class);
            stopService(serviceIntent);
        });
    }
}