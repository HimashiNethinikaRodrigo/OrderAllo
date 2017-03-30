package com.uom.project.orderallo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.uom.project.orderallo.R;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_initial)
public class Initial extends AppCompatActivity {

//    @ViewById
//    Button btnSignup;
//    @ViewById
//    Button btnSignin;

    @Click({R.id.btnSignin, R.id.btnSignup})
    void buttonClicks(View view){
        Intent intent;
        switch (view.getId()){
            case R.id.btnSignup:
                intent=new Intent(Initial.this, Signup_.class);
                startActivity(intent);
                break;
            case R.id.btnSignin:
                intent=new Intent(Initial.this, Signin_.class);
                startActivity(intent);
                startActivity(intent);
                break;
        }
    }
}
