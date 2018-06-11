package com.tw.retail.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Model holds all the category list.
 */
public class Data extends RealmObject
{
    private RealmList<Category> category;

    public RealmList<Category> getCategory() {
        return category;
    }

    public void setCategory(RealmList<Category> category) {
        this.category = category;
    }
}

