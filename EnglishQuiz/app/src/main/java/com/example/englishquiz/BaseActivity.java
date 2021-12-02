package com.example.englishquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.Nullable;

public class BaseActivity extends AppCompatActivity implements LogoutListener{


    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        ((MyApp) getApplication()).registerSessionListener(this);
        ((MyApp) getApplication()).startUserSession();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();

        ((MyApp) getApplication()).onUserInteracted();
    }

    @Override
    public void onSessionLogout() {
        finish();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(this, LoginIntroActivity.class);
        startActivity(intent);
    }
}
