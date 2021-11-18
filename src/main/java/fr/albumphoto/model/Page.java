package fr.albumphoto.model;

public class Page {

    private String title;
    private String imagePath;

    public String getTitle() {
        return title;
    }

    public Page setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getImagePath() {
        return imagePath;
    }

    public Page setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }

    @Override
    public String toString() {
        return "Page{" +
                "title='" + title + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
