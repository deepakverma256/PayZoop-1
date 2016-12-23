package com.objectedge.payzoop.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.objectedge.payzoop.OCCApplication;
import com.objectedge.payzoop.R;
import com.objectedge.payzoop.event.CategoryClickEvent;
import com.objectedge.payzoop.model.CategoryModel;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;


/**
 * Created by sachin.yadav on 02-08-2016.Test comment
 */
public class NavigationDrawerRecyclerAdapter extends RecyclerView.Adapter<NavigationDrawerRecyclerAdapter.Holder>{

    @Inject
    LayoutInflater mInflater;

    @Inject
    EventBus mEventBus;

    public List<CategoryModel> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryModel> categories) {
        this.categories.addAll(categories);
    }

    private List<CategoryModel> categories;

    private Context mContext;


    public NavigationDrawerRecyclerAdapter(Context context, List<CategoryModel> objects) {
        OCCApplication.getRootComponent().inject(this);
        categories = objects;
        mContext = context;
    }

    @Override
    public NavigationDrawerRecyclerAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_tile, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(NavigationDrawerRecyclerAdapter.Holder holder, int position) {
        holder.catNameTv.setText(categories.get(position).getDisplayName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final String TAG = Holder.class.getSimpleName();

        LinearLayout catListView;
        TextView  catNameTv;
        ImageView catIconView;

        public Holder(View itemView) {
            super(itemView);
            catListView = (LinearLayout) itemView.findViewById(R.id.category_list_item);
            catNameTv = (TextView) itemView.findViewById(R.id.category_name);
            catIconView = (ImageView) itemView.findViewById(R.id.category_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG,"position clicked = " + getLayoutPosition());
            mEventBus.post(new CategoryClickEvent.FreshListingPageEvent(categories.get(getLayoutPosition())));
            //mEventBus.post(new CategoryClickEvent.ListProductsEvent(categories.get(getLayoutPosition())));
        }
    }
}
