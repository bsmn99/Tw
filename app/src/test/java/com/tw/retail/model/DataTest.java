package com.tw.retail.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import io.realm.RealmList;

public class DataTest {

    Data data;

    @Before
    public void setUp() {
        this.data = new Data();
    }

    @Test
    public void test_CategoryListNotEmpty() {
        RealmList<Category> categories = new RealmList<>();

        Category category = new Category();
        category.setId("s1");

        categories.add(category);

        data.setCategory(categories);

        RealmList<Category> result = data.getCategory();

        Assert.assertNotNull(result);
        Assert.assertEquals(result,categories);
    }

    @Test
    public void test_CategoryListEmpty() {
        RealmList<Category> categories = new RealmList<>();

        data.setCategory(categories);

        RealmList<Category> result = data.getCategory();

        Assert.assertNotNull(result);
        Assert.assertEquals(result,categories);
    }
}
