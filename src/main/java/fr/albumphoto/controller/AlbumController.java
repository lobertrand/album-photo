package fr.albumphoto.controller;

import fr.albumphoto.model.App;
import fr.albumphoto.model.event.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AlbumController implements Initializable {

    @FXML
    public Text albumTitle;
    @FXML
    public Text pageTitle;
    @FXML
    public ImageView imageView;
    @FXML
    public Text pageNumber;

    private int pageIndex = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var app = App.getInstance();
        albumTitle.setText(app.getAlbum().getName());

        updateShownPage();

        app.events.onEvent(Event.ALBUM_PAGE_ADDED, page -> {
            pageIndex = app.getAlbum().getPages().indexOf(page);
            updateShownPage();
        });
    }

    @FXML
    public void previousPage(ActionEvent actionEvent) {
        var album = App.getInstance().getAlbum();
        var pagesCount = album.getPages().size();
        if (pagesCount > 0) {
            pageIndex = (pageIndex + pagesCount - 1) % pagesCount;
        } else {
            pageIndex = 0;
        }
        updateShownPage();
    }

    @FXML
    public void nextPage(ActionEvent actionEvent) {
        var album = App.getInstance().getAlbum();
        var pagesCount = album.getPages().size();
        if (pagesCount > 0) {
            pageIndex = (pageIndex + 1) % pagesCount;
        } else {
            pageIndex = 0;
        }
        updateShownPage();
    }

    private void updateShownPage() {
        var album = App.getInstance().getAlbum();
        var pages = album.getPages();
        if (pages.isEmpty()) {
            imageView.setImage(null);
            pageTitle.setText("");
            pageNumber.setText("Page 0 / 0");
        } else {
            var page = pages.get(pageIndex);
            var image = new Image("file:" + page.getImagePath(), 500, 500, true, true);
            imageView.setImage(image);
            pageTitle.setText(page.getTitle());
            pageNumber.setText(String.format("Page %d / %d", pageIndex + 1, pages.size()));
        }
    }
}
