package com.example.omduggineni.operationsquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(EnterActivity.this, ChooseOperation.class));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Activity().finish();
            }
        });
    }
}
