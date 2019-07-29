package com.pasqualiselle.calculus;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    private static int RUN_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void goRunActivity(View view) {
        Intent intent = new Intent(this, RunActivity.class);
        startActivityForResult(intent,RUN_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RUN_REQUEST_CODE)
        {
            Intent intent = new Intent(this, ScoreActivity.class);
            startActivity(intent);
        }
    }
}
