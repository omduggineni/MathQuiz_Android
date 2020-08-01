package com.example.omtatva.mathquizdeveloperpermissions;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DevPermissionsGranted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev_permissions_granted);

        new DelRunner().execute();
    }

    private class DelRunner extends AsyncTask<Void, Void, Integer> {
        @Override
        protected Integer doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 9;
        }
        @Override
        protected void onPostExecute(Integer i){
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.omduggineni.operationsquiz");
            startActivity(intent);
            new Activity().finish();
        }
    }
}
