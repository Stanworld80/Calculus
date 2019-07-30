package com.pasqualiselle.calculus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Math.random;

public class RunActivity extends AppCompatActivity {

    int mN1 = 0;
    int mN2 = 0;
    int mGoodAnswer  = 0;
    int mLimitMax = 10;
    EditText answerTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        answerTextView   = findViewById(R.id.answer_editText);

        prepareQuestion();
        prepareAnswerBtn();
    }

    protected void prepareQuestion() {
        mN1 = (int) (random() * (mLimitMax+1));
        mN2 = (int) (random() * (mLimitMax+1));
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
                    int userAnswer = 0;
                    if (userAnswerStr.length() > 0)
                        userAnswer = Integer.parseInt(userAnswerStr);

                    if (mGoodAnswer == userAnswer) {
                        prepareQuestion();
                    } else {
                        Log.d(this.getClass().toString(), "onEditorAction: WRONG ANSWER "+userAnswer + " != " +mGoodAnswer );
                        finish();
                        return false;
                    }
                }
                return true;
            }
        });
    }

}
