package com.tw.retail.model;

import io.realm.RealmObject;

/**
 * Model holds the data objects
 */
public class Retail extends RealmObject
{
    private Data data;

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }
}
