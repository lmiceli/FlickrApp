package com.lmiceli.flickrapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.lmiceli.flickrapp.view.Photo;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

public class ViewPhotoDetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_details);

        activateToolbarWithHomeEnabled();

        Intent intent = getIntent();
        Photo photo = (Photo) intent.getSerializableExtra(PHOTO_TRANSFER);

        TextView photoTitle = (TextView) findViewById(R.id.photo_title);
        photoTitle.setText("Title: " + photo.getTitle());

        TextView photoTags = (TextView) findViewById(R.id.photo_tags);
        photoTags.setText("Tags: " + photo.getTags());

        TextView photoAuthor = (TextView) findViewById(R.id.photo_author);
        photoAuthor.setText(photo.getAuthor());

        ImageView photoImage = (ImageView) findViewById(R.id.photo_image);

        // doing in 2 steps seem to reduce waiting time (img is got from internet
        // while the imageView is inflated)
        RequestCreator rc = Picasso.with(ViewPhotoDetailsActivity.this).load(photo.getImgHighRes())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder);

        // I need to do this after
        photoImage.post(new Runnable() {
            @Override
            public void run() {
                rc
                // 0 height means this dimension is set free to accomodate
                // to desired width without affectin image ratio
                .resize(photoImage.getWidth(), 0)
                .into(photoImage);
            }
        });




    }
}
