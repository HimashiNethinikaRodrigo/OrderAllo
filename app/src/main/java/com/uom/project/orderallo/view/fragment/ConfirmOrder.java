package com.uom.project.orderallo.view.fragment;


import android.app.Fragment;
import android.content.DialogInterface;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uom.project.orderallo.R;
import com.uom.project.orderallo.adapter.CategoryListAdapter;
import com.uom.project.orderallo.adapter.ItemListAdapter;
import com.uom.project.orderallo.adapter.OrderListAdapter;
import com.uom.project.orderallo.entity.Category;
import com.uom.project.orderallo.entity.Item;
import com.uom.project.orderallo.util.HorizontalListView;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_confirm_order)
public class ConfirmOrder extends BaseFragment {

    private static final String TAG = ConfirmOrder.class.getSimpleName();

    @ViewById
    ListView lvOrderedItem;
    @ViewById
    TextView txtTotalAmount;
    @Bean
    OrderListAdapter orderListAdapter;
    List<Item> orderedItems;
    private CharSequence total;
    private String userChoosenTask;

    @AfterViews
    void afterViewUI() {
        initToolBar(true);
        lvOrderedItem.setAdapter(orderListAdapter);
        orderListAdapter.addDataToList(orderedItems);
        txtTotalAmount.setText(total);

    }


    public void setData(CharSequence text, List<Item> orderedItems) {
        this.total = text;
        this.orderedItems = orderedItems;
        System.out.println("SIZE SIZE " + orderedItems.size());
    }

    @Click
    void btnPayment() {
        System.out.println("METHOD RUN");
        selectPayMethod();
    }


    private void selectPayMethod() {
        final CharSequence[] items = {"Pay by Cash", "Pay by Card",
                "Cancel"};

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
        builder.setTitle("Payment");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Pay by Cash")) {

                    Toast.makeText(getActivity(),"Please wait for Waiter",Toast.LENGTH_SHORT).show();
                } else if (items[item].equals("Pay by Card")) {
                    Payment payment= new Payment_();
                    payment.setData(txtTotalAmount.getText());
                    setFragment(payment, "add", ConfirmOrder.TAG);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

}
