package com.example.englishquiz;

import static com.example.englishquiz.LoginIntroActivity.Dictionary1;
import static com.example.englishquiz.LoginIntroActivity.Dictionary2;
import static com.example.englishquiz.LoginIntroActivity.Dictionary3;
import static com.example.englishquiz.LoginIntroActivity.Dictionary4;
import static com.example.englishquiz.LoginIntroActivity.Dictionary5;
import static com.example.englishquiz.LoginIntroActivity.Dictionary6;
import static com.example.englishquiz.LoginIntroActivity.Dictionary7;
import static com.example.englishquiz.LoginIntroActivity.Dictionary8;
import static com.example.englishquiz.LoginIntroActivity.Dictionary9;
import static com.example.englishquiz.LoginIntroActivity.Dictionary10;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.WriteBatch;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CorrectWordActivity extends BaseActivity {

    TextView txtInfo, txtWord, btnCheck, txtTimer, txtStreak, txtCount, txtStarCount;
    ImageView fire1, fire2;
    EditText etWord;
    TextView txtStar1, txtStar2, txtStar3;
    CountDownTimer countDownTimer;
    String currentWord;

    private FirebaseFirestore firestore;

    private int timerValue = 30;
    private int index = 0;
    private int star = 0;
    private int correctStreak = 0;
    private int wrongStreak = 0;
    private int wrongCount = 0;
    private int totalStar = 0;
    private int currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correctword);

        firestore = FirebaseFirestore.getInstance();

        currentLevel = getIntent().getIntExtra("chooseLevel", 1);

        Hooks();

        checkDifficulty();

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etWord.getText().toString().equalsIgnoreCase(currentWord))
                {
                    Correct();
                }
                else {
                    txtInfo.setText("Try again");
                    etWord.setText("");
                }
            }
        });

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timerValue = timerValue - 1;
                txtTimer.setText("00:" + timerValue);
            }

            @Override
            public void onFinish() {
                correctStreak = 0;
                wrongStreak++;
                wrongCount++;
                currentLevel--;
                checkWrongStreak();
                checkDifficulty();
                if ((wrongCount < 3) && (star >= 0))
                {
                    setAllData();
                } else if ((wrongCount == 3) && (star == 0)){
                    Result();
                }
            }
        }.start();

        setAllData();
    }

    private void Hooks() {
        txtInfo = findViewById(R.id.txtInfo);
        txtWord = findViewById(R.id.txtWord);
        txtTimer = findViewById(R.id.txtTimer);
        txtStreak = findViewById(R.id.txtStreak);
        etWord = findViewById(R.id.etWord);

        btnCheck = findViewById(R.id.btnCheck);
        txtStar1 = findViewById(R.id.txtStar1);
        txtStar2 = findViewById(R.id.txtStar2);
        txtStar3 = findViewById(R.id.txtStar3);
        txtStarCount = findViewById(R.id.txtStarCount);
        fire1 = findViewById(R.id.fire1);
        fire2 = findViewById(R.id.fire2);
    }

    private void Result() {
        countDownTimer.cancel();
        DocumentReference userDoc = firestore.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        WriteBatch batch = firestore.batch();
        batch.update(userDoc, "wordscore", totalStar);
        batch.commit().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(CorrectWordActivity.this, CorrectWordResultActivity.class);
                intent.putExtra("totalStar", totalStar);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setAllData() {
        txtWord.setText(shuffleWord(currentWord));
        etWord.setText("");
        txtInfo.setText("Guess the word");
        txtStarCount.setText("x " + star);
        setStreak();
        timerValue = 30;
        countDownTimer.cancel();
        countDownTimer.start();
    }

    private void Correct() {
        correctStreak++;
        wrongStreak = 0;
        wrongCount = 0;
        currentLevel++;
        checkCorrectStreak();
        checkDifficulty();
        setAllData();
    }

    private void checkCorrectStreak() {
        if (mod(correctStreak, 3) == 0 && correctStreak != 0)
        {
            if (star < 99)
                star++;
            if (star == 1)
                txtStar1.setVisibility(View.VISIBLE);
            if (star == 2)
                txtStar2.setVisibility(View.VISIBLE);
            if (star == 3)
                txtStar3.setVisibility(View.VISIBLE);
        }
    }

    private void checkWrongStreak() {
        if (mod(wrongStreak, 3) == 0 && wrongStreak != 0)
        {
            if (star == 1)
                txtStar1.setVisibility(View.INVISIBLE);
            if (star == 2)
                txtStar2.setVisibility(View.INVISIBLE);
            if (star == 3)
                txtStar3.setVisibility(View.INVISIBLE);
            if (star > 0) {
                if (totalStar <= star)
                {
                    totalStar = star;
                }
                star--;
                wrongCount = 0;
            }
        }
    }

    private void checkDifficulty() {
        if (currentLevel < 1) {
            currentLevel = 1;
        } else if (currentLevel > 10) {
            currentLevel = 10;
        }
        if (currentLevel == 1) {
            Collections.shuffle(Dictionary1);
            currentWord = Dictionary1.get(index);
        } else if (currentLevel == 2) {
            Collections.shuffle(Dictionary2);
            currentWord = Dictionary2.get(index);
        } else if (currentLevel == 3) {
            Collections.shuffle(Dictionary3);
            currentWord = Dictionary3.get(index);
        } else if (currentLevel == 4) {
            Collections.shuffle(Dictionary4);
            currentWord = Dictionary4.get(index);
        } else if (currentLevel == 5) {
            Collections.shuffle(Dictionary5);
            currentWord = Dictionary5.get(index);
        } else if (currentLevel == 6) {
            Collections.shuffle(Dictionary6);
            currentWord = Dictionary6.get(index);
        } else if (currentLevel == 7) {
            Collections.shuffle(Dictionary7);
            currentWord = Dictionary7.get(index);
        } else if (currentLevel == 8) {
            Collections.shuffle(Dictionary8);
            currentWord = Dictionary8.get(index);
        } else if (currentLevel == 9) {
            Collections.shuffle(Dictionary9);
            currentWord = Dictionary9.get(index);
        } else if (currentLevel == 10) {
            Collections.shuffle(Dictionary10);
            currentWord = Dictionary10.get(index);
        }
    }

    private String shuffleWord(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String shuffled = "";
        for (String letter : letters)
        {
            shuffled += letter + " ";
        }
        return shuffled;
    }

    private int mod(int x, int y) {
        int result = x % y;
        if (result < 0)
            result += y;
        return result;
    }

    private void setStreak() {
        if (correctStreak == 0) {
            txtStreak.setText(correctStreak + "-Streak");
        }
        if (correctStreak > 0)
        {
            fire1.setVisibility(View.VISIBLE);
            fire2.setVisibility(View.INVISIBLE);
            txtStreak.setText(correctStreak + "-Streak");
        } else if (wrongStreak > 0) {
            fire1.setVisibility(View.INVISIBLE);
            fire2.setVisibility(View.VISIBLE);
            txtStreak.setText(wrongStreak + "-Streak");
        }
    }
}