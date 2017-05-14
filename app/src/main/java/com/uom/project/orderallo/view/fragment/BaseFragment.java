package com.uom.project.orderallo.view.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.rengwuxian.materialedittext.MaterialEditText;
import com.uom.project.orderallo.R;
import com.uom.project.orderallo.view.DashboardActivity;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.RootContext;

/**
 * Created by ransikadesilva on 2/15/17.
 */
@EFragment
public class BaseFragment extends Fragment {


    void setFragment(Fragment fragment, String key, String tag) {
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (key.equals("add"))
            fragmentTransaction.add(R.id.mainContainer, fragment);
        else
            fragmentTransaction.replace(R.id.mainContainer, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }


    protected void initToolBar( boolean backBtnVisibility) {
        AppCompatActivity activity = (DashboardActivity) getActivity();
        if (activity.getSupportActionBar() != null) {

            if (backBtnVisibility) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                activity.getSupportActionBar().setDisplayShowHomeEnabled(true);

            }

            activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

}
