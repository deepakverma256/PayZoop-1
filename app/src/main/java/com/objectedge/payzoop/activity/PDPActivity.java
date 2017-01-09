package com.objectedge.payzoop.activity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.objectedge.payzoop.OCCApplication;
import com.objectedge.payzoop.R;
import com.objectedge.payzoop.credentials.DeveloperKey;
import com.objectedge.payzoop.event.CartEvent;
import com.objectedge.payzoop.event.ListingPageClickEvent;
import com.objectedge.payzoop.event.RestEvent;
import com.objectedge.payzoop.model.Cart;
import com.objectedge.payzoop.model.ProductModel;
import com.objectedge.payzoop.rest.OCCRestService;
import com.objectedge.payzoop.rest.RestClient;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class PDPActivity extends GenericActivity implements YouTubePlayer.OnInitializedListener{
    public static ProductModel product;
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerFragment youTubePlayerFragment;

    public static final String VIDEO_ID = "o7VVHhK9zf0";

    @BindView(R.id.pdp_name)
    TextView mPdpNameTv;

    @BindView(R.id.pdp_price)
    TextView mPdpPriceTv;

    @BindView(R.id.pdp_desc)
    TextView mPdpDescTv;

    @BindView(R.id.pdp_prod_img)
    ImageView mPdpImgView;

    @BindView(R.id.addToCart)
    Button addToCart;

    @BindString(R.string.get_products_list_error)
    String getProductsListError;

    @Inject
    OCCRestService mOCCRestService;

    @Inject
    EventBus mEventBus;

    @Inject
    Cart cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);//bind butterknife
        OCCApplication.getRootComponent().inject(this);
        mEventBus.register(this);//register Events Catcher
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mEventBus.post(new CartEvent.AddToCart(product));
                addToCart();
            }
        });
        loadData();

        youTubePlayerFragment =(YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtubeplayerfragment);
        youTubePlayerFragment.initialize(DeveloperKey.DEVELOPER_KEY, this);
    }
    //Event Bus Action to redirect to the PDP page when the respective product is clicked in the Product Listing page.
    public void onEventMainThread(ListingPageClickEvent.GoToPDPEvent event) {
        product = event.product;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this); //unregister events catcher
    }

    //Event Bus Actions
    public void onEventMainThread(RestEvent.GetProductByIdSuccessEvent event) {
        setDataToView(event.productData);
    }

    public void onEventMainThread(RestEvent.GetProductsListFailureEvent event) {
        Toast.makeText(this, getProductsListError, Toast.LENGTH_LONG).show();
    }

    //Event Bus Actions
    public void onEventMainThread(CartEvent.UpdateCart event) {
        miniCartItemCountView.setText(String.valueOf(event.cartItemCount));
    }

    private void addToCart(){
        Toast.makeText(this, "Product added to cart : "+product.getId(), Toast.LENGTH_LONG).show();
        cart.addToCart(product);
        //miniCartItemCountView.setText(String.valueOf(cart.products.size()));
    }


    private void loadData() {
        String productId = getIntent().getStringExtra(ProductListingActivity.KEY_ID);
        if (productId != null) {
            mOCCRestService.getProductById(productId);
        } else {
            Toast.makeText(this, getProductsListError, Toast.LENGTH_LONG).show();
        }
    }


    private void setDataToView(ProductModel productData) {
        Picasso.with(getApplicationContext()).load(RestClient.BASE_URL + productData.getImageURL())
                .into(mPdpImgView);
        mPdpNameTv.setText(productData.getProductName());
        mPdpPriceTv.setText("$" + productData.getListPrice());
        mPdpDescTv.setText(productData.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
            this.youTubePlayer = player;
            if (!wasRestored) {
                player.cueVideo(VIDEO_ID);
            }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Boolean status = super.onCreateOptionsMenu(menu);
        miniCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(PDPActivity.this, CartActivity.class);
                startActivity(myIntent);
            }
        });
        return status;
    }
}
