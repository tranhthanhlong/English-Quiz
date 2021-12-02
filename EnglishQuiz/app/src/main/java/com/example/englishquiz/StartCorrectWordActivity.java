package com.example.englishquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StartCorrectWordActivity extends AppCompatActivity {

    private TextView btnStart;
    private EditText edtLevel;
    private MediaPlayer mediaPlayer;
    private int chooseLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startcorrectword);

        btnStart = findViewById(R.id.btnStartCorrectWord);
        edtLevel = findViewById(R.id.edtLevel);
        mediaPlayer = MediaPlayer.create(this, R.raw.start);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseLevel = Integer.parseInt(edtLevel.getText().toString());
                if (chooseLevel < 1 || chooseLevel > 10) {
                    Toast.makeText(StartCorrectWordActivity.this, "Please input level from 1 to 10", Toast.LENGTH_SHORT).show();
                } else {
                    mediaPlayer.start();
                    Intent intent = new Intent(StartCorrectWordActivity.this, CorrectWordActivity.class);
                    intent.putExtra("chooseLevel", chooseLevel);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
