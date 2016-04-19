package com.lmiceli.flickrapp.service;

import com.lmiceli.flickrapp.model.FlickrResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lmiceli on 30/03/2016.
 *
 * https://api.flickr.com/services/feeds/photos_public.gne?tags=red%20truck&tagmode=ALL&format=json&nojsoncallback=1
 */
public interface FlickrService {

    @GET("services/feeds/photos_public.gne?tagmode=ALL&format=json&nojsoncallback=1")
    Observable<FlickrResponse> getPhotosData(@Query("tags") String tags);

//    @GET("services/feeds/photos_public.gne?tags={tags}&tagmode=ANY&format=json&nojsoncallback=1")
//    Observable<String> getPhotosDataMatchAny(@Path("tags") String tags);

    String FLICKR_BASE_URL = "https://api.flickr.com/";

    /*static String getTagmode(boolean matchAll){
        return matchAll ? "ALL" : "ANY";
    }*/



}