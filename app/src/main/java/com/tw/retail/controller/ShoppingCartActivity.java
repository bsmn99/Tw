package com.tw.retail.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tw.retail.R;
import com.tw.retail.model.SubCategory;

import java.text.MessageFormat;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ShoppingCartActivity extends BaseActivity {
    /**
     * LinearLayout used to add dynamic cart item views.
     */
    LinearLayout cartContainer;
    /**
     * RelativeLayout used to show empty cart view.
     */
    RelativeLayout emptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppingcart);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        emptyView = findViewById(R.id.emptyView);

        cartContainer = findViewById(R.id.cartContainer);

        RealmResults<SubCategory> subCategories = getCartItems();

        subCategories.addChangeListener(new RealmChangeListener<RealmResults<SubCategory>>() {
            @Override
            public void onChange(RealmResults<SubCategory> subCategories) {
                if(subCategories.size() == 0) {
                    showEmptyCart();
                }
            }
        });

        if(subCategories.size() > 0) {
            emptyView.setVisibility(View.GONE);
            cartContainer.setVisibility(View.VISIBLE);

            populateCartItems(subCategories);
        } else {
            showEmptyCart();
        }

        setTotal(subCategories);
    }

    /**
     * Method to set Total of all added cart items.
     * @param subCategories
     */
    private void setTotal(RealmResults<SubCategory> subCategories) {

        if(subCategories.size() > 0) {
            Number totalAmount = subCategories.sum("price");

            getSupportActionBar().setSubtitle(
                    MessageFormat.format("{0} items - ₹{1}", String.valueOf(subCategories.size()),
                                         totalAmount));
        } else {
            getSupportActionBar().setSubtitle(
                    MessageFormat.format("{0} items", String.valueOf(subCategories.size())));
        }
    }

    /**
     * Method to show empty cart view.
     */
    private void showEmptyCart() {
        emptyView.setVisibility(View.VISIBLE);
        cartContainer.setVisibility(View.GONE);
    }

    /**
     * Method to inflate dynamic view for cart items.
     * @param subCategories
     */
    private void populateCartItems(final RealmResults<SubCategory> subCategories) {
        for(int i=0;i<subCategories.size();i++) {

            final SubCategory subCategory = subCategories.get(i);

            LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            assert vi != null;

            final View subView = vi.inflate(R.layout.adapter_shoppingcart, null);

            TextView productName = subView.findViewById(R.id.name);
            ImageView imageView = subView.findViewById(R.id.image);
            TextView price = subView.findViewById(R.id.price);
            TextView removeFromCart = subView.findViewById(R.id.removeFromCart);

            assert subCategory != null;
            productName.setText(subCategory.getName());

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("₹ ").append(subCategory.getPrice());

            price.setText(stringBuilder);

            int resourceId = getResources().getIdentifier(subCategory.getImage(), "drawable", getPackageName());
            imageView.setImageResource(resourceId);

            removeFromCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    realm.beginTransaction();
                    subCategory.setAddedToCart(false);
                    realm.commitTransaction();

                    cartContainer.removeView(subView);

                    setTotal(subCategories);
                }
            });

            subView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoProductDetails(subCategory.getId(), ShoppingCartActivity.this);
                }
            });

            cartContainer.addView(subView);

            if(i == subCategories.size()-1) {
                final View checkOutView = vi.inflate(R.layout.checkoutbutton, null);

                Button button = checkOutView.findViewById(R.id.checkOut);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(ShoppingCartActivity.this, getString(R.string.proceed_to_checkout),
                                       Toast.LENGTH_SHORT).show();
                    }
                });

                cartContainer.addView(checkOutView);
            }
        }
    }
}
