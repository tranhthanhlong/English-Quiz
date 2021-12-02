package com.example.englishquiz;

import static com.example.englishquiz.SelectLeaderboardActivity.choice;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class User {
    private String email;
    private int textscore;
    private int imagescore;
    private int wordscore;

    public User() {

    }

    public User(String email, int textscore, int imagescore, int wordscore) {
        this.email = email;
        this.textscore = textscore;
        this.imagescore = imagescore;
        this.wordscore = wordscore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTextscore() {
        return textscore;
    }

    public void setTextscore(int textscore) {
        this.textscore = textscore;
    }

    public int getImagescore() {
        return imagescore;
    }

    public void setImagescore(int imagescore) {
        this.imagescore = imagescore;
    }

    public int getWordscore() {
        return wordscore;
    }

    public void setWordscore(int wordscore) {
        this.wordscore = wordscore;
    }

    public static Comparator<User> textScoreDescending = new Comparator<User>()
    {
        @Override
        public int compare(User user1, User user2)
        {
            int score1 = user1.getTextscore();
            int score2 = user2.getTextscore();
            return Integer.compare(score2, score1);
        }
    };

    public static Comparator<User> imageScoreDescending = new Comparator<User>()
    {
        @Override
        public int compare(User user1, User user2)
        {
            int score1 = user1.getImagescore();
            int score2 = user2.getImagescore();
            return Integer.compare(score2, score1);
        }
    };

    public static Comparator<User> wordScoreDescending = new Comparator<User>()
    {
        @Override
        public int compare(User user1, User user2)
        {
            int score1 = user1.getWordscore();
            int score2 = user2.getWordscore();
            return Integer.compare(score2, score1);
        }
    };

    @Override
    public String toString() {
        String format = "%d";
        if (choice == 1)
            return String.format(format, this.wordscore);
        else if (choice == 2)
            return String.format(format, this.wordscore);
        else if (choice == 3)
            return String.format(format, this.wordscore);
        return "";
    }
}
