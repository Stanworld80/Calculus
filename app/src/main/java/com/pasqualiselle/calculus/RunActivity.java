package com.pasqualiselle.calculus;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.pasqualiselle.calculus.ScoreActivity.PREF_KEY_LAST_SCORE;
import static java.lang.Math.pow;
import static java.lang.Math.random;

public class RunActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;

    int mN1 = 0;
    int mN2 = 0;
    int mGoodAnswer = 0;
    int mLimitMax = 5;
    int mStreak = 0;
    long mStartTime = 0;
    long mDuration = 0;
    long mCurrentScore = 0;
    float mSpeed = 0;
    EditText answerTextView;
    TextView lastScoreTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        answerTextView = findViewById(R.id.answer_editText);
        lastScoreTextView = findViewById(R.id.lastpointsTextView);
        mPreferences = getSharedPreferences(ScoreActivity.PREFERENCES_ID, MODE_PRIVATE);

        prepareQuestion();
        prepareAnswerBtn();
        mStartTime = System.currentTimeMillis();
    }

    protected void prepareQuestion() {
        int prevN1 = mN1;
        int prevN2 = mN2;
        while (prevN1 == mN1 || prevN2 == mN2) {
            mN1 = (int) (random() * (mLimitMax + 1));
            mN2 = (int) (random() * (mLimitMax + 1));
        }
        mGoodAnswer = mN1 * mN2;

        TextView n1view = findViewById(R.id.N1_textView);
        n1view.setText(String.valueOf(mN1));
        TextView n2view = findViewById(R.id.N2_textView);
        n2view.setText(String.valueOf(mN2));
        answerTextView.setText("");

    }

    protected void prepareAnswerBtn() {

        answerTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView textView, int actId, KeyEvent keyEvent) {
                if (actId == EditorInfo.IME_ACTION_SEND) {
                    String userAnswerStr = textView.getText().toString();
                    boolean isUserCorrect = false;
                    int userAnswer = 0;
                    if (userAnswerStr.length() > 0) {
                        userAnswer = Integer.parseInt(userAnswerStr);
                        isUserCorrect = (mGoodAnswer == userAnswer);
                    }
                    if (isUserCorrect) {
                        mStreak++;
                        prepareQuestion();
                        lastScoreTextView.setText(mStreak+" Streaks!");
                    } else {
                        mDuration = System.currentTimeMillis() - mStartTime;
                        mCurrentScore = ((long) pow(mStreak, 1.5) * 20000) / mDuration;
                        mSpeed = (mStreak * 1000) / (float)mDuration;
                        saveScore();
                        Log.d(this.getClass().toString(), "onEditorAction: WRONG ANSWER " + userAnswer + " != " + mGoodAnswer);
                        finish();
                        Toast.makeText(RunActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
                return true;
            }
        });
    }

    private void saveScore() {
        mPreferences.edit().putLong(ScoreActivity.PREF_KEY_LAST_STREAK, mStreak).apply();
        mPreferences.edit().putLong(PREF_KEY_LAST_SCORE, mCurrentScore).apply();
        mPreferences.edit().putLong(ScoreActivity.PREF_KEY_LAST_DURATION, mDuration).apply();
        mPreferences.edit().putFloat(ScoreActivity.PREF_KEY_LAST_SPEED, mSpeed).apply();


        float currentFastest = mPreferences.getFloat(ScoreActivity.PREF_KEY_FASTEST, 0);
        if (mSpeed > currentFastest) {
            mPreferences.edit().putFloat(ScoreActivity.PREF_KEY_FASTEST, mSpeed).apply();
        }

        long currentLongestStreak = mPreferences.getLong(ScoreActivity.PREF_KEY_LONGEST_STREAK, 0);
        if (mStreak > currentLongestStreak) {
            mPreferences.edit().putLong(ScoreActivity.PREF_KEY_LONGEST_STREAK, mStreak).apply();
        }

        long currentBestScore = mPreferences.getLong(ScoreActivity.PREF_KEY_BEST_SCORE, 0);
        if (mCurrentScore > currentBestScore) {
            mPreferences.edit().putLong(ScoreActivity.PREF_KEY_BEST_SCORE, mCurrentScore).apply();
            mPreferences.edit().putLong(ScoreActivity.PREF_KEY_BEST_STREAK, mStreak).apply();
            mPreferences.edit().putLong(ScoreActivity.PREF_KEY_BEST_DURATION, mDuration).apply();
        }
    }


}
