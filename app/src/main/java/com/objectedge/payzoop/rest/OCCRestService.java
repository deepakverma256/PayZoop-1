package com.objectedge.payzoop.rest;


import com.objectedge.payzoop.OCCApplication;
import com.objectedge.payzoop.event.RestEvent;
import com.objectedge.payzoop.model.APIKey;
import com.objectedge.payzoop.model.APIMetadata;
import com.objectedge.payzoop.model.GetAllCategoriesListModel;
import com.objectedge.payzoop.model.GetProductsListModel;
import com.objectedge.payzoop.model.MenuModel;
import com.objectedge.payzoop.model.ProductModel;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by michaelkatkov on 8/4/16.
 */
public class OCCRestService {

    @Inject
    Api mApi;

    @Inject
    EventBus mEventBus;

    public OCCRestService() {
        OCCApplication.getRootComponent().inject(this);
    }

    public void getProducts() {
        mApi.getProducts().enqueue(new Callback<GetProductsListModel>() {    //api call for getting list of products
            @Override
            public void onResponse(Call<GetProductsListModel> call, Response<GetProductsListModel> response) {
                if(response.isSuccessful()) {
                    mEventBus.post(new RestEvent.GetProductsListSuccessEvent(response.body()));
                } else {
                    mEventBus.post(new RestEvent.GetProductsListFailureEvent());
                }
            }

            @Override
            public void onFailure(Call<GetProductsListModel> call, Throwable t) {
                mEventBus.post(new RestEvent.GetProductsListFailureEvent());
            }
        });
    }


    public void getProductById(String id) {
        mApi.getProductById(id).enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                if(response.isSuccessful()) {
                    mEventBus.post(new RestEvent.GetProductByIdSuccessEvent(response.body()));
                } else {
                    mEventBus.post(new RestEvent.GetProductByIdFailureEvent());
                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                mEventBus.post(new RestEvent.GetProductByIdFailureEvent());
            }
        });
    }

    public void getProductByOffset(int offset,int limit) {
        mApi.getProductByOffset(offset,limit).enqueue(new Callback<GetProductsListModel>() {    //api call for getting list of products
            @Override
            public void onResponse(Call<GetProductsListModel> call, Response<GetProductsListModel> response) {
                if(response.isSuccessful()) {
                    mEventBus.post(new RestEvent.GetProductsListSuccessEvent(response.body()));
                } else {
                    mEventBus.post(new RestEvent.GetProductsListFailureEvent());
                }
            }

            @Override
            public void onFailure(Call<GetProductsListModel> call, Throwable t) {
                mEventBus.post(new RestEvent.GetProductsListFailureEvent());
            }
        });
    }

    public void getAllCategories() {
        mApi.getCategories().enqueue(new Callback<GetAllCategoriesListModel>() {    //api call for getting list of products
            @Override
            public void onResponse(Call<GetAllCategoriesListModel> call, Response<GetAllCategoriesListModel> response) {
                if(response.isSuccessful()) {
                    mEventBus.post(new RestEvent.GetCategoriesListSuccessEvent(response.body()));
                } else {
                    mEventBus.post(new RestEvent.GetProductsListFailureEvent());
                }
            }

            @Override
            public void onFailure(Call<GetAllCategoriesListModel> call, Throwable t) {
                mEventBus.post(new RestEvent.GetProductsListFailureEvent());
            }
        });
    }
    public void getProductsForCategory(String categoryId, int offset,int limit) {
        mApi.getProductsForCategory(categoryId,offset,limit).enqueue(new Callback<GetProductsListModel>() {    //api call for getting list of products
            @Override
            public void onResponse(Call<GetProductsListModel> call, Response<GetProductsListModel> response) {
                if(response.isSuccessful()) {
                    mEventBus.post(new RestEvent.GetProductsListSuccessEvent(response.body()));
                } else {
                    mEventBus.post(new RestEvent.GetProductsListFailureEvent());
                }
            }

            @Override
            public void onFailure(Call<GetProductsListModel> call, Throwable t) {
                mEventBus.post(new RestEvent.GetProductsListFailureEvent());
            }
        });
    }

    public void getAuthKey(APIMetadata apiMetaData) {
        mApi.getAuthKey(apiMetaData).enqueue(new Callback<APIKey>() {    //api call for get session authentication key
            @Override
            public void onResponse(Call<APIKey> call, Response<APIKey> response) {
                if(response.isSuccessful()) {
                    mEventBus.post(new RestEvent.GetAPIKeySuccessEvent(response.body()));
                } else {
                    mEventBus.post(new RestEvent.GetAPIKeyFailureEvent(response.toString()));
                }
            }

            @Override
            public void onFailure(Call<APIKey> call, Throwable t) {
                mEventBus.post(new RestEvent.GetAPIKeyFailureEvent("Failed without response"));
            }
        });
    }

    public void getProductByBarcode(String authKey, String barcode) {
        mApi.getProductByBarcode(authKey, barcode).enqueue(new Callback<ProductModel>() {
            @Override
            public void onResponse(Call<ProductModel> call, Response<ProductModel> response) {
                if(response.isSuccessful()) {
                    mEventBus.post(new RestEvent.GetProductByBarcodeSuccessEvent(response.body()));
                } else {
                    mEventBus.post(new RestEvent.GetProductByBarcodeFailureEvent());
                }
            }

            @Override
            public void onFailure(Call<ProductModel> call, Throwable t) {
                mEventBus.post(new RestEvent.GetProductByBarcodeFailureEvent());
            }
        });
    }

    public void getMenuFromAPI(String authKey) {
        mApi.getDishFromAPI(authKey).enqueue(new Callback<MenuModel>() {
            @Override
            public void onResponse(Call<MenuModel> call, Response<MenuModel> response) {
                if(response.isSuccessful()) {
                    mEventBus.post(new RestEvent.GetMenuSuccessEvent(response.body()));
                } else {
                    mEventBus.post(new RestEvent.GetMenuFailureEvent());
                }
            }

            @Override
            public void onFailure(Call<MenuModel> call, Throwable t) {
                mEventBus.post(new RestEvent.GetMenuFailureEvent());
            }
        });
    }
}
