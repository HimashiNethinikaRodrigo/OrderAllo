package com.uom.project.orderallo.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.uom.project.orderallo.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    @ViewById
    Toolbar toolBar;
    @ViewById
    MaterialEditText edtEmail;
    @ViewById
    MaterialEditText edtPassword;
    @ViewById
    Button btnLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @AfterViews
    void afterViewUI() {
        initToolBar(toolBar);
        super.afterViewUI();
    }

    @AfterInject
    void afterInject() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Click({R.id.txtForgotPw, R.id.btnLogin})
    void buttonClicks(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.txtForgotPw:
//                intent = new Intent(LoginActivity.this, ForgotPassword_.class);
//                startActivity(intent);
                break;
            case R.id.btnLogin:
                if (isNetworkAvailable()) {
                    super.hideKeyboard();
                    userLogin();
                } else
                    showSnackbar(view, getString(R.string.noInternetConnection));
                break;
        }
    }

    private void userLogin() {
        showProgressbar();
        if (!cleckEditTextFieldsEmpty(edtPassword, edtEmail)) {
            mAuth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (progressDialog != null) {
                                progressDialog.dismiss();
                            }
                            if (task.isSuccessful()) {
                                DashboardActivity_.intent(LoginActivity.this).start();
                                finish();
                            } else {
                                showSnackbar(btnLogin, "Email or password incorrect. please try again.");
                            }
                        }
                    });
        } else {
            showErrorForEmptyFields("Please fill the field", edtPassword, edtEmail);
        }

    }


    @OptionsItem(android.R.id.home)
    void backButtonClick() {
        InitialActivity_.intent(this).start();
        finish();
    }

}
