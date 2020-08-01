package com.example.omduggineni.addition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.*;

public class Finished extends AppCompatActivity {
    int score;
    TextView text = (TextView)findViewById(R.id.textView);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);
        score = Integer.parseInt(getIntent().getStringExtra("Score"));
        text.setText("Your score was: " + score);
    }
}
