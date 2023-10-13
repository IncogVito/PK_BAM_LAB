package com.example.myapplication;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

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