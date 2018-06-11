package com.tw.retail.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class SubCategoryTest {

    SubCategory subCategory;

    @Before
    public void setUp() {
        this.subCategory = new SubCategory();
    }

    @Test
    public void test_id() {
        String id = "s1";

        SubCategory subCategory = new SubCategory();
        subCategory.setId(id);

        String result = subCategory.getId();

        Assert.assertNotNull(result);
        Assert.assertEquals(result,id);
    }

    @Test
    public void test_name() {
        String name = "Vaccum Cleaner";

        SubCategory subCategory = new SubCategory();
        subCategory.setName(name);

        String result = subCategory.getName();

        Assert.assertNotNull(result);
        Assert.assertEquals(result,name);
    }

    @Test
    public void test_price() {
        double price = 10.f;

        SubCategory subCategory = new SubCategory();
        subCategory.setPrice(price);

        double result = subCategory.getPrice();

        Assert.assertEquals(result,price);
    }

    @Test
    public void test_image() {
        String image = "ImagePath";

        SubCategory subCategory = new SubCategory();
        subCategory.setImage(image);

        String result = subCategory.getImage();

        Assert.assertEquals(result,image);
    }

    @Test
    public void test_cid() {
        String id = "c1";

        SubCategory subCategory = new SubCategory();
        subCategory.setCid(id);

        String result = subCategory.getCid();

        Assert.assertEquals(result,id);
    }

    @Test
    public void test_isAddedToCart() {
        boolean isAdded = true;

        SubCategory subCategory = new SubCategory();
        subCategory.setAddedToCart(isAdded);

        boolean result = subCategory.isAddedToCart();

        Assert.assertEquals(result,isAdded);
    }
}
