package com.lmiceli.flickrapp.flickrdata;

import android.net.Uri;

/**
 * Created by lmiceli on 09/04/2016.
 */
public class FlickrUrlBuilder {

//    "https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1
    //https://api.flickr.com/services/feeds/photos_public.gne?tags=jojo&tagmode=ALL&format=json&nojsoncallback=1
    //https://api.flickr.com/services/feeds/photos_public.gne?tags=red%20truck&tagmode=ALL&format=json&nojsoncallback=1


    private static final String FLICKR_API_BASE_URL = "https://api.flickr.com/services/feeds/photos_public.gne";
    private static final String TAGS_PARAM = "tags";
    private static final String TAGMODE_PARAM = "tagmode";
    private static final String FORMAT_PARAM = "format";
    private static final String NO_JSON_CALLBACK_PARAM = "nojsoncallback";

    public static String buildUrl(String searchCriteria, boolean matchAll){

        Uri destinationUri = Uri.parse(FLICKR_API_BASE_URL).buildUpon()
                .appendQueryParameter(TAGS_PARAM,searchCriteria)
                .appendQueryParameter(TAGMODE_PARAM, matchAll ? "ALL" : "ANY")
                .appendQueryParameter(FORMAT_PARAM, "json")
                .appendQueryParameter(NO_JSON_CALLBACK_PARAM, "1")
                .build();

        return destinationUri.toString();
    }

}
