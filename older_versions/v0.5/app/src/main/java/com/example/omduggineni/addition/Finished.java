package com.example.omduggineni.addition;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.*;

public class Finished extends AppCompatActivity {
    int score;
    TextView text;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);
        text = (TextView)findViewById(R.id.textView);
        score = Integer.parseInt(getIntent().getStringExtra("Score"));
        text.setText("Your score was: " + score);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restart = new Intent(Finished.this, Addition.class);
                startActivity(restart);
                new Activity().finish();
            }
        });
    }
}
