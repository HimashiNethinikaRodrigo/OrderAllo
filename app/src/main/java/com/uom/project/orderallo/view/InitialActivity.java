package com.uom.project.orderallo.view;

import android.content.Intent;
import android.view.View;


import com.uom.project.orderallo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
@EActivity(R.layout.activity_initial)
public class InitialActivity extends BaseActivity {

    private static final String TAG = InitialActivity.class.getSimpleName();

    @AfterViews
    void afterViewUI() {
        super.afterViewUI();
    }


    @Click({R.id.btnSignin, R.id.btnSignupEmail})
    void buttonClicks(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnSignupEmail:
                intent = new Intent(InitialActivity.this, SignUpActivity_.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btnSignin:
                intent = new Intent(InitialActivity.this, LoginActivity_.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
