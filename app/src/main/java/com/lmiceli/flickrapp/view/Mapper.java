package com.lmiceli.flickrapp.view;

import com.lmiceli.flickrapp.model.Item;

/**
 * Created by lmiceli on 12/04/2016.
 */
public interface Mapper {

    static Photo map(Item origin) {

        Photo result = new Photo();

        result.setAuthor(origin.getAuthor());
        result.setAuthorId(origin.getAuthorId());
        result.setDateTaken(origin.getDateTaken());
        result.setDescription(origin.getDescription());
        //FIXME throw some exception
        result.setImg(origin.getMedia().getM());
        result.setLink(origin.getLink());
        result.setPublished(origin.getPublished());
        result.setTags(origin.getTags());
        result.setTitle(origin.getTitle());

        return result;
    }

}
