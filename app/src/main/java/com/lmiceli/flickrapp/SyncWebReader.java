package com.lmiceli.flickrapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lmiceli on 30/03/2016.
 */
public class SyncWebReader {

    private String LOG_TAG = SyncWebReader.class.getSimpleName();

    public String execute(String address) {

        StringBuffer buffer = new StringBuffer();

        if(address == null) {
            //  exception here?
            return null;
        }

        URL url = null;
        try {
            url = new URL(address);
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

}