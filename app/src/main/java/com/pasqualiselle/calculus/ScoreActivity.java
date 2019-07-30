package com.pasqualiselle.calculus;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    public static final String PREFERENCES_ID = "CalculusPreference";
    public static final String PREF_KEY_LASTSCORE = "LastScore";
    public static final String PREF_KEY_BESTSCORE = "BestScore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        mPreferences = getSharedPreferences(PREFERENCES_ID, MODE_PRIVATE);
        displayLastScore();
    }

    public void displayLastScore()
    {
        int lastScore = mPreferences.getInt(PREF_KEY_LASTSCORE, 0);
        TextView lastScoreTextView = findViewById(R.id.lastScoreValue);
        lastScoreTextView.setText(String.valueOf(lastScore));

        int bestScore = mPreferences.getInt(PREF_KEY_BESTSCORE, 0);
        TextView bestScoreTextView = findViewById(R.id.bestScoreValue);
        bestScoreTextView.setText(String.valueOf(bestScore));
    }
}
