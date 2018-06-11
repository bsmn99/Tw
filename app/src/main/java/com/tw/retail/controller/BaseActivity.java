package com.tw.retail.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tw.retail.R;
import com.tw.retail.model.Retail;
import com.tw.retail.model.SubCategory;
import com.tw.retail.utils.Utils;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Base Acitivity which holds common functionality's
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Realm Database object to access the table's
     */
    Realm realm;

    /**
     * Textview used to set cart count in toolbar.
     */
    TextView cartNotificationTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Initialize the Database object*/
        realm = Realm.getDefaultInstance();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(this instanceof ShoppingCartActivity) {
            menu.findItem(R.id.cart).setVisible(false);
        } else {
            menu.findItem(R.id.cart).setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem item1 = menu.findItem(R.id.cart);
        item1.setActionView(R.layout.badge_layout);
        RelativeLayout cartIcon = (RelativeLayout) item1.getActionView();

        cartNotificationTextView = cartIcon.findViewById(R.id.badgeTextView);

        Button cartButton = cartIcon.findViewById(R.id.button1);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoShoppingCart(BaseActivity.this);
            }
        };

        cartNotificationTextView.setOnClickListener(onClickListener);

        cartButton.setOnClickListener(onClickListener);

        updateCart();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.cart) {
            gotoShoppingCart(this);
            if(this instanceof ShoppingCartActivity) {
                finish();
            }
        } else if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*Remove all change listeners*/
        realm.removeAllChangeListeners();

        /*Closing the database object*/
        realm.close();
    }

    /**
     * @return - Get the Category list from database.
     */
    public RealmResults<Retail> getCategorys() {
        return realm.where(Retail.class).findAll();
    }

    /**
     * @return - Get the Cart list from database.
     */
    public RealmResults<SubCategory> getCartItems() {
        return realm.where(SubCategory.class)
                .equalTo("isAddedToCart",true).findAll();
    }

    /**
     * @param id - Subcategory Id
     * @return - Get the Subcategory list from database.
     */
    public SubCategory getSubCategory(String id) {
        return realm.where(SubCategory.class).equalTo("id", id).findFirst();
    }

    /**
     * Method to navigate to Product Details page
     * @param id Subcategory Id
     * @param context
     */
    public void gotoProductDetails(String id, Context context) {
        Intent intent = new Intent(context, ProductDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(Utils.SELECTED_PRODUCT, id);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * Method to navigate Shopping Cart page.
     * @param context
     */
    public void gotoShoppingCart(Context context) {
        Intent intent = new Intent(context, ShoppingCartActivity.class);
        context.startActivity(intent);
    }

    /**
     * Method to update the cart count in toolbar
     */
    public void updateCart() {
        if(cartNotificationTextView !=null) {
            cartNotificationTextView.setText(String.valueOf(getCartItems().size()));
        }
    }
}
