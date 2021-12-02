package com.example.englishquiz;

import android.os.Bundle;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyProfileActivity extends BaseActivity {

    private TextView txtMyEmail, txtMyTextScore, txtMyImageScore, txtMyWordScore;
    private FirebaseFirestore firestore;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        txtMyEmail = findViewById(R.id.txtMyEmail);
        txtMyTextScore = findViewById(R.id.txtMyTextScore);
        txtMyImageScore = findViewById(R.id.txtMyImageScore);
        txtMyWordScore = findViewById(R.id.txtMyWordScore);

        firestore = FirebaseFirestore.getInstance();

        DocumentReference userDoc = firestore.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        userDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);
                txtMyEmail.setText(user.getEmail());
                txtMyTextScore.setText("" + user.getTextscore());
                txtMyImageScore.setText("" + user.getImagescore());
                txtMyWordScore.setText("" + user.getWordscore());
            }
        });
    }
}
