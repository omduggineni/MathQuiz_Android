package com.example.omduggineni.operationsquiz;


import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import static com.example.omduggineni.operationsquiz.SubtractionPositive.internet;

public class Finished extends AppCompatActivity {
    int score, probnum;
    TextView text;
    Button button;
    Button button2;
    Button backbutton, forwardbutton;
    public InterstitialAd mInterstitialAd;
    String prevActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        prevActivity = getIntent().getExtras().getString("Activity_Signature");
        System.out.println(prevActivity);

        mInterstitialAd = new InterstitialAd(getApplicationContext());
        new AdSetup().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        text = (TextView) findViewById(R.id.textView);
        score = Integer.parseInt(getIntent().getStringExtra("Score"));
        text.setText("Your score was: " + score);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            // Code to be executed when an ad finishes loading.
                        }

                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            // Code to be executed when an ad request fails.
                        }

                        @Override
                        public void onAdOpened() {
                            // Code to be executed when the ad is displayed.
                        }

                        @Override
                        public void onAdLeftApplication() {
                            // Code to be executed when the user has left the app.
                        }

                        @Override
                        public void onAdClosed() {
                            Intent restart = new Intent(Finished.this, ChooseOperation.class);
                            startActivity(restart);
                            new Activity().finish();
                        }
                    });
                } else {
                    System.out.println("The interstitial wasn't loaded yet.");
                    Intent restart = new Intent(Finished.this, ChooseOperation.class);
                    startActivity(restart);
                    new Activity().finish();
                }
            }
        });
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    mInterstitialAd.setAdListener(new AdListener() {
                        @Override
                        public void onAdLoaded() {
                            // Code to be executed when an ad finishes loading.
                        }

                        @Override
                        public void onAdFailedToLoad(int errorCode) {
                            // Code to be executed when an ad request fails.
                        }

                        @Override
                        public void onAdOpened() {
                            // Code to be executed when the ad is displayed.
                        }

                        @Override
                        public void onAdLeftApplication() {
                            // Code to be executed when the user has left the app.
                        }

                        @Override
                        public void onAdClosed() {
                            Intent restart;
                            if(prevActivity.equals("A")){
                                restart = new Intent(Finished.this, Addition.class);
                            }else if(prevActivity.equals("S")){
                                restart = new Intent(Finished.this, Subtraction.class);
                            }else if(prevActivity.equals("M")){
                                restart = new Intent(Finished.this, Multiplication.class);
                            }else{
                                restart = new Intent(Finished.this, SubtractionPositive.class);
                            }
                            restart.putExtra("Time", getIntent().getStringExtra("Activity_Time_Settings"));
                            startActivity(restart);
                            new Activity().finish();
                        }
                    });
                } else {
                    System.out.println("The interstitial wasn't loaded yet.");
                    Intent restart;
                    if(prevActivity.equals("A")){
                        restart = new Intent(Finished.this, Addition.class);
                    }else if(prevActivity.equals("S")){
                        restart = new Intent(Finished.this, Subtraction.class);
                    }else if(prevActivity.equals("M")){
                        restart = new Intent(Finished.this, Multiplication.class);
                    }else{
                        restart = new Intent(Finished.this, SubtractionPositive.class);
                    }
                    restart.putExtra("Time", getIntent().getStringExtra("Activity_Time_Settings"));
                    startActivity(restart);
                    new Activity().finish();
                }
            }
        });
    }

    public class AdSetup extends AsyncTask<Void, Void, Integer> {
        @Override
        public Integer doInBackground(Void[] v){
            if(internet(getApplicationContext())){
                return 1;
            }
            return 0;
        }
        public void onPostExecute(Integer i){
            if(i==1){
                MobileAds.initialize(getApplicationContext(), "ca-app-pub-6255614109797267~3856143908");
                mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        }
    }
}
