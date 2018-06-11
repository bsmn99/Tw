package com.tw.retail.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tw.retail.OnClickItemListener;
import com.tw.retail.R;
import com.tw.retail.model.Category;
import com.tw.retail.model.SubCategory;

import io.realm.RealmList;

/**
 * Adapter class used to Bind views to RecyclerView
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private RealmList<Category> categories;

    private OnClickItemListener onClickItemListener;

    /**
     * Constructor receiving the interface object for click events
     * @param onClickItemListener
     */
    public CustomAdapter(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_category, parent, false);
        return new ViewHolder(v, parent.getContext(), onClickItemListener);
    }


    /**
     * Set Data object to adapter for binding with views
     * @param categories
     */
    public void setData(RealmList<Category> categories) {
        this.categories = categories;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        assert categories.get(position) != null;
        holder.bind(categories.get(position));
    }


    @Override
    public int getItemCount() {
        if(categories!=null && categories.size()>0) {
            return categories.size();
        }

        return 0;
    }

    /**
     * ViewHolde class for each item view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text;
        private LinearLayout subCategoryContainer;
        Context context;
        OnClickItemListener onClickItemListener;

        ViewHolder(View itemView, Context context, OnClickItemListener onClickItemListener) {
            super(itemView);
            this.context = context;
            this.onClickItemListener = onClickItemListener;

            text = itemView.findViewById(R.id.textView);
            subCategoryContainer = itemView.findViewById(R.id.subCategoryContainer);
        }

        /**
         * View binding with values are done here.
         * @param category
         */
        private void bind(Category category) {
            text.setText(category.getName());

            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(subCategoryContainer.getVisibility() == View.VISIBLE) {
                        subCategoryContainer.setVisibility(View.GONE);
                    } else {
                        subCategoryContainer.setVisibility(View.VISIBLE);
                    }
                }
            });

            for(int i=0;i<category.getSubCategory().size();i++) {
                assert category.getSubCategory().get(i) != null;
                setSubCategoryViews(category.getSubCategory().get(i), i);
            }
        }

        /**
         * SubCategory views are added dynamically
         * @param subCategoryViews
         * @param position
         */
        private void setSubCategoryViews(final SubCategory subCategoryViews, final int position) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert vi != null;
            View subView = vi.inflate(R.layout.adapter_subcategory, null);

            TextView subCategoryName = subView.findViewById(R.id.textView);

            subCategoryName.setText(subCategoryViews.getName());

            subCategoryName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItemListener.onClick(subCategoryViews, position);
                }
            });

            subCategoryContainer.addView(subView);
        }
    }
}
