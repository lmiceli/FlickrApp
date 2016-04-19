package com.lmiceli.flickrapp.service;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lmiceli on 11/04/2016.
 */
public class FlickrServiceFactory {

    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(FlickrService.FLICKR_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    public static FlickrService getWebService(){
        return retrofit.create(FlickrService.class);
    }

}
