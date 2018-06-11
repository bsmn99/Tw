package com.tw.retail.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Model used for Category.
 */
public class Category extends RealmObject
{
    private RealmList<SubCategory> subCategory;

    private String name;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RealmList<SubCategory> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(RealmList<SubCategory> subCategory) {
        this.subCategory = subCategory;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }
}
