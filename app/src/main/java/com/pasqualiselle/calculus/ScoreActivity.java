package com.pasqualiselle.calculus;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    public static final String PREFERENCES_ID = "CalculusPreference";
    public static final String PREF_KEY_LAST_STREAK = "LastStreak";
    public static final String PREF_KEY_LAST_SCORE = "LastScore";
    public static final String PREF_KEY_LAST_DURATION = "LastDuration";
    public static final String PREF_KEY_BEST_STREAK = "BestStreak";
    public static final String PREF_KEY_BEST_SCORE = "BestScore";
    public static final String PREF_KEY_BEST_DURATION = "BestDuration";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        mPreferences = getSharedPreferences(PREFERENCES_ID, MODE_PRIVATE);
        displayLastScore();
    }

    public void displayLastScore()
    {
        long lastScore = mPreferences.getLong(PREF_KEY_LAST_SCORE, 0);
        long lastStreak = mPreferences.getLong(PREF_KEY_LAST_STREAK, 0);
        long lastDuration = mPreferences.getLong(PREF_KEY_LAST_DURATION, 0);

        long bestScore = mPreferences.getLong(PREF_KEY_BEST_SCORE, 0);
        long bestStreak = mPreferences.getLong(PREF_KEY_BEST_STREAK, 0);
        long bestDuration = mPreferences.getLong(PREF_KEY_BEST_DURATION, 0);

        TextView lastScoreTextView = findViewById(R.id.lastScoreValue);
        lastScoreTextView.setText(String.valueOf(lastScore)+"points");
        TextView lastStreakTextView = findViewById(R.id.lastStreakValue);
        lastStreakTextView.setText(String.valueOf(lastStreak));
        TextView lastDurationTextView = findViewById(R.id.lastDurationValue);
        lastDurationTextView.setText(String.valueOf(lastDuration)+"ms");

        TextView bestScoreTextView = findViewById(R.id.bestScoreValue);
        bestScoreTextView.setText(String.valueOf(bestScore)+"points");
        TextView bestStreakTextView = findViewById(R.id.bestStreakValue);
        bestStreakTextView.setText(String.valueOf(bestStreak));
        TextView bestDurationTextView = findViewById(R.id.bestDurationValue);
        bestDurationTextView.setText(String.valueOf(bestDuration)+"ms");


    }
}
