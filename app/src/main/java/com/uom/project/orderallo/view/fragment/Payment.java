package com.uom.project.orderallo.view.fragment;


import android.content.DialogInterface;
import android.widget.ListView;
import android.widget.TextView;

import com.uom.project.orderallo.R;
import com.uom.project.orderallo.adapter.OrderListAdapter;
import com.uom.project.orderallo.entity.Item;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EFragment(R.layout.fragment_payment)
public class Payment extends BaseFragment {

    private static final String TAG = Payment.class.getSimpleName();

    @ViewById
    TextView txtAmount;
    private CharSequence data;


    @AfterViews
    void afterViewUI() {
        initToolBar(true);
        txtAmount.setText(data);

    }

    @Click
    void btnPay() {
        setFragment(new SelectFood_(), "replace", Payment.TAG);
    }


    public void setData(CharSequence data) {
        this.data = data;
    }
}
