package com.uom.project.orderallo.view;


import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.validator.routines.EmailValidator;

@EActivity(R.layout.activity_signup)
public class SignUpActivity extends BaseActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    @ViewById
    Toolbar toolBar;
    @ViewById
    MaterialEditText edtEmail;
    @ViewById
    MaterialEditText edtPassword;
    @ViewById
    MaterialEditText edtConfirmPassword;
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
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @OptionsItem(android.R.id.home)
    void backButtonClick() {
        InitialActivity_.intent(this).start();
        finish();
    }

    @Click(R.id.btnLogin)
    void buttonClicks(final View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                if (isNetworkAvailable()) {
                    hideKeyboard();
                    callUserLogin();

                } else
                    showSnackbar(view, getString(R.string.noInternetConnection));
                break;
        }
    }

    private void callUserLogin() {

        if (!cleckEditTextFieldsEmpty(edtEmail, edtPassword, edtConfirmPassword)) {
            String password = edtPassword.getText().toString();
            String confirmPassword = edtConfirmPassword.getText().toString();
            if (EmailValidator.getInstance().isValid(edtEmail.getText().toString())) {
                if (password.equals(confirmPassword)) {

                    showProgressbar();

                    firebaseAuthentication();


                } else {
                    showErrorToEditText(getString(R.string.passwordMismatch), edtConfirmPassword);
                }
            } else {
                showErrorToEditText(getString(R.string.invalidEmail), edtEmail);
            }
        } else {
            showErrorForEmptyFields(getString(R.string.fillFields), edtConfirmPassword, edtPassword, edtEmail);
        }
    }

    private void firebaseAuthentication() {
        mAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (progressDialog != null) {
                                    progressDialog.dismiss();
                                }
                                if (task.isSuccessful()) {
                                    showSnackbar(btnLogin, getString(R.string.registrationSuccess));
                                    backToLogin();
                                    clearFields();
                                } else {
                                    showSnackbar(btnLogin, getString(R.string.registrationError));
                                }
                            }
                        }
                ).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("ERROR " + e);
                if (e.getMessage().contains("WEAK_PASSWORD")){
                    showSnackbar(btnLogin, "User registration fail. Password is not strong.");
                    progressDialog.dismiss();
                }else if(e.getMessage().contains("The email address is badly formatted")){
                    showSnackbar(btnLogin,"User registration fail. Email is not valid.");
                    progressDialog.dismiss();
                }else if(e.getMessage().contains("The email address is already in use by another account")){
                    showSnackbar(btnLogin, "User registration fail. Email is not valid.");
                    progressDialog.dismiss();
                }
            }
        });
    }


    @UiThread
    void backToLogin() {

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                LoginActivity_.intent(SignUpActivity.this).start();
            }
        }, 2000);
    }

    @UiThread
    void clearFields() {
        edtEmail.setText("");
        edtPassword.setText("");
        edtConfirmPassword.setText("");
    }

}
