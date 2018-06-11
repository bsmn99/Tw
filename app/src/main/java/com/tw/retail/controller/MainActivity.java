package com.tw.retail.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tw.retail.OnClickItemListener;
import com.tw.retail.R;
import com.tw.retail.adapter.CustomAdapter;
import com.tw.retail.model.Retail;
import com.tw.retail.model.SubCategory;
import com.tw.retail.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.RealmResults;

/**
 * Initial Activity to display category list and subcategory list.
 */
public class MainActivity extends BaseActivity implements OnClickItemListener {

    /**
     * Recyclerview for category list.
     */
    RecyclerView categoryListRecyclerView;
    /**
     * Activity context
     */
    Context context = MainActivity.this;
    /**
     * Adapter object to bind with categoryListRecyclerView.
     */
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryListRecyclerView = (RecyclerView) findViewById(R.id.categoryListRecyclerView);
        categoryListRecyclerView.setHasFixedSize(true);
        categoryListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoryListRecyclerView.setItemAnimator(new DefaultItemAnimator());

        setInitialDataLoad();

        populateView();
    }

    /**
     * Method to set customAdapter adapter and populate the categoryListRecyclerView
     */
    private void populateView() {

        getSupportActionBar().setTitle(getString(R.string.category));

        RealmResults<Retail> retailResults = getCategorys();

        Retail retail = retailResults.get(0);

        customAdapter = new CustomAdapter(this);

        assert retail != null;
        customAdapter.setData(retail.getData().getCategory());

        categoryListRecyclerView.setAdapter(customAdapter);
    }

    /**
     * Method to Load data from assest for one single time after application installed.
     */
    private void setInitialDataLoad() {

        if(!Utils.getOnInitialDataLoad(context)) {
            try {
                JSONObject jsonObject = new JSONObject(Utils.getdataFromLocalJson(context));

                realm.beginTransaction();

                realm.createObjectFromJson(Retail.class, jsonObject);

                realm.commitTransaction();

                Utils.setOnInitialDataLoad(true, this);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateCart();
    }

    @Override
    public void onClick(SubCategory subCategory, int pos) {
        gotoProductDetails(subCategory.getId(), this);
    }
}
