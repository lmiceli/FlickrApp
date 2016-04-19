package com.lmiceli.flickrapp.otherwayswhichareworst;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_AUTHOR;
import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_AUTHOR_ID;
import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_DATE_TAKEN;
import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_DESCRIPTION;
import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_ITEMS;
import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_LINK;
import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_MEDIA;
import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_PIC_URL;
import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_PUBLISHED;
import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_TAGS;
import static com.lmiceli.flickrapp.constants.FlickrJsonConstants.TAG_TITLE;

/**
 * Created by lmiceli on 30/03/2016.
 */
//enum DownloadStatus { IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK}

public class FlickrJsonDataParser  {

    private String LOG_TAG = FlickrJsonDataParser.class.getSimpleName();

    public static List<Photo> parse(String flickrJsonString){

        List<Photo> flickerDocuments = new ArrayList<>();

        try {
            JSONObject mainObject = new JSONObject(flickrJsonString);
            String title = mainObject.getString(TAG_TITLE);
            JSONArray items = mainObject.getJSONArray(TAG_ITEMS);


            for (int i=0; i<items.length(); i++) {

                JSONObject item = items.getJSONObject(i);
                Photo doc = new Photo();

                doc.setTitle(item.getString(TAG_TITLE));
                doc.setLink(item.getString(TAG_LINK));

                JSONObject media = item.getJSONObject(TAG_MEDIA);
                doc.setMedia(media.getString(TAG_PIC_URL));

                doc.setDateTaken(item.getString(TAG_DATE_TAKEN));
                doc.setDescription(item.getString(TAG_DESCRIPTION));
                doc.setPublished(item.getString(TAG_PUBLISHED));
                doc.setAuthor(item.getString(TAG_AUTHOR));
                doc.setAuthorId(item.getString(TAG_AUTHOR_ID));
                doc.setTags(item.getString(TAG_TAGS));

                flickerDocuments.add(doc);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return flickerDocuments;
    }

}

