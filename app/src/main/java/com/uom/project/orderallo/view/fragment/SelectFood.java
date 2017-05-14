package com.uom.project.orderallo.view.fragment;


import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uom.project.orderallo.R;
import com.uom.project.orderallo.adapter.CategoryListAdapter;
import com.uom.project.orderallo.adapter.ItemListAdapter;
import com.uom.project.orderallo.entity.Category;
import com.uom.project.orderallo.entity.Item;
import com.uom.project.orderallo.util.HorizontalListView;
import com.uom.project.orderallo.view.DashboardActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.fragment_select_food)
public class SelectFood extends BaseFragment {

    private static final String TAG = SelectFood.class.getSimpleName();
    @ViewById
    Toolbar toolBar;
    @ViewById
    Button btnMakeOrder;
    @ViewById
    TextView txtTotalAmount;
    @ViewById
    ListView lvMenu;
    @ViewById
    HorizontalListView lvCategory;
    @ViewById
    ProgressBar progressBar;
    @Bean
    CategoryListAdapter categoryListAdapter;
    @Bean
    ItemListAdapter itemListAdapter;
    List<Category> categories;
    List<Item> itemList;
    List<Item> orderedItems;

    FirebaseDatabase database;
    DatabaseReference dbRefCategory;
    DatabaseReference dbRefItem;

    @AfterViews
    void afterViewUI() {
        lvCategory.setAdapter(categoryListAdapter);
        lvMenu.setAdapter(itemListAdapter);
        orderedItems = new ArrayList<>();

        categoryListAdapter.setOnClickListener(new CategoryListAdapter.ItemCLick() {
            @Override
            public void onClick(Category category) {
                loadMenuData(category);
            }
        });

        itemListAdapter.setOnClickListener(new ItemListAdapter.ItemOnClickListener() {
            @Override
            public void onAddOrderItem(Item item) {
                double amount = Double.parseDouble(txtTotalAmount.getText().toString().split(". ")[1]);
                double tot = amount + item.getPrice();
                txtTotalAmount.setText("Rs. " + tot);
                Item itemOrdered = null;
                for (Item item1 : orderedItems) {
                    if (item.getItemCode() == item1.getItemCode()) {
                        item1.setQty(item1.getQty() + 1);
                        item1.setPrice(item1.getPrice() + item.getPrice());
                        itemOrdered = item1;
                    }
                }
                if (itemOrdered == null) {
                    itemOrdered = item;
                    itemOrdered.setQty(1);
                    itemOrdered.setPrice(item.getPrice());
                    orderedItems.add(itemOrdered);
                }
            }

            @Override
            public void onRemoveOrderItem(Item item) {
                double amount = Double.parseDouble(txtTotalAmount.getText().toString().split(". ")[1]);
                double tot = amount - item.getPrice();
                txtTotalAmount.setText("Rs. " + tot);
                for (Item item1 : orderedItems) {
                    if (item.getItemCode() == item1.getItemCode()) {
                        item1.setQty(item1.getQty() - 1);
                        item1.setPrice(item1.getPrice() - item.getPrice());
                        if (item1.getQty() == 0) {
                            orderedItems.remove(item1);
                        }
                    }
                }
            }
        });
        loadCategoryData();
    }

    private void loadMenuData(final Category category) {
        itemList.clear();
        dbRefItem.child("categoryId").equalTo(category.getCategoryId());
        dbRefItem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Item item = postSnapshot.getValue(Item.class);
                    if (item.getCategoryId() == category.getCategoryId()) {
                        itemList.add(item);
                    }
                }
                Log.e("Count ", "" + categories.size());
                itemListAdapter.addDataToList(itemList,orderedItems);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void loadCategoryData() {
        dbRefCategory.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count ", "" + dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    categories.add(category);
                }
                Log.e("Count ", "" + categories.size());
                categoryListAdapter.addDataToList(categories);
                loadMenuData(categories.get(0));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


    @Click
    void btnMakeOrder() {
        ConfirmOrder confirmOrder = new ConfirmOrder_();
        confirmOrder.setData(txtTotalAmount.getText(), orderedItems);
        setFragment(confirmOrder, "add", SelectFood.TAG);
    }

    @AfterInject
    void afterInject() {
        database = FirebaseDatabase.getInstance();
        dbRefCategory = database.getReference("category");
        dbRefItem = database.getReference("item");
        categories = new ArrayList<>();
        itemList = new ArrayList<>();
    }


}
