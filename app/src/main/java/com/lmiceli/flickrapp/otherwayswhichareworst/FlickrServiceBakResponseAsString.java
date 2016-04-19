package com.lmiceli.flickrapp.otherwayswhichareworst;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lmiceli on 30/03/2016.
 *
 * https://api.flickr.com/services/feeds/photos_public.gne?tags=red%20truck&tagmode=ALL&format=json&nojsoncallback=1
 */
public interface FlickrServiceBakResponseAsString {

    @GET("services/feeds/photos_public.gne?tagmode=ALL&format=json&nojsoncallback=1")
    Observable<ResponseBody> getPhotosData(@Query("tags") String tags);

//    @GET("services/feeds/photos_public.gne?tags={tags}&tagmode=ANY&format=json&nojsoncallback=1")
//    Observable<String> getPhotosDataMatchAny(@Path("tags") String tags);

    String FLICKR_BASE_URL = "https://api.flickr.com/";

    /*static String getTagmode(boolean matchAll){
        return matchAll ? "ALL" : "ANY";
    }*/

    Retrofit retrofit = new Retrofit.Builder()

            .baseUrl(FLICKR_BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();
//
//    static FlickrService getWebService(){
//
////        ResponseBody responseBody = new ResponseBody() {
////            @Override
////            public MediaType contentType() {
////                return null;
////            }
////
////            @Override
////            public long contentLength() {
////                return 0;
////            }
////
////            @Override
////            public BufferedSource source() {
////                return null;
////            }
////        };
////
////        responseBody.t
//
//        return retrofit.create(FlickrService.class);
//    }

}