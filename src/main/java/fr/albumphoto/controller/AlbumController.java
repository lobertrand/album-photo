package fr.albumphoto.controller;

import fr.albumphoto.model.AppState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static fr.albumphoto.model.event.Event.ALBUM_PAGE_ADDED;

public class AlbumController implements Initializable {

    @FXML
    public ImageView imageView;
    @FXML
    public Text pageTitle;
    @FXML
    public Text pageNumber;

    private int pageIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateImage();

        var appState = AppState.getInstance();
        appState.events.on(ALBUM_PAGE_ADDED, page -> {
            pageIndex = appState.getAlbum().getPages().size() - 1;
            updateImage();
        });
    }

    @FXML
    public void previousPage(ActionEvent actionEvent) {
        System.out.println("AlbumController.previousPage");

        var album = AppState.getInstance().getAlbum();
        var pagesCount = album.getPages().size();
        pageIndex = (pageIndex + pagesCount - 1) % pagesCount;
        updateImage();
    }

    @FXML
    public void nextPage(ActionEvent actionEvent) {
        System.out.println("AlbumController.nextPage");

        var album = AppState.getInstance().getAlbum();
        var pagesCount = album.getPages().size();
        pageIndex = (pageIndex + 1) % pagesCount;
        updateImage();
    }

    private void updateImage() {
        var album = AppState.getInstance().getAlbum();
        var pages = album.getPages();
        if (pageIndex < pages.size()) {
            var page = pages.get(pageIndex);
            var image = new Image("file:" + page.getImagePath(), 500, 500, true, true);
            imageView.setImage(image);
            pageTitle.setText(page.getTitle());
            pageNumber.setText(String.format("Page %d / %d", pageIndex + 1, pages.size()));
        } else {
            System.err.println("No album page at index " + pageIndex);
        }
    }
}
