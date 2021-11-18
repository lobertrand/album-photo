package fr.albumphoto.model;

import java.util.ArrayList;
import java.util.List;

public class Album {

    private String name;
    private List<Page> pages = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Album setName(String name) {
        this.name = name;
        return this;
    }

    public List<Page> getPages() {
        return pages;
    }

    public Album setPages(List<Page> pages) {
        this.pages = pages;
        return this;
    }

    @Override
    public String toString() {
        return "Album{" +
                "name='" + name + '\'' +
                ", pages=" + pages +
                '}';
    }
}
