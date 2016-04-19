package com.lmiceli.flickrapp.img;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmiceli.flickrapp.R;
import com.lmiceli.flickrapp.view.Photo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lmiceli on 31/03/2016.
 *
 */
public class FlickrRecyclerViewAdapter extends RecyclerView.Adapter<FlickrImgViewHolder>{

    private List<Photo> photos;
    private Context context;
    private static final String TAG = "FlickrRecyclViewAdapter";

    public FlickrRecyclerViewAdapter(Context context) {
        this.photos = new ArrayList<>();
        this.context = context;
    }

    @Override
    public FlickrImgViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.browse, null);
        FlickrImgViewHolder viewHolder = new FlickrImgViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FlickrImgViewHolder holder, int position) {

        Photo photo = photos.get(position);
        Log.d(TAG, "processing: " + photo.getTitle());
        Picasso.with(context).load(photo.getImg())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.getThumbnail());

        holder.getTitle().setText(photo.getTitle());
    }

    @Override
    public int getItemCount() {
        return (null != photos ? photos.size() : 0);
    }

    public void loadNewItems(List<Photo> newPhotos) {
        photos = newPhotos;
        log(photos);
        notifyDataSetChanged();
    }

    private void log(List<Photo> photos) {
        for (Photo photo : photos) {
            Log.d(TAG, "new photo: " + photo);
        }
    }

    public Photo getPhoto(int position) {
        return (null != photos ? photos.get(position) : null);
    }
}
