package com.example.englishquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class EnglishQuizResultActivity extends BaseActivity {

    private CircularProgressBar circularProgressBar;
    private TextView resultText, txtEmail, txtHighScore, btnReturn;
    private int correct,wrong;
    private int numberOfQuestions, score;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_englishquizresult);

        user = FirebaseAuth.getInstance().getCurrentUser();

        numberOfQuestions = getIntent().getIntExtra("numberOfQuestions",0);
        correct = getIntent().getIntExtra("correct",0);
        wrong = getIntent().getIntExtra("wrong",0);
        score = getIntent().getIntExtra("score", 0);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        resultText = findViewById(R.id.resultText);
        txtEmail = findViewById(R.id.txtEmail);
        txtHighScore = findViewById(R.id.txtHighScore);
        btnReturn = findViewById(R.id.btnReturn);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.result);
        mediaPlayer.start();

        double temp1 = numberOfQuestions;
        double temp2 = correct;
        double percent = ((temp2 / temp1) * 100);
        String email = user.getEmail();
        circularProgressBar.setProgress(correct);
        circularProgressBar.setProgressMax(numberOfQuestions);
        resultText.setText(String.format("%.0f", percent) + "%");
        txtEmail.setText(email);
        txtHighScore.setText("Your high scores: " + score);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                Intent intent = new Intent(EnglishQuizResultActivity.this, MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
