package com.example.englishquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class SelectLeaderboardActivity extends AppCompatActivity {

    Button btnTextScore, btnImageScore, btnWordScore;
    public static int choice;
    public static ArrayList<User> userlist;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectleaderboard);

        btnTextScore = findViewById(R.id.btnTextScore);
        btnImageScore = findViewById(R.id.btnImageScore);
        btnWordScore = findViewById(R.id.btnWordScore);

        firestore = FirebaseFirestore.getInstance();
        userlist = new ArrayList<>();

        firestore.collection("Users")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            User user = document.toObject(User.class);
                            userlist.add(user);
                        }
                    }
                });

        btnTextScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = 1;
                Intent intent = new Intent(SelectLeaderboardActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        btnImageScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = 2;
                Intent intent = new Intent(SelectLeaderboardActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });

        btnWordScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choice = 3;
                Intent intent = new Intent(SelectLeaderboardActivity.this, LeaderboardActivity.class);
                startActivity(intent);
            }
        });
    }
}
