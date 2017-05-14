package com.uom.project.orderallo.view.listitem;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.uom.project.orderallo.R;
import com.uom.project.orderallo.adapter.CategoryListAdapter;
import com.uom.project.orderallo.entity.Category;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Himashi Nethinika on 2/14/17.
 */
@EViewGroup(R.layout.item_category)
public class CategoryItem extends RelativeLayout {

    @ViewById
    TextView txtCategory;

    Category category;
    private CategoryListAdapter.ItemCLick onClickListener;

    public CategoryItem(Context context) {
        super(context);
    }

    public void bindData(final Category category) {

        this.category = category;
        setDataToUI();
    }

    @UiThread
    void setDataToUI() {
        System.out.println("SET DATA "+category.getName());
        txtCategory.setText(category.getName());
        txtCategory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(category);
            }
        });
    }

    public void setOnClickListener(CategoryListAdapter.ItemCLick onClickListener) {
        this.onClickListener=onClickListener;
    }
}
