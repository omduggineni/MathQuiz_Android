package com.example.omduggineni.addition;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.CountDownLatch;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Addition extends AppCompatActivity {
    int rightanswer, score;
    Button button1, button2, button3, button4;
    ProgressBar bar;
    TextView text;
    boolean isrunning, answasright;
    long starttime;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        bar = findViewById(R.id.progressBar);
        text = findViewById(R.id.fullscreen_content);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timer.answasright = timer.check(0);
                timer.responsedone = true;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timer.answasright = timer.check(1);
                timer.responsedone = true;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timer.answasright = timer.check(2);
                timer.responsedone = true;
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                timer.answasright = timer.check(3);
                timer.responsedone = true;
            }
        });
        timer = new Timer(bar, text, new Button[]{button1, button2, button3,  button4});
        timer();
    }
    public void check(int button){

    }
    public void timer(){
        timer.setup();
        timer.start();
        while(!timer.isDone) {
            CountDownLatch latch = new CountDownLatch(1);
            latch.countDown();
        }
        answasright = timer.answasright;
        if(answasright){
            timer();
        }else{
            Intent intent = new Intent(getBaseContext(), Finished.class);
            intent.putExtra("Score", score);
            startActivity(intent);
            new Activity().finish();
        }
    }
}
