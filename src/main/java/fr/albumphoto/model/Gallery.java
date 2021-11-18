package fr.albumphoto.model;

import java.util.ArrayList;
import java.util.List;

public class Gallery {

    public List<String> imagePaths = new ArrayList<>();

    public List<String> getImagePaths() {
        return imagePaths;
    }

    public Gallery setImagePaths(List<String> imagePaths) {
        this.imagePaths = imagePaths;
        return this;
    }

    @Override
    public String toString() {
        return "Gallery{" +
                "imagePaths=" + imagePaths +
                '}';
    }
}
