package com.uom.project.orderallo.entity;

import java.util.Date;

/**
 * Created by Himashi Nethinika on 3/31/2017.
 */

public class Category {
    private long categoryId;
    private String name;
    private Date date;


    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
