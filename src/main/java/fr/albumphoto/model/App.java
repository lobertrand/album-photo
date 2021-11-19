package fr.albumphoto.model;

import fr.albumphoto.model.event.Event;
import fr.albumphoto.model.event.EventEmitter;

import java.io.File;
import java.util.ArrayList;

public class App {

    private Album album;
    private Gallery gallery;
    private int currentPageIndex;

    public final EventEmitter events = new EventEmitter();

    private static App instance;

    public static App getInstance() {
        if (instance == null) {
            instance = makeDefaultApp();
        }
        return instance;
    }

    private App() {}

    public Album getAlbum() {
        return album;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    public Page getCurrentPage() {
        return album.getPages().get(currentPageIndex);
    }

    private static App makeDefaultApp() {
        var imagePaths = new ArrayList<String>();
        var albumPages = new ArrayList<Page>();

        // Tente de remplir la galerie et l'album avec des images d'exemple
        var imageFolder = new File("sample_images");
        var imageFiles = imageFolder.listFiles();
        if (imageFiles == null) {
            System.err.println("Folder 'sample_images' not found");
        } else {
            for (File imageFile : imageFiles) {
                imagePaths.add(imageFile.getAbsolutePath());
            }
            var page = Page.fromImagePath(imagePaths.get(0));
            albumPages.add(page);
        }

        // Crée le modèle initial de l'application
        var app = new App();
        app.gallery = new Gallery()
                .setImagePaths(imagePaths);
        app.album = new Album()
                .setName("Album sans nom")
                .setPages(albumPages);
        app.currentPageIndex = 0;
        return app;
    }

    // Fonctionnalités de l'application

    public void addGalleryImage(String imagePath) {
        gallery.getImagePaths().add(imagePath);
        events.emitEvent(Event.GALLERY_IMAGE_ADDED, imagePath);
    }

    public void addAlbumPage(String imagePath) {
        var page = Page.fromImagePath(imagePath);
        album.getPages().add(page);
        events.emitEvent(Event.ALBUM_PAGE_ADDED, page);
    }

    public void previousPage() {
        var pagesCount = album.getPages().size();
        if (pagesCount > 0) {
            currentPageIndex = (currentPageIndex + pagesCount - 1) % pagesCount;
        } else {
            currentPageIndex = 0;
        }
        events.emitEvent(Event.ALBUM_PAGE_TURNED, getCurrentPage());
    }

    public void nextPage() {
        var pagesCount = album.getPages().size();
        if (pagesCount > 0) {
            currentPageIndex = (currentPageIndex + 1) % pagesCount;
        } else {
            currentPageIndex = 0;
        }
        events.emitEvent(Event.ALBUM_PAGE_TURNED, getCurrentPage());
    }
}
