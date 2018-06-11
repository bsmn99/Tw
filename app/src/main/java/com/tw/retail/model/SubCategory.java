package com.tw.retail.model;

import io.realm.RealmObject;

/**
 * Model holds the subcategory of particular category.
 */
public class SubCategory extends RealmObject
{
    private double price;

    private String name;

    private String image;

    private String id;

    private String cid;

    private boolean isAddedToCart;

    public double getPrice ()
    {
        return price;
    }

    public void setPrice (double price)
    {
        this.price = price;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public boolean isAddedToCart() {
        return isAddedToCart;
    }

    public void setAddedToCart(boolean addedToCart) {
        isAddedToCart = addedToCart;
    }

}

