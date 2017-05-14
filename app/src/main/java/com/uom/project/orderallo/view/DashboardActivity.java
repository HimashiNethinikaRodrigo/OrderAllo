package com.uom.project.orderallo.view;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uom.project.orderallo.R;
import com.uom.project.orderallo.entity.Category;
import com.uom.project.orderallo.adapter.CategoryListAdapter;
import com.uom.project.orderallo.entity.Item;
import com.uom.project.orderallo.adapter.ItemListAdapter;
import com.uom.project.orderallo.util.HorizontalListView;
import com.uom.project.orderallo.view.fragment.SelectFood;
import com.uom.project.orderallo.view.fragment.SelectFood_;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EActivity(R.layout.activity_dashboard)
public class DashboardActivity extends BaseActivity {

    private static final String TAG = DashboardActivity.class.getSimpleName();
    @ViewById
    Toolbar toolBar;

    @AfterViews
    void afterViewUI() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        super.afterViewUI();

        setFragment(new SelectFood_(), "add");
    }


}
