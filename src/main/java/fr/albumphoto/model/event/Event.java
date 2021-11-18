package fr.albumphoto.model.event;

import fr.albumphoto.model.Page;

public class Event<T> {

    public static final Event<Page> ALBUM_PAGE_ADDED = new Event<>();
    public static final Event<String> GALLERY_IMAGE_ADDED = new Event<>();

    private Event() {}

}
