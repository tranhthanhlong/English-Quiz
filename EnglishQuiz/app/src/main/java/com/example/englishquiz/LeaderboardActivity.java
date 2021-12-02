package com.example.englishquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.englishquiz.SelectLeaderboardActivity.choice;
import static com.example.englishquiz.SelectLeaderboardActivity.userlist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardActivity extends BaseActivity {

    private Button btnReturn;
    private TextView txtTitleScore;
    private ListView listView;
    private ListAdapter listAdapter;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        btnReturn = findViewById(R.id.btnReturn3);
        txtTitleScore = findViewById(R.id.txtTitleScore);
        listView = findViewById(R.id.listView);

        if (choice == 1) {
            txtTitleScore.setText("Text Score");
            Collections.sort(userlist, User.textScoreDescending);
        } else if (choice == 2) {
            txtTitleScore.setText("Image Score");
            Collections.sort(userlist, User.imageScoreDescending);
        } else if (choice == 3) {
            txtTitleScore.setText("Word Score");
            Collections.sort(userlist, User.wordScoreDescending);
        }
        listAdapter = new ListAdapter(LeaderboardActivity.this, R.layout.list_item, userlist);
        listView.setAdapter(listAdapter);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaderboardActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}