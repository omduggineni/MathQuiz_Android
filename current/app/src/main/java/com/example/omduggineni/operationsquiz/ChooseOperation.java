package com.example.omduggineni.operationsquiz;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static com.example.omduggineni.operationsquiz.SubtractionPositive.internet;

public class ChooseOperation extends AppCompatActivity {

    Button[] buttons = new Button[4];
    int time;
    private AdView mAdView;
    public static boolean dev = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_choose_operation);

        mAdView  = (AdView) findViewById(R.id.adView);

        new AdSetup().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mAdView);

        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);


        buttons[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    time = Integer.parseInt(String.valueOf(((TextView) findViewById(R.id.editTime)).getText()));
                }catch(NumberFormatException e){
                    time = 10;
                }
                Intent intent = new Intent(ChooseOperation.this, Addition.class);
                intent.putExtra("Time", "" + time);
                startActivity(intent);
                new Activity().finish();
            }
        });
        buttons[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    time = Integer.parseInt(String.valueOf(((TextView) findViewById(R.id.editTime)).getText()));
                }catch(NumberFormatException e){
                    time = 10;
                }
                Intent intent = new Intent(ChooseOperation.this, Subtraction.class);
                intent.putExtra("Time", "" + time);
                startActivity(intent);
                new Activity().finish();
            }
        });
        buttons[2].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    time = Integer.parseInt(String.valueOf(((TextView) findViewById(R.id.editTime)).getText()));
                }catch(NumberFormatException e){
                    time = 10;
                }
                Intent intent = new Intent(ChooseOperation.this, Multiplication.class);
                intent.putExtra("Time", "" + time);
                startActivity(intent);
                new Activity().finish();
            }
        });
        buttons[3].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    time = Integer.parseInt(String.valueOf(((TextView) findViewById(R.id.editTime)).getText()));
                }catch(NumberFormatException e){
                    time = 10;
                }
                Intent intent = new Intent(ChooseOperation.this, SubtractionPositive.class);
                intent.putExtra("Time", "" + time);
                startActivity(intent);
                new Activity().finish();
            }
        });
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
