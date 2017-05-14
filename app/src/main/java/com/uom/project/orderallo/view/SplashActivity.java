package com.uom.project.orderallo.view;

import android.content.Intent;
import android.os.Handler;
import android.widget.ProgressBar;

import com.uom.project.orderallo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    @ViewById
    ProgressBar progressBar;

    @AfterViews
    void afterViewUI() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, InitialActivity_.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }


}
