package com.lmiceli.flickrapp.otherwayswhichareworst;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lmiceli.flickrapp.R;
import com.lmiceli.flickrapp.img.FlickrImgViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lmiceli on 31/03/2016.
 *
 */
public class FlickrRecyclerViewAdapterBeforeGsonMapping extends RecyclerView.Adapter<FlickrImgViewHolder>{

    private List<Photo> photos;
    private Context context;
    private static final String TAG = "FlickrRecyclViewAdapter";

    public FlickrRecyclerViewAdapterBeforeGsonMapping(List<Photo> photos, Context context) {
        this.photos = photos;
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
        Picasso.with(context).load(photo.getMedia())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .into(holder.getThumbnail());
//        holder.getTitle().setText(photo.getTitle());
    }

    @Override
    public int getItemCount() {
        return (null != photos? photos.size() : 0);
    }
}
