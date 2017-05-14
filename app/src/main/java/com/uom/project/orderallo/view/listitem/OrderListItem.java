package com.uom.project.orderallo.view.listitem;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.uom.project.orderallo.R;
import com.uom.project.orderallo.adapter.ItemListAdapter;
import com.uom.project.orderallo.entity.Item;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Himashi Nethinika on 2/14/17.
 */
@EViewGroup(R.layout.item_order)
public class OrderListItem extends RelativeLayout {

    @ViewById
    TextView txtPrice;
    @ViewById
    TextView txtName;
    @ViewById
    TextView txtQty;
//    @ViewById
//    ImageView imgMenu;
//    @ViewById
//    ImageView btnUp;
//    @ViewById
//    ImageView btnDown;

    Item item;
    private ItemListAdapter.ItemOnClickListener onClickListener;

    public OrderListItem(Context context) {
        super(context);
    }

    public void bindData(final Item item) {

        this.item = item;
        setDataToUI();
    }
//
//    @Click
//    void btnUp() {
//        increaseQty();
//    }
//
//    @android.support.annotation.UiThread
//    void increaseQty() {
//        if(item.getQty()>Integer.valueOf(txtQty.getText().toString()) ){
//            txtQty.setText(Integer.valueOf(txtQty.getText().toString()) + 1+"");
//            onClickListener.onAddOrderItem(item);
//        }
//
//    }
//
//    @Click
//    void btnDown() {
//        decreaseQty();
//
//    }

//    @android.support.annotation.UiThread
//    void decreaseQty() {
//        if(Integer.valueOf(txtQty.getText().toString())>0){
//            txtQty.setText(Integer.valueOf(txtQty.getText().toString()) - 1+"");
//            onClickListener.onRemoveOrderItem(item);
//        }
//    }


    @UiThread
    void setDataToUI() {
        txtName.setText(item.getDescription());
        txtPrice.setText("Rs. " + item.getPrice());
        txtQty.setText(item.getQty() + "");

//        Picasso.with(getContext())
//                .load(item.getImgUrl())
//                .into(imgMenu, new Callback() {
//                    @Override
//                    public void onSuccess() {
////                        progressBar.setVisibility(GONE);
//                    }
//
//                    @Override
//                    public void onError() {
//
//                    }
//                });
    }

    public void setOnClickListener(ItemListAdapter.ItemOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
