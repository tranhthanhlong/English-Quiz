package com.example.englishquiz;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.englishquiz.SelectLeaderboardActivity.choice;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CorrectWordResultActivity extends AppCompatActivity {

    TextView txtTotalStar, txtMessage, btnReturn, btnLeaderboard;
    private MediaPlayer mediaPlayer;

    private int totalStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correctwordresult);

        totalStar = getIntent().getIntExtra("totalStar",0);

        txtTotalStar = findViewById(R.id.txtTotalStar);
        txtMessage = findViewById(R.id.txtMessage);
        btnReturn = findViewById(R.id.btnReturn2);
        btnLeaderboard = findViewById(R.id.btnLeaderboard2);

        txtTotalStar.setText("x " + totalStar);

        mediaPlayer = MediaPlayer.create(this, R.raw.result);
        mediaPlayer.start();

        if (totalStar == 0)
            txtMessage.setText("You need to study English again");
        else if (totalStar == 1)
            txtMessage.setText("You are on elementary level");
        else if (totalStar == 2)
            txtMessage.setText("You are on intermediate level");
        else if (totalStar == 3)
            txtMessage.setText("You are on advanced level");
        else if (totalStar > 3 && totalStar < 8)
            txtMessage.setText("You are on expert level");
        else if (totalStar >= 8)
            txtMessage.setText("You are on professional level");

        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choice = 3;
                mediaPlayer.stop();
                Intent intent = new Intent(CorrectWordResultActivity.this, LeaderboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                Intent intent = new Intent(CorrectWordResultActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

