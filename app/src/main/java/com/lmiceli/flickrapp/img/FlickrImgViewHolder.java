package com.lmiceli.flickrapp.img;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmiceli.flickrapp.R;

/**
 * Created by lmiceli on 31/03/2016.
 *
 */
public class FlickrImgViewHolder extends RecyclerView.ViewHolder{

    private ImageView thumbnail;
    private TextView title;


    public FlickrImgViewHolder(View view) {
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        this.title = (TextView) view.findViewById(R.id.title);
    }

    public ImageView getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImageView thumbnail) {
        this.thumbnail = thumbnail;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }
}
