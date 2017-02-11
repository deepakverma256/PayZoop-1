package com.objectedge.restroappproto.rest;


import com.objectedge.restroappproto.model.APIKey;
import com.objectedge.restroappproto.model.APIMetadata;
import com.objectedge.restroappproto.model.GetAllCategoriesListModel;
import com.objectedge.restroappproto.model.GetProductsListModel;
import com.objectedge.restroappproto.model.SubMenuModel;
import com.objectedge.restroappproto.model.ProductModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Api {

    @GET("ccstore/v1/products/")
    Call<GetProductsListModel> getProducts();

    @GET("ccstore/v1/products/{id}")
    Call<ProductModel> getProductById(@Path("id") String id);

    @GET("ccstore/v1/products/")
    Call<GetProductsListModel> getProductByOffset(@Query("offset") int offset, @Query("limit") int limit);

    @GET("ccstoreui/v1/collections/rootCategory/")
    Call<GetAllCategoriesListModel> getCategories();

    @GET("ccstore/v1/products/")
    Call<GetProductsListModel> getProductsForCategory(@Query("categoryId") String categoryId, @Query("offset") int offset, @Query("limit") int limit);

    @POST("api/authenticate")
    Call<APIKey> getAuthKey(@Body APIMetadata apiMetaData);

    @GET("api/products/barcode/{barcode}")
    Call<ProductModel> getProductByBarcode(@Header("Authorization") String authorization, @Path("barcode") String barcode);

    @GET("api/menu-items")
    Call<SubMenuModel> getDishFromAPI(@Header("Authorization") String authorization);
}
