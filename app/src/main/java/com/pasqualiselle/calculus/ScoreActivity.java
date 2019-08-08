package com.pasqualiselle.calculus;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> arrayList = new ArrayList<>();

    private SharedPreferences mPreferences;

    public static final String PREFERENCES_ID = "CalculusPreference";
    public static final String PREF_KEY_LAST_STREAK = "LastStreak";
    public static final String PREF_KEY_LAST_SCORE = "LastScore";
    public static final String PREF_KEY_LAST_DURATION = "LastDuration";
    public static final String PREF_KEY_LAST_SPEED = "LastSpeed";
    public static final String PREF_KEY_BEST_STREAK = "BestStreak";
    public static final String PREF_KEY_BEST_SCORE = "BestScore";
    public static final String PREF_KEY_BEST_DURATION = "BestDuration";
    public static final String PREF_KEY_LONGEST_STREAK= "LongestStreak";
    public static final String PREF_KEY_FASTEST = "Fastest";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        mPreferences = getSharedPreferences(PREFERENCES_ID, MODE_PRIVATE);
        listView = findViewById(R.id.listview);
        displayLastScoreListView();
        setTitle("SCORE");


    }

    public void displayLastScore()
    {
        long lastScore = mPreferences.getLong(PREF_KEY_LAST_SCORE, 0);
        long lastStreak = mPreferences.getLong(PREF_KEY_LAST_STREAK, 0);
        long lastDuration = mPreferences.getLong(PREF_KEY_LAST_DURATION, 0);
        float lastSpeed = mPreferences.getFloat(PREF_KEY_LAST_SPEED, 0);

        long bestScore = mPreferences.getLong(PREF_KEY_BEST_SCORE, 0);
        long bestStreak = mPreferences.getLong(PREF_KEY_BEST_STREAK, 0);
        long bestDuration = mPreferences.getLong(PREF_KEY_BEST_DURATION, 0);
        long longestStreak = mPreferences.getLong(PREF_KEY_LONGEST_STREAK, 0);
        float fastest = mPreferences.getFloat(PREF_KEY_FASTEST, 0);

        TextView lastScoreTextView = findViewById(R.id.lastScoreValue);
        lastScoreTextView.setText(String.valueOf(lastScore)+" points");
        TextView lastStreakTextView = findViewById(R.id.lastStreakValue);
        lastStreakTextView.setText(String.valueOf(lastStreak));
        TextView lastDurationTextView = findViewById(R.id.lastDurationValue);
        lastDurationTextView.setText(String.valueOf(lastDuration)+" ms");
        TextView lastSpeedTextView = findViewById(R.id.lastSpeedValue);
        lastSpeedTextView.setText(String.format("%.03f",lastSpeed)+" points/sec");

        TextView bestScoreTextView = findViewById(R.id.bestScoreValue);
        bestScoreTextView.setText(String.valueOf(bestScore)+" points");
        TextView bestStreakTextView = findViewById(R.id.bestStreakValue);
        bestStreakTextView.setText(String.valueOf(bestStreak));
        TextView bestDurationTextView = findViewById(R.id.bestDurationValue);
        bestDurationTextView.setText(String.valueOf(bestDuration)+" ms");
        TextView longestStreakTextView = findViewById(R.id.longestStreakValue);
        longestStreakTextView.setText(String.valueOf(longestStreak)+" points");
        TextView fastestTextView = findViewById(R.id.fastestValue);
        fastestTextView.setText(String.format("%.03f",fastest)+" points/sec");



    }

    public void displayLastScoreListView()
    {
        long lastScore = mPreferences.getLong(PREF_KEY_LAST_SCORE, 0);
        long lastStreak = mPreferences.getLong(PREF_KEY_LAST_STREAK, 0);
        long lastDuration = mPreferences.getLong(PREF_KEY_LAST_DURATION, 0);
        float lastSpeed = mPreferences.getFloat(PREF_KEY_LAST_SPEED, 0);

        long bestScore = mPreferences.getLong(PREF_KEY_BEST_SCORE, 0);
        long bestStreak = mPreferences.getLong(PREF_KEY_BEST_STREAK, 0);
        long bestDuration = mPreferences.getLong(PREF_KEY_BEST_DURATION, 0);
        long longestStreak = mPreferences.getLong(PREF_KEY_LONGEST_STREAK, 0);
        float fastest = mPreferences.getFloat(PREF_KEY_FASTEST, 0);

        arrayList.add("last score : "+lastScore+" points");
        arrayList.add("last streak : "+(lastStreak));
        arrayList.add("last duration : "+lastDuration + " ms");
        arrayList.add("last speed : "+String.format("%.03f",lastSpeed)+" points/sec");
        arrayList.add("best score : "+(bestScore)+" points");
        arrayList.add("best streak : "+(bestStreak));
        arrayList.add("best duration : "+(bestDuration)+" ms");
        arrayList.add("longest streak : "+(longestStreak)+" points");
        arrayList.add("fastest speed : "+String.format("%.03f",fastest)+" points/sec");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

    }

}
