package com.tw.retail.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import io.realm.RealmList;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() {
        this.category = new Category();
    }

    @Test
    public void test_id() {
        String id="c1";
        category.setId(id);

        String resultId = category.getId();

        Assert.assertNotNull(resultId);
        Assert.assertEquals(id,resultId);
    }

    @Test
    public void test_name() {
        String name="Washing Machine";
        category.setName(name);

        String resultId = category.getName();

        Assert.assertNotNull(resultId);
        Assert.assertEquals(name,resultId);
    }

    @Test
    public void test_SubcategoryEmpty() {
        RealmList<SubCategory> subCategories = new RealmList<>();

        category.setSubCategory(subCategories);

        RealmList<SubCategory> result = category.getSubCategory();

        Assert.assertNotNull(result);
        Assert.assertEquals(result,subCategories);
    }

    @Test
    public void test_SubcategoryWithList() {
        RealmList<SubCategory> subCategories = new RealmList<>();

        SubCategory subCategory = new SubCategory();
        subCategory.setId("s1");

        subCategories.add(subCategory);

        category.setSubCategory(subCategories);

        RealmList<SubCategory> result = category.getSubCategory();

        Assert.assertNotNull(result);
        Assert.assertEquals(result,subCategories);
    }
}
