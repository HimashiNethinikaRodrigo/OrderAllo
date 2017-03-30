package com.uom.project.orderallo.view;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.uom.project.orderallo.R;
import com.uom.project.orderallo.service.MyService;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_splash)
public class Splash extends AppCompatActivity {

    @AfterViews
    void AfterViewUI(){
        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this,Initial_.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }

}
