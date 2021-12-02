package com.example.englishquiz;

import static com.example.englishquiz.SelectLeaderboardActivity.choice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<User> {

    private Context mContext;
    private int mResource;

    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<User> userArrayList) {
        super(context,resource,userArrayList);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView email = convertView.findViewById(R.id.textView_Email);
        TextView score = convertView.findViewById(R.id.textView_Score);

        email.setText(getItem(position).getEmail());
        if (choice == 1)
            score.setText("" + getItem(position).getTextscore());
        else if (choice == 2)
            score.setText("" + getItem(position).getImagescore());
        else if (choice == 3)
            score.setText("" + getItem(position).getWordscore());

        return convertView;
    }
}