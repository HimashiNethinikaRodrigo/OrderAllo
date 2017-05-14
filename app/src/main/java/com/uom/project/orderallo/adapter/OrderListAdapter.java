package com.uom.project.orderallo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.uom.project.orderallo.entity.Item;
import com.uom.project.orderallo.view.listitem.ItemListItem;
import com.uom.project.orderallo.view.listitem.ItemListItem_;
import com.uom.project.orderallo.view.listitem.OrderListItem;
import com.uom.project.orderallo.view.listitem.OrderListItem_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himashi Nethinika on 2/14/17.
 */
@EBean
public class OrderListAdapter extends BaseAdapter  {

    @RootContext
    Context context;

    private List<Item> ItemList = new ArrayList<>();
    private ItemOnClickListener onClickListener;

    public void addDataToList(List<Item> items) {
        ItemList.clear();
        if (items != null) {
            ItemList.addAll(items);
            changeListData();
        }

    }


    @UiThread
    void changeListData() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return ItemList.size();
    }

    @Override
    public Item getItem(int position) {
        return ItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        OrderListItem orderListItem = null;
//        if (convertView == null) {
        orderListItem = OrderListItem_.build(context);
//        } else {
//            itemListItem = (ItemListItem) convertView;
//        }

        orderListItem.bindData(getItem(position));

        return orderListItem;
    }

    public void setOnClickListener(ItemOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface ItemOnClickListener {
        void onAddOrderItem(Item item);
        void onRemoveOrderItem(Item item);
    }

}
