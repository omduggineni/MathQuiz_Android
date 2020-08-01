package com.example.omduggineni.addition;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Arrays;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Addition extends AppCompatActivity {
    int rightbutton, score, num1, num2, sum;
    Button[] buttons = new Button[4];
    ProgressBar bar;
    TextView text;
    boolean isrunning, answasright, isDone;
    long starttime;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        score = 0;
        bar = findViewById(R.id.progressBar);
        text = findViewById(R.id.fullscreen_content);
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answasright = rightbutton == 0;
                timer.cancel(true);
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answasright = rightbutton == 1;
                timer.cancel(true);
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answasright = rightbutton == 2;
                timer.cancel(true);
            }
        });
        buttons[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                answasright = rightbutton == 3;
                timer.cancel(true);
            }
        });
        setup();
    }
    public int getRandom(int max){
        int number = (int) Math.floor(Math.random()*max);
        if(max == 18){
            if(number == sum){
                return getRandom(18);
            }
        }
        return number;
    }
    public void setup(){
        timer = new Timer();
        isDone = false;
        isrunning = true;
        num1 = getRandom(10);
        num2 = getRandom(10);
        text.setText(new String(String.valueOf(num1) + "+" + String.valueOf(num2)));
        sum = num1+num2;
        rightbutton = getRandom(4);
        buttons[rightbutton].setText(String.valueOf(sum));
        int[] possiblevalues = {1, 2, 3, 4};
        System.out.println(rightbutton);
        if(rightbutton == 0){
            possiblevalues = new int[]{1, 2, 3};
        }else if(rightbutton == 1){
            possiblevalues = new int[]{0, 2, 3};
        }else if(rightbutton == 2){
            possiblevalues = new int[]{0, 1, 3};
        }else if(rightbutton == 3){
            possiblevalues = new int[]{0, 1, 2};
        }
        System.out.println(Arrays.toString(possiblevalues));
        for(int i = 0; i < possiblevalues.length; i++){
            buttons[possiblevalues[i]].setText(String.valueOf(getRandom(18)));
        }
        starttime = System.nanoTime();
        timer.execute();
    }
    public void Done(){
        if(answasright){
            score += 5;
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e){
                System.out.println("ERROR: Interrupted.");
            }
            setup();
        }else{
            Intent intent = new Intent(this, Finished.class);
            intent.putExtra("Score", String.valueOf(score));
            startActivity(intent);
            new Activity().finish();
        }
    }
    public class Timer extends AsyncTask<Void, Integer, Integer> {
        @Override
        protected Integer doInBackground(Void... Params){
            long elapsedtime = System.nanoTime() - starttime;
            while(elapsedtime < 10.0E09){
                try {
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    // do nothing
                }
                publishProgress((int) ((elapsedtime/ 10.0E09) * 100));
                elapsedtime = System.nanoTime() - starttime;
                if(isCancelled()) break;
            }
            return 9;
        }

        @Override
        protected void onProgressUpdate(Integer... Progress){
            int progress = (100-Integer.parseInt(Progress[0].toString()));
            System.out.println(progress);
            bar.setProgress(progress);
        }

        @Override
        protected void onPostExecute(Integer nothingimportant){
            done();
            answasright = false;
        }

        @Override
        protected void onCancelled(Integer nothingimportant){
            done();
        }

        public void done(){
            Done();
        }
    }
}
