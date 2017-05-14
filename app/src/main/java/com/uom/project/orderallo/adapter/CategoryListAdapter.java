package com.uom.project.orderallo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.uom.project.orderallo.entity.Category;
import com.uom.project.orderallo.view.listitem.CategoryItem;
import com.uom.project.orderallo.view.listitem.CategoryItem_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Himashi Nethinika on 2/14/17.
 */
@EBean
public class CategoryListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    private List<Category> categoryList = new ArrayList<>();
    private ItemCLick onClickListener;

    public void addDataToList(List<Category> categories) {
        if (categories != null) {
            categoryList.addAll(categories);
            changeListData();
        }

    }

    @UiThread
    void changeListData() {
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Category getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CategoryItem categoryItem = null;
        if (convertView == null) {
            categoryItem = CategoryItem_.build(context);
        } else {
            categoryItem = (CategoryItem) convertView;
        }

        categoryItem.bindData(getItem(position));
        categoryItem.setOnClickListener(onClickListener);
        return categoryItem;
    }

    public void setOnClickListener(ItemCLick onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface ItemCLick{
        void onClick(Category category);
    }
}
