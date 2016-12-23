package com.objectedge.payzoop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.objectedge.payzoop.OCCApplication;
import com.objectedge.payzoop.R;
import com.objectedge.payzoop.event.ListingPageClickEvent;
import com.objectedge.payzoop.model.ProductModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;


/**
 * Created by sachin.yadav on 02-08-2016.Test comment
 */
public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.Holder>{

    @Inject
    LayoutInflater mInflater;

    @Inject
    EventBus mEventBus;

    public List<ProductModel> getProducts() {
        return mProducts;
    }

    public void addProducts(List<ProductModel> mProducts) {
        this.mProducts.addAll(mProducts);
    }

    public void addProduct(ProductModel product){
        this.mProducts.add(product);
    }

    public void flushProductList(){
        mProducts = new ArrayList<ProductModel>();
    }

    private List<ProductModel> mProducts;

    private Context mContext;

    private Currency currency;

    public Boolean stillLoading;

    public static final Map<String, String> MYCURRENCIES = new HashMap<String, String>(){
        {
            put("EUR","€");
            put("USD","$");
            put("IND","₹");
            put("BR","R$");
            put("UA","₴");
        }
    };


    public ProductRecyclerAdapter(Context context, List<ProductModel> objects) {
        OCCApplication.getRootComponent().inject(this);
        mProducts = objects;
        mContext = context;
        currency = Currency.getInstance(Locale.getDefault());
    }

    public void showScrollProgress(Boolean show){
        stillLoading = show;
        notifyDataSetChanged();
    }

    @Override
    public ProductRecyclerAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_tile, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(ProductRecyclerAdapter.Holder holder, int position) {
            holder.prodNameTv.setText(mProducts.get(position).getProductName());
            holder.prodPriceTv.setText(getCurrency().getSymbol() + mProducts.get(position).getListPrice());
            String imageURL = mProducts.get(position).getImageURL();
            Picasso.with(mContext).load(mProducts.get(position).getImageURL()).placeholder(R.mipmap.ic_launcher).into(holder.prodImgView);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }


    public class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final String TAG = Holder.class.getSimpleName();

        LinearLayout prodListView;
        TextView  prodNameTv;
        TextView  prodPriceTv;
        ImageView prodImgView;
        ProgressBar progressBar;

        public Holder(View itemView) {
            super(itemView);
            prodListView = (LinearLayout) itemView.findViewById(R.id.product_list_item);
            prodNameTv = (TextView) itemView.findViewById(R.id.grid_text);
            prodPriceTv = (TextView) itemView.findViewById(R.id.grid_text_price);
            prodImgView = (ImageView) itemView.findViewById(R.id.grid_image);
            progressBar = (ProgressBar) itemView.findViewById(R.id.product_scroll_progress);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG,"position clicked = " + getLayoutPosition());
            mEventBus.post(new ListingPageClickEvent.GoToPDPEvent(mProducts.get(getLayoutPosition())));
        }
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
