package com.handelluss.tictactoegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button twoPlayersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
        twoPlayersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("Checking Listeners", "Second Listener has worked");
                Intent intent = new Intent(MainActivity.this, TwoPlayersActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initButtons(){
        twoPlayersButton = findViewById(R.id.twoPlayersButton);
    }
}