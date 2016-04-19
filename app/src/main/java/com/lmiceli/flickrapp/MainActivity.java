package com.lmiceli.flickrapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.lmiceli.flickrapp.img.FlickrRecyclerViewAdapter;
import com.lmiceli.flickrapp.listener.RecyclerItemClickListener;
import com.lmiceli.flickrapp.model.FlickrResponse;
import com.lmiceli.flickrapp.view.Mapper;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.lmiceli.flickrapp.constants.Constants.DEFAULT_WHEN_EMPTY;
import static com.lmiceli.flickrapp.service.FlickrServiceFactory.getWebService;


public class MainActivity extends BaseActivity {

//    TODO export intellij preferences to a git repo

    private static final String TAG = "MainActivity";
    @Bind(R.id.recycler_view)
    public RecyclerView recyclerView;

    private FlickrRecyclerViewAdapter viewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        activateToolbar();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAdapter = new FlickrRecyclerViewAdapter(MainActivity.this);
        recyclerView.setAdapter(viewAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this, "Normal tap", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ViewPhotoDetailsActivity.class);
                intent.putExtra(PHOTO_TRANSFER, viewAdapter.getPhoto(position));
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(MainActivity.this, "Title: " + viewAdapter.getPhoto(position).getTitle(), Toast.LENGTH_SHORT).show();

            }
        }));

        // get flickr data from web in onResume which android calls after on create

    }

    @Override
    protected void onResume() {
        super.onResume();
        // get flickr data from web
        String query = getSavedPreferenceData(FLICKR_QUERY);

        // TODO we can check if adapting FlickrRecyclerViewAdapter to take them one by one

        getWebService()
                .getPhotosData(query)
                .map(FlickrResponse::getItems)
                .flatMap(Observable::from) // Observable<List<Item>> to Observable<Item>
                .map(Mapper::map) // Item to Photo
                .toList() // For now we need a List<Photos>
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(viewAdapter::loadNewItems);
    }

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
    // TODO the app leaks around 1 mb per different search, probably not in the image handling but in the web connection.
}
