package fr.albumphoto.model;

import fr.albumphoto.model.event.EventRegister;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AppState {

    private Album album;
    private Gallery gallery;

    public final EventRegister events = new EventRegister();

    private static AppState instance;

    private AppState() {}

    public Album getAlbum() {
        return album;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public static AppState getInstance() {
        if (instance == null) {
            instance = makeDefaultAppState();
        }
        return instance;
    }

    private static AppState makeDefaultAppState() {
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
        var page = new Page().setTitle("Première image").setImagePath(imagePaths.get(0));
        albumPages.add(page);

        var appState = new AppState();
        appState.gallery = new Gallery()
                .setImagePaths(imagePaths);
        appState.album = new Album()
                .setName("Album sans nom")
                .setPages(albumPages);
        return appState;
    }
}
