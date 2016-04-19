package com.lmiceli.flickrapp.otherwayswhichareworst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.lmiceli.flickrapp.BaseActivity;
import com.lmiceli.flickrapp.R;
import com.lmiceli.flickrapp.SearchActivity;
import com.lmiceli.flickrapp.img.FlickrRecyclerViewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lmiceli.flickrapp.otherwayswhichareworst.ObservableWebReaderFactory.createWebReader;
import static com.lmiceli.flickrapp.constants.Constants.DEFAULT_WHEN_EMPTY;
import static com.lmiceli.flickrapp.flickrdata.FlickrUrlBuilder.buildUrl;


public class MainActivityBakNoRetrofit extends BaseActivity {


    private static final String TAG = "MainActivity";
    @Bind(R.id.recycler_view)
    public RecyclerView recyclerView;

    private FlickrRecyclerViewAdapter viewAdapter;
//    private Subscription sub;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        activateToolbar();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // get flickr data from web in onResume which android calls after on create

    }

    @Override
    protected void onResume() {
        super.onResume();
        // get flickr data from web
        String query = getSavedPreferenceData(FLICKR_QUERY);

//        unsubscribe();

//        if (query.length() > 0) {

            //                            .observeOn(Schedulers.immediate())
//            sub =
                    createWebReader(buildUrl(query, true))
                   .subscribeOn(Schedulers.io())
//                            .observeOn(Schedulers.immediate())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(s -> {
                       List<Photo> photos = FlickrJsonDataParser.parse(s);
//                       viewAdapter = new FlickrRecyclerViewAdapter(photos, MainActivityBakNoRetrofit.this);
                       recyclerView.setAdapter(viewAdapter);
                   });





//        }

    }

    /*private void unsubscribe() {
        if (sub != null){
            sub.unsubscribe();
            sub = null;
        }
    }*/

    private String getSavedPreferenceData(String key) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return sharedPref.getString(key, DEFAULT_WHEN_EMPTY);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.menu_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // will check after adding retrofit
    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
    }*/
}
