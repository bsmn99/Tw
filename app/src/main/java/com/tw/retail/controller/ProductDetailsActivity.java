package com.tw.retail.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tw.retail.R;
import com.tw.retail.model.SubCategory;
import com.tw.retail.utils.Utils;

import io.realm.Realm;

public class ProductDetailsActivity extends BaseActivity {

    /**
     * Subcategory Id used to display product details.
     */
    String selectedSubCategoryId;

    /**
     * Product name Textview
     */
    TextView name;

    /**
     * Product Imageview
     */
    ImageView imageView;

    /**
     * Product price Textview
     */
    TextView price;

    /**
     * For adding item to cart
     */
    Button addToCart;

    /**
     * For navigating to shopping cart page.
     */
    Button checkOut;

    /**
     * Local SubCategory object from database.
     */
    SubCategory subCategory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeButtonEnabled(true);

        selectedSubCategoryId = getIntent().getStringExtra(Utils.SELECTED_PRODUCT);

        name = findViewById(R.id.name);
        imageView = findViewById(R.id.imageView);
        price = findViewById(R.id.price);
        addToCart = findViewById(R.id.addToCart);
        checkOut = findViewById(R.id.checkOut);

    }

    @Override
    protected void onResume() {
        super.onResume();

        subCategory = getSubCategory(selectedSubCategoryId);

        updateCart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        name.setText(subCategory.getName());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("₹ ").append(subCategory.getPrice());
        price.setText(stringBuilder);

        int resourceId = getResources().getIdentifier(subCategory.getImage(), "drawable", getPackageName());
        imageView.setImageResource(resourceId);

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoShoppingCart(ProductDetailsActivity.this);
            }
        });

        if(subCategory.isAddedToCart()) {
            addToCart.setEnabled(false);
            addToCart.setText(R.string.addedtocart);
            checkOut.setVisibility(View.VISIBLE);
        } else {
            addToCart.setEnabled(true);
            addToCart.setText(R.string.add_to_cart);
            checkOut.setVisibility(View.GONE);

            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    realm.beginTransaction();

                    subCategory.setAddedToCart(true);

                    realm.commitTransaction();

                    addToCart.setText(R.string.addedtocart);

                    addToCart.setEnabled(false);

                    updateCart();

                    checkOut.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
