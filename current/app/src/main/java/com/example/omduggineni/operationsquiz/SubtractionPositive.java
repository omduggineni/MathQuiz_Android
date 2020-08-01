package com.example.omduggineni.operationsquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.Arrays;

import com.example.omduggineni.operationsquiz.ChooseOperation.AdSetup;
import com.google.android.gms.ads.MobileAds;

import static com.example.omduggineni.operationsquiz.ChooseOperation.dev;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SubtractionPositive extends AppCompatActivity {
    int rightbutton, score, num1, num2, sum;
    Button[] buttons = new Button[4];
    ProgressBar bar;
    TextView text;
    boolean isrunning, answasright, isDone;
    long starttime;
    Timer timer;
    int time;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);


        mAdView = findViewById(R.id.adView);
        new AdSetup().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mAdView);


        score = 0;
        try {
            time = Integer.parseInt(getIntent().getStringExtra("Time"));
        }catch(Exception e){
            time = 10;
        }

        bar = findViewById(R.id.progressBar);
        text = findViewById(R.id.fullscreen_content);
        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(rightbutton == 0) {
                    answasright = true;
                }else{
                    answasright = false;
                }
                timer.cancel(true);
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(rightbutton == 1) {
                    answasright = true;
                }else{
                    answasright = false;
                }
                timer.cancel(true);
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(rightbutton == 2) {
                    answasright = true;
                }else{
                    answasright = false;
                }
                timer.cancel(true);
            }
        });
        buttons[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(rightbutton == 3) {
                    answasright = true;
                }else{
                    answasright = false;
                }
                timer.cancel(true);
            }
        });
        setup();
    }

    public static boolean internet(Context thecon){
        if(isPackageInstalled("com.example.omtatva.mathquizdeveloperpermissions", thecon.getPackageManager())) {
            Runtime runtime = Runtime.getRuntime();
            try {

                Process ipProcess = runtime.exec("/system/bin/ping -c 1 -i 0.2 8.8.8.8");
                int exitValue = ipProcess.waitFor();
                return (exitValue == 0);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
    private static boolean isPackageInstalled(String packagename, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packagename, 0);
            System.out.println("DEV Permissions UNLOCKED!");
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    public int getRandom(int max){
        int number = (int) Math.floor(Math.random()*max);
        if(max == 9){
            int[] numbers = new int[4];
            for(int i = 0; i < numbers.length; i++) {
                try{
                    numbers[i] = Integer.parseInt((String) buttons[i].getText());
                }catch(NumberFormatException e){
                    numbers[i] = -1;
                }
            }
            if(number == sum || number == numbers[0] || number == numbers[1] || number == numbers[2] || number == numbers[3]){
                return getRandom(9);
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
        text.setText(new String(String.valueOf(num1) + " - " + String.valueOf(num2)));
        sum = num1-num2;
        if(sum < 0){
            int temp = num1;
            num1 = num2;
            num2 = temp;
            sum = num1-num2;
            text.setText(new String(String.valueOf(num1) + " - " + String.valueOf(num2)));
        }
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
            buttons[possiblevalues[i]].setText(String.valueOf(getRandom(9)));
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
            intent.putExtra("Activity_Signature","SP");
            intent.putExtra("Activity_Time_Settings",getIntent().getStringExtra("Time"));
            startActivity(intent);
            new Activity().finish();
        }
    }
    public class Timer extends AsyncTask<Void, Integer, Integer> {
        @Override
        protected Integer doInBackground(Void... Params){
            long elapsedtime = System.nanoTime() - starttime;
            while(elapsedtime < 1E9*time){
                try {
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    System.out.println("ERROR: Interrupted.");
                }
                publishProgress((int) ((elapsedtime/ (1E9*time)) * 100));
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

    public class AdSetup extends AsyncTask<AdView, Void, Integer> {
        @Override
        public Integer doInBackground(AdView... mAdView){
            if(internet(getApplicationContext())){
                return 0;
            }else{
                FrameLayout button_bar = findViewById(R.id.frame_layout);
                button_bar.setTranslationY(0);
            }
            return 1;
        }

        @Override
        public void onPostExecute(Integer resultcode){
            if( resultcode == 0 ){
                MobileAds.initialize(getApplicationContext(), "ca-app-pub-6255614109797267~3856143908");
                AdRequest adRequest = new AdRequest.Builder().addTestDevice("E52CA99BA814AB00B586FED468B3B6F5").build();
                mAdView.loadAd(adRequest);
                mAdView.setAdListener(new AdListener() {
                    @Override
                    public void onAdLoaded() {
                        //Nothing
                    }

                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        FrameLayout button_bar = findViewById(R.id.frame_layout);
                        button_bar.setTranslationY(0);
                    }

                    @Override
                    public void onAdOpened() {
                        //Nothing
                    }

                    @Override
                    public void onAdLeftApplication() {
                        //Nothing
                    }

                    @Override
                    public void onAdClosed() {
                        //Nothing
                    }
                });
            }
        }
    }
    
}
