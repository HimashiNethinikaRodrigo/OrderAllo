package com.uom.project.orderallo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.uom.project.orderallo.R;
import com.uom.project.orderallo.service.MyService;

public class Splash extends AppCompatActivity {
    String msg="Android";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Log.d(msg,"On create method.");
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(msg,"On start method.");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(msg,"On resume method.");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(msg,"On pause method.");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(msg,"On stop method.");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(msg,"On destroy method.");
    }

    public void startService(View view){
        startService(new Intent(getBaseContext(),MyService.class));
    }

    public void stopService(View view){
        stopService(new Intent(getBaseContext(),MyService.class));
    }
}
