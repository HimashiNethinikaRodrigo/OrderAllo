package com.uom.project.orderallo.view;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.UiThread;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.uom.project.orderallo.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created by ransikadesilva on 2/14/17.
 */
@EActivity
public class BaseActivity extends AppCompatActivity {

    protected ProgressDialog progressDialog;

    void setFragment(Fragment fragment, String key) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (key.equals("add"))
            fragmentTransaction.add(R.id.mainContainer, fragment);
        else {
            System.out.println("FRAGMENT REPLACE");
            fragmentTransaction.replace(R.id.mainContainer, fragment);
        }
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();
        if(fragmentManager.getBackStackEntryCount()>1){
            super.onBackPressed();
        }
    }

    void afterViewUI() {
        progressDialog = new ProgressDialog(this, R.style.MyTheme);
        progressDialog.setCancelable(false);
        //progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);
    }

    @org.androidannotations.annotations.UiThread
    public void showAlertMessage(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        if (!isFinishing()) {
            alert.show();
            TextView messageView = (TextView) alert.findViewById(android.R.id.message);
            messageView.setGravity(Gravity.CENTER);
        }
    }

    @org.androidannotations.annotations.UiThread
    public void showSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }

    @org.androidannotations.annotations.UiThread
    protected void showErrorToEditText(String error, MaterialEditText... materialEditTexts) {
        for (MaterialEditText materialEditText : materialEditTexts) {
            materialEditText.setError(error);
        }
    }

    @org.androidannotations.annotations.UiThread
    protected void showErrorForEmptyFields(String error, MaterialEditText... materialEditTexts) {
        for (MaterialEditText materialEditText : materialEditTexts) {
            if (materialEditText.getText().toString().isEmpty() || materialEditText.getText().toString().equals("")) {
                materialEditText.setError(error);
            }
        }
    }

    protected boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    protected boolean cleckEditTextFieldsEmpty(EditText... editTextFields) {
        boolean isEmpty = false;
        for (EditText editText1 : editTextFields) {
            if (editText1.getText().toString().isEmpty() || editText1.getText().toString().equals("")) {
                isEmpty = true;
                break;
            }
        }
        return isEmpty;
    }

    protected void initToolBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @org.androidannotations.annotations.UiThread
    protected void showProgressbar() {
        progressDialog.show();
    }


    public void hideKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {

        }
    }
}
