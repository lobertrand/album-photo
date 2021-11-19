package fr.albumphoto.model;

import fr.albumphoto.model.event.EventEmitter;

import java.io.File;
import java.util.ArrayList;

public class App {

    private Album album;
    private Gallery gallery;

    public final EventEmitter events = new EventEmitter();

    private static App instance;

    private App() {}

    public Album getAlbum() {
        return album;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public static App getInstance() {
        if (instance == null) {
            instance = makeDefaultApp();
        }
        return instance;
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
            albumPages.add(Page.namedFromImagePath(imagePaths.get(0)));
        }

        // Crée le modèle initial de l'application
        var app = new App();
        app.gallery = new Gallery()
                .setImagePaths(imagePaths);
        app.album = new Album()
                .setName("Album sans nom")
                .setPages(albumPages);
        return app;
    }
}
