package com.lmiceli.flickrapp.otherwayswhichareworst;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lmiceli on 30/03/2016.
 */
//enum DownloadStatus { IDLE, PROCESSING, NOT_INITIALISED, FAILED_OR_EMPTY, OK}

public class AsyncWebReader extends AsyncTask<String, Void, String>  {

    private String LOG_TAG = AsyncWebReader.class.getSimpleName();
    //    private String mRawUrl;
//    private String mData;
    private AsyncTaskCompleteListener<String> callback;
//    private String finalResult;
//    private Dialog progressDialog;

//    private DownloadStatus mDownloadStatus;

    /*public FlickrReader(String mRawUrl) {
        this.mRawUrl = mRawUrl;
        this.mDownloadStatus = DownloadStatus.IDLE;
    }*/


    public AsyncWebReader(AsyncTaskCompleteListener<String> cb, String searchCriteria) {
        this.callback = cb;
    }

    @Override
    protected String doInBackground(String... params) {

        StringBuffer buffer = new StringBuffer();

        if(params == null)
            return null;

        URL url = null;
        try {
            url = new URL(params[0]);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                buffer.append(inputLine);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return buffer.toString();
    }

    @Override
    protected void onPostExecute(String result) {
//        finalResult = result;
//        progressDialog.dismiss();
        System.out.println("on Post execute called");
        callback.onTaskComplete(result);
    }
}