package com.example.englishquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class LoginIntroActivity extends AppCompatActivity {

    private Button btngetstarted;
    private FirebaseAuth mAuth;

    public static ArrayList<MCTextQuestions> textList;
    public static ArrayList<MCImageQuestions> imageList;
    public static ArrayList<String> Dictionary1;
    public static ArrayList<String> Dictionary2;
    public static ArrayList<String> Dictionary3;
    public static ArrayList<String> Dictionary4;
    public static ArrayList<String> Dictionary5;
    public static ArrayList<String> Dictionary6;
    public static ArrayList<String> Dictionary7;
    public static ArrayList<String> Dictionary8;
    public static ArrayList<String> Dictionary9;
    public static ArrayList<String> Dictionary10;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginintro);
        mAuth = FirebaseAuth.getInstance();
        DbQuery.firestore = FirebaseFirestore.getInstance();

        textList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("TextQuestion");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                textList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MCTextQuestions mcTextQuestions = dataSnapshot.getValue(MCTextQuestions.class);
                    textList.add(mcTextQuestions);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get text list failed", Toast.LENGTH_SHORT).show();
            }
        });


        imageList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("ImageQuestion");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imageList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MCImageQuestions mcImageQuestions = dataSnapshot.getValue(MCImageQuestions.class);
                    imageList.add(mcImageQuestions);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get image list failed", Toast.LENGTH_SHORT).show();
            }
        });

        Dictionary1 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CorrectWord1");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dictionary1.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Dictionary1.add(dataSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get dictionary failed", Toast.LENGTH_SHORT).show();
            }
        });

        Dictionary2 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CorrectWord2");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dictionary2.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Dictionary2.add(dataSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get dictionary failed", Toast.LENGTH_SHORT).show();
            }
        });

        Dictionary3 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CorrectWord3");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dictionary3.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Dictionary3.add(dataSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get dictionary failed", Toast.LENGTH_SHORT).show();
            }
        });

        Dictionary4 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CorrectWord4");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dictionary4.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Dictionary4.add(dataSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get dictionary failed", Toast.LENGTH_SHORT).show();
            }
        });

        Dictionary5 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CorrectWord5");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dictionary5.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Dictionary5.add(dataSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get dictionary failed", Toast.LENGTH_SHORT).show();
            }
        });

        Dictionary6 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CorrectWord6");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dictionary6.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Dictionary6.add(dataSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get dictionary failed", Toast.LENGTH_SHORT).show();
            }
        });

        Dictionary7 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CorrectWord7");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dictionary7.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Dictionary7.add(dataSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get dictionary failed", Toast.LENGTH_SHORT).show();
            }
        });

        Dictionary8 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CorrectWord8");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dictionary8.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Dictionary8.add(dataSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get dictionary failed", Toast.LENGTH_SHORT).show();
            }
        });

        Dictionary9 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CorrectWord9");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dictionary9.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Dictionary9.add(dataSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get dictionary failed", Toast.LENGTH_SHORT).show();
            }
        });

        Dictionary10 = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("CorrectWord10");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Dictionary10.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Dictionary10.add(dataSnapshot.getValue().toString());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginIntroActivity.this, "get dictionary failed", Toast.LENGTH_SHORT).show();
            }
        });

        btngetstarted = findViewById(R.id.btnGetStarted);
        btngetstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAuth.getCurrentUser() != null) {
                    Toast.makeText(getApplicationContext(), "User is already logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginIntroActivity.this, MainMenuActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(LoginIntroActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
