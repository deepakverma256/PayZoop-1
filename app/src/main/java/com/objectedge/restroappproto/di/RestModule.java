package com.objectedge.restroappproto.di;


import com.objectedge.restroappproto.rest.Api;
import com.objectedge.restroappproto.rest.OCCRestService;
import com.objectedge.restroappproto.rest.RestClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class RestModule {

    private RestClient mRestClient;


    public RestModule() {
        mRestClient = new RestClient();
    }


    @Provides
    @Singleton
    public RestClient provideRestClient() {
        return mRestClient;
    }


    @Provides
    @Singleton
    public Api provideApi() {
        return mRestClient.createService(Api.class);
    }


    @Singleton
    @Provides
    OCCRestService provideProductRestService() {
        return new OCCRestService();
    }
}
