package com.uom.project.orderallo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.uom.project.orderallo.entity.Item;
import com.uom.project.orderallo.view.listitem.CategoryItem;
import com.uom.project.orderallo.view.listitem.CategoryItem_;
import com.uom.project.orderallo.view.listitem.ItemListItem;
import com.uom.project.orderallo.view.listitem.ItemListItem_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himashi Nethinika on 2/14/17.
 */
@EBean
public class ItemListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    private List<Item> ItemList = new ArrayList<>();
    private ItemOnClickListener onClickListener;
    private List<Item> orderedItems;

    public void addDataToList(List<Item> items, List<Item> orderedItems) {
        ItemList.clear();
        if (items != null) {
            ItemList.addAll(items);
            changeListData();
        }
        if (orderedItems != null) {
            this.orderedItems=orderedItems;
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
        ItemListItem itemListItem = null;
//        if (convertView == null) {
        itemListItem = ItemListItem_.build(context);
//        } else {
//            itemListItem = (ItemListItem) convertView;
//        }
        Item itemOrder = null;
        for (Item item:orderedItems) {
            if(getItem(position).getItemCode()==item.getItemCode()){
                itemOrder=item;
                break;
            }
        }
        if(itemOrder!=null){
            itemListItem.bindData(getItem(position),itemOrder.getQty());
        }else {
           itemListItem.bindData(getItem(position),0);
        }

        itemListItem.setOnClickListener(onClickListener);
        return itemListItem;
    }

    public void setOnClickListener(ItemOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface ItemOnClickListener {
        void onAddOrderItem(Item item);
        void onRemoveOrderItem(Item item);
    }

}
