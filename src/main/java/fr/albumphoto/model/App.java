package fr.albumphoto.model;

import fr.albumphoto.model.event.EventRegister;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class App {

    private Album album;
    private Gallery gallery;

    public final EventRegister events = new EventRegister();

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
        var imageFolder = new File("/home/loic/Bureau/sample_images");
        var imageFiles = imageFolder.listFiles();
        if (imageFiles == null) {
            System.err.println("Sample image files not found");
            imageFiles = new File[0];
        }

        var imagePaths = Arrays.stream(imageFiles)
                               .map(File::getAbsolutePath)
                               .collect(Collectors.toList());

        var albumPages = new ArrayList<Page>();
        var page = Page.namedFromImagePath(imagePaths.get(0));
        albumPages.add(page);

        var app = new App();
        app.gallery = new Gallery()
                .setImagePaths(imagePaths);
        app.album = new Album()
                .setName("Album sans nom")
                .setPages(albumPages);
        return app;
    }
}
