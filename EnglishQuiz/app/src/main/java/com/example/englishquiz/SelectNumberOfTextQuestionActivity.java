package com.example.englishquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class SelectNumberOfTextQuestionActivity extends BaseActivity {

    private MediaPlayer mediaPlayer;
    private Button btnStart;
    private Spinner Spin;
    private String Arr[]={"5","6","7","8","9","10"};
    private int pos;
    private int z=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectnumberofquestion);

        btnStart = findViewById(R.id.btnStart);
        Spin = findViewById(R.id.spinner);
        mediaPlayer = MediaPlayer.create(this, R.raw.start);

        createSpinner();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(SelectNumberOfTextQuestionActivity.this, MCTextQuestionnaireActivity.class);
                intent.putExtra("numberOfQuestions", numberOfQuestions());
                startActivity(intent);
                finish();
            }
        });
    }

    private void createSpinner()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item,Arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        Spin.setAdapter(adapter);
        Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pos=-1;
            }
        });
    }

    private int numberOfQuestions()
    {
        switch (pos)
        {
            case 0: z=5; break;
            case 1: z=6; break;
            case 2: z=7; break;
            case 3: z=8; break;
            case 4: z=9; break;
            case 5: z=10; break;
        }
        return z;
    }
}
