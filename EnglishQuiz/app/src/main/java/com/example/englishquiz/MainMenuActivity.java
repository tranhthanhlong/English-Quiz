package com.example.englishquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainMenuActivity extends BaseActivity {

    private ActionBarDrawerToggle actionbardrawertoggle;
    private MaterialToolbar appbar;
    private DrawerLayout mainDrawer;
    private NavigationView navigationView;
    private CardView btnMCWithText, btnMCWithImage, btnCorrectWord, btnHowToUse;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        Hooks();

        setUpViews();

        btnMCWithText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SelectNumberOfTextQuestionActivity.class);
                startActivity(intent);
            }
        });

        btnMCWithImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, SelectNumberOfImageQuestionActivity.class);
                startActivity(intent);
            }
        });

        btnCorrectWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, StartCorrectWordActivity.class);
                startActivity(intent);
            }
        });

        btnHowToUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainMenuActivity.this, HowToUseActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Hooks() {
        appbar = findViewById(R.id.appBar);
        mainDrawer = findViewById(R.id.mainDrawer);
        navigationView = findViewById(R.id.navigationView);
        btnMCWithText = findViewById(R.id.btnMCWithText);
        btnMCWithImage = findViewById(R.id.btnMCWithImage);
        btnCorrectWord = findViewById(R.id.btnCorrectWord);
        btnHowToUse = findViewById(R.id.btnHowToUse);
    }

    public void setUpViews() {
        setUpDrawerLayout();
    }

    public void setUpDrawerLayout() {
        setSupportActionBar(appbar);
        actionbardrawertoggle = new ActionBarDrawerToggle(MainMenuActivity.this, mainDrawer, R.string.app_name, R.string.app_name);
        actionbardrawertoggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.myprofile:
                        intent = new Intent(MainMenuActivity.this, MyProfileActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.logout:
                        logout();
                        mainDrawer.closeDrawers();
                        break;
                    case R.id.leaderboard:
                        intent = new Intent(MainMenuActivity.this, SelectLeaderboardActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionbardrawertoggle.onOptionsItemSelected(item)) {
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainMenuActivity.this, LoginIntroActivity.class);
        startActivity(intent);
        finish();
    }
}