package com.example.myapplication;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = (Button)findViewById(R.id.button2);
        TextView mEdit   = findViewById(R.id.textEdit);

        mButton.setOnClickListener( (event) -> {
            Intent intent = new Intent(MainActivity.this, UserActivity.class);

            intent.putExtra("name", mEdit.getText().toString());
            startActivity(intent);
        });
    }
}