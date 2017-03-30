package com.uom.project.orderallo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.uom.project.orderallo.R;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_dashboard)
public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }
}
