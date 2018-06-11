package com.tw.retail.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class RetailTest {

    Retail retail;

    @Before
    public void setUp() {
        this.retail = new Retail();
    }

    @Test
    public void test_Data() {

        Data data = new Data();

        retail.setData(data);

        Data result = retail.getData();

        Assert.assertNotNull(result);
        Assert.assertEquals(result,data);
    }
}
