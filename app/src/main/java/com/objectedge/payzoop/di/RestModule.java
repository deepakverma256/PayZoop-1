package com.objectedge.payzoop.di;


import com.objectedge.payzoop.rest.Api;
import com.objectedge.payzoop.rest.OCCRestService;
import com.objectedge.payzoop.rest.RestClient;

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
