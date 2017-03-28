package com.uom.project.orderallo.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.uom.project.orderallo.R;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

@EActivity(R.layout.activity_signin)
public class Signin extends AppCompatActivity {

    @ViewById
    EditText edtEmail;
    @ViewById
    EditText edtPassword;
    @ViewById
    Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_signin);
    }
}
