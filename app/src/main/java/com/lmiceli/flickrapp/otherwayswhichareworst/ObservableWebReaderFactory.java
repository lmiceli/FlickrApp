package com.lmiceli.flickrapp.otherwayswhichareworst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by lmiceli on 30/03/2016.
 */
public class ObservableWebReaderFactory {

    private String LOG_TAG = ObservableWebReaderFactory.class.getSimpleName();

    public static Observable<String> createWebReader(String address) {

        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> observer) {
                try {
                    String webData = getWebData(address);
                    observer.onNext(webData);
                } catch (Exception e) {
                    observer.onError(e);
                }
                // why is not making difference on memory?
                // apparently one has to be careful when to call this
                // this may not be the place, not sure what will happen when many subscribers
                observer.onCompleted();
            }
        });

    }

    /**
     *
     * @param address
     * @return
     */
    private static String getWebData(String address) {
        StringBuffer buffer = new StringBuffer();

        if (address == null) {
            return null;
        }

        URL url = null;
        BufferedReader in = null;
        try {
            url = new URL(address);
            in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                buffer.append(inputLine);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return buffer.toString();
    }

}