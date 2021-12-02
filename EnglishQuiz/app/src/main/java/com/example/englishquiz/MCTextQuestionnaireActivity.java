package com.example.englishquiz;

import static com.example.englishquiz.LoginIntroActivity.textList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;
import com.sasank.roundedhorizontalprogress.RoundedHorizontalProgressBar;

import java.util.Collections;
import java.util.List;

public class MCTextQuestionnaireActivity extends BaseActivity {

    CountDownTimer countDownTimer;
    RoundedHorizontalProgressBar progressBar;
    List<MCTextQuestions> allQuestionsList;
    MCTextQuestions mcTextQuestions;
    TextView question, txtNumberOfQuestions;
    TextView ansA, ansB, ansC, ansD;
    CardView cvA, cvB, cvC, cvD;
    MediaPlayer mediaPlayer;

    private FirebaseFirestore firestore;

    private int timerValue = 20;
    private int index = 0;
    private int correctCount = 0;
    private int wrongCount = 0;
    private int numberOfQuestions;
    private int score = 0;
    private int streak = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textquestionnaire);

        firestore = FirebaseFirestore.getInstance();

        Hooks();

        numberOfQuestions = getIntent().getIntExtra("numberOfQuestions", 0);

        allQuestionsList = textList;
        Collections.shuffle(allQuestionsList);
        mcTextQuestions = textList.get(index);

        cvA.setCardBackgroundColor(getResources().getColor(R.color.white));
        cvB.setCardBackgroundColor(getResources().getColor(R.color.white));
        cvC.setCardBackgroundColor(getResources().getColor(R.color.white));
        cvD.setCardBackgroundColor(getResources().getColor(R.color.white));

        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long l) {
                timerValue = timerValue - 1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                wrongCount++;
                if (index < numberOfQuestions-1)
                {
                    index++;
                    mcTextQuestions = textList.get(index);
                    countDownTimer.cancel();
                    resetColor();
                    setAllData();
                    enableButton();
                } else {
                    Result();
                }
            }
        }.start();

        setAllData();
    }

    private void setAllData() {
        question.setText(mcTextQuestions.getQuestion());
        ansA.setText(mcTextQuestions.getAnsA());
        ansB.setText(mcTextQuestions.getAnsB());
        ansC.setText(mcTextQuestions.getAnsC());
        ansD.setText(mcTextQuestions.getAnsD());
        txtNumberOfQuestions.setText((index+1)+"/"+numberOfQuestions);

        timerValue = 20;
        countDownTimer.cancel();
        countDownTimer.start();
    }

    private void Hooks() {
        progressBar = findViewById(R.id.quiz_timer);
        question = findViewById(R.id.txtQuestion);
        txtNumberOfQuestions = findViewById(R.id.txtNumberOfQuestions);
        ansA = findViewById(R.id.btnAnsA);
        ansB = findViewById(R.id.btnAnsB);
        ansC = findViewById(R.id.btnAnsC);
        ansD = findViewById(R.id.btnAnsD);

        cvA = findViewById(R.id.cvA);
        cvB = findViewById(R.id.cvB);
        cvC = findViewById(R.id.cvC);
        cvD = findViewById(R.id.cvD);

       mediaPlayer = MediaPlayer.create(this, R.raw.buttonclick);

    }

    private void Correct(CardView cardView) {
        mediaPlayer.start();
        cardView.setBackgroundColor(getResources().getColor(R.color.green));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                correctCount++;
                streak++;
                score += 1 * streak;
                if (index < numberOfQuestions-1)
                {
                    index++;
                    mcTextQuestions = textList.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                } else {
                    Result();
                }
            }
        }, 1500);
    }

    private void Wrong(CardView cardView) {
        mediaPlayer.start();
        cardView.setBackgroundColor(getResources().getColor(R.color.red));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                wrongCount++;
                streak = 0;
                if (index < numberOfQuestions-1)
                {
                    index++;
                    mcTextQuestions = textList.get(index);
                    resetColor();
                    setAllData();
                    enableButton();
                } else {
                    Result();
                }
            }
        }, 1500);
    }

    private void Result() {
        countDownTimer.cancel();;
        DocumentReference userDoc = firestore.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        WriteBatch batch = firestore.batch();
        batch.update(userDoc, "textscore", score);
        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(MCTextQuestionnaireActivity.this, EnglishQuizResultActivity.class);
                intent.putExtra("correct", correctCount);
                intent.putExtra("wrong", wrongCount);
                intent.putExtra("numberOfQuestions", numberOfQuestions);
                intent.putExtra("score", score);
                startActivity(intent);
                finish();
            }
        });
    }

    private void enableButton() {
        cvA.setClickable(true);
        cvB.setClickable(true);
        cvC.setClickable(true);
        cvD.setClickable(true);
    }

    private void disableButton() {
        cvA.setClickable(false);
        cvB.setClickable(false);
        cvC.setClickable(false);
        cvD.setClickable(false);
    }

    private void resetColor() {
        cvA.setBackgroundColor(getResources().getColor(R.color.white));
        cvB.setBackgroundColor(getResources().getColor(R.color.white));
        cvC.setBackgroundColor(getResources().getColor(R.color.white));
        cvD.setBackgroundColor(getResources().getColor(R.color.white));
    }

    public void OptionAClick(View view) {
        disableButton();
        if (mcTextQuestions.getAnsA().equals(mcTextQuestions.getAns()))
        {
            Correct(cvA);
        } else {
            Wrong(cvA);
        }
    }

    public void OptionBClick(View view) {
        disableButton();
        if (mcTextQuestions.getAnsB().equals(mcTextQuestions.getAns()))
        {
            Correct(cvB);
        } else {
            Wrong(cvB);
        }
    }

    public void OptionCClick(View view) {
        disableButton();
        if (mcTextQuestions.getAnsC().equals(mcTextQuestions.getAns()))
        {
            Correct(cvC);
        } else {
            Wrong(cvC);
        }
    }

    public void OptionDClick(View view) {
        disableButton();
        if (mcTextQuestions.getAnsD().equals(mcTextQuestions.getAns()))
        {
            Correct(cvD);
        } else {
            Wrong(cvD);
        }
    }
}