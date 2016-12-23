package com.objectedge.payzoop.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.objectedge.payzoop.EndlessRecyclerViewScrollListener;
import com.objectedge.payzoop.OCCApplication;
import com.objectedge.payzoop.R;
import com.objectedge.payzoop.adapter.NavigationDrawerRecyclerAdapter;
import com.objectedge.payzoop.adapter.ProductRecyclerAdapter;
import com.objectedge.payzoop.camera.BarCodeScanner;
import com.objectedge.payzoop.credentials.DeveloperKey;
import com.objectedge.payzoop.event.CartEvent;
import com.objectedge.payzoop.event.ListingPageClickEvent;
import com.objectedge.payzoop.event.RestEvent;
import com.objectedge.payzoop.model.APIMetadata;
import com.objectedge.payzoop.model.Cart;
import com.objectedge.payzoop.model.CategoryModel;
import com.objectedge.payzoop.model.ProductModel;
import com.objectedge.payzoop.rest.OCCRestService;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;


public class ProductListingActivity extends GenericActivity {

    private static final String TAG = ProductListingActivity.class.getSimpleName();
    public static final String KEY_ID = "id";

    @Inject
    OCCRestService mOCCRestService;

    @Inject
    EventBus mEventBus;

    @BindView(R.id.product_listing_recylcer)
    RecyclerView recyclerView;

    @BindView (R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.left_drawer)
    RecyclerView drawerList;

    @BindString(R.string.get_product_error)
    String getProductError;

    @BindView(R.id.product_list_progress)
    ProgressBar progressView;

    //@BindView(R.id.scan_format)
    private TextView formatTxt;

    //@BindView(R.id.scan_content)
    private TextView contentTxt;

    @Inject
    Cart cart;

    BarCodeScanner barCodeScanner;

    private String currentCategory;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

    private ProductRecyclerAdapter productListAdapter;
    private NavigationDrawerRecyclerAdapter categoryListAdapter;

    private Boolean initialRun = true;

    private int offset=0;
    private int limit=5;
    private Boolean beginning = true;

    ProductListingActivity(){
        BarCodeScanner bcs = new BarCodeScanner();
        barCodeScanner = bcs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_listing);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ButterKnife.bind(this);//bind butterknife
        //showProgress(true);
        OCCApplication.getRootComponent().inject(ProductListingActivity.this); //inject activity into RootComponent
        initGrid();
        initiaization();
        mEventBus.register(this);//register Events Catcher
    }

    private void initiaization() {
        mOCCRestService.getAuthKey(new APIMetadata("admin","admin"));
    }

    public void onEventMainThread(RestEvent.GetAPIKeySuccessEvent event) {
        DeveloperKey.setAPIKey(event.key);
        Toast toast = Toast.makeText(getApplicationContext(),DeveloperKey.APIKey.getId_token(), Toast.LENGTH_SHORT);
        toast.show();
    }

    public void onEventMainThread(RestEvent.GetAPIKeyFailureEvent event) {
        //DeveloperKey.APIKey = event.key;
        Toast toast = Toast.makeText(getApplicationContext(),"API call failed "+event.response, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mEventBus.unregister(this); //unregister events catcher
    }

    //Currently adding this method to populate dummy product when barcode is scanned. This method would be changed to the event reciever to get the response from rest API once it is ready.
    public void onEventMainThread(RestEvent.GetProductByBarcodeSuccessEvent event) {
        productListAdapter.addProduct(event.product);
        addToCart(event.product);
        offset+=limit;
        showProgress(false);
        //productListAdapter.stillLoading = false;
        productListAdapter.notifyDataSetChanged();
    }

    public void onEventMainThread(RestEvent.GetProductByBarcodeFailureEvent event) {
        Toast toast = Toast.makeText(getApplicationContext(),"API call for product failed ", Toast.LENGTH_SHORT);
        toast.show();
    }

    private ProductModel getDummyProduct(){
        ProductModel dummyProd = new ProductModel();
        dummyProd.setProductName("NoteBook");
        dummyProd.setId("BK1");
        dummyProd.setListPrice("45.3");
        dummyProd.setDescription("This is an awesome looking and super lucky book. Padna mut pass ho jaogay.");
        dummyProd.setImageURL("");
        return dummyProd;
    }

    //Event Bus Actions
    public void onEventMainThread(CartEvent.UpdateCart event) {
        miniCartItemCountView.setText(String.valueOf(event.cartItemCount));
    }

    //Event Bus Action to redirect to the PDP page when the respective product is clicked in the Product Listing page.
    public void onEventMainThread(ListingPageClickEvent.GoToPDPEvent event) {
        PDPActivity.product = event.product;
        Intent myIntent = new Intent(ProductListingActivity.this, PDPActivity.class);
        myIntent.putExtra(KEY_ID, event.product.getId());
        startActivity(myIntent);
    }

    //This method performs the required declarations/initializations and is invoked only once in the beginning of activity creation.
    private void initGrid() {
        //formatTxt = (TextView) findViewById(R.id.scan_format);
        //contentTxt =  (TextView) findViewById(R.id.scan_content);

        productListAdapter = new ProductRecyclerAdapter(this, new ArrayList<ProductModel>());
        final LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(productListAdapter);

        endlessRecyclerViewScrollListener = new EndlessRecyclerViewScrollListener(layoutManager1) {

            @Override
            public void onLoadMore(int page, int totalItemsCount) {
               // productListAdapter.stillLoading = true;
                //showProgress(true);
                //productListAdapter.notifyDataSetChanged();
                if (currentCategory != null) {
                    //mOCCRestService.getProductsForCategory(currentCategory, offset, limit);
                } else {
                    //mOCCRestService.getProductByOffset(offset, limit);
                }
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerViewScrollListener);
        categoryListAdapter = new NavigationDrawerRecyclerAdapter(this, new ArrayList<CategoryModel>());

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // Set the adapter for the list view
        drawerList.setLayoutManager(layoutManager2);
        drawerList.setAdapter(categoryListAdapter);
    }

    private void flushAllProducts(){
        productListAdapter.flushProductList();
    }

    /**
     * Shows the progress UI.
     */
    private void showProgress(final boolean show) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
    }

    public void scanNow(View view){
        Snackbar.make(view, "Scaning started", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        barCodeScanner.scanNow(view,this);
    }

    /**
     * function handle scan result
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {
            //we have a result
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            mOCCRestService.getProductByBarcode(DeveloperKey.APIKey.getId_token(),scanContent);
            //onEventMainThread(scanFormat);
            mEventBus.post(new CartEvent.GetProductForBarcodeEvent(scanContent));
        }else{
            Toast toast = Toast.makeText(getApplicationContext(),"No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Boolean status = super.onCreateOptionsMenu(menu);
        miniCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProductListingActivity.this, CartActivity.class);
                startActivity(myIntent);
            }
        });
        return status;
    }
}
