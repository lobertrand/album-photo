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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var app = App.getInstance();
        albumTitle.setText(app.getAlbum().getName());
        showCurrentPage();

        app.events.onEvent(Event.ALBUM_PAGE_ADDED, page -> showCurrentPage());
        app.events.onEvent(Event.ALBUM_PAGE_TURNED, page -> showCurrentPage());
    }

    @FXML
    public void previousPage(ActionEvent actionEvent) {
        App.getInstance().previousPage();
    }

    @FXML
    public void nextPage(ActionEvent actionEvent) {
        App.getInstance().nextPage();
    }

    private void showCurrentPage() {
        var app = App.getInstance();
        var page = app.getCurrentPage();
        if (page == null) {
            imageView.setImage(null);
            pageTitle.setText("");
            pageNumber.setText("Page 0 / 0");
        } else {
            var pageIndex = app.getCurrentPageIndex();
            var pagesCount = app.getAlbum().getPages().size();
            var image = new Image("file:" + page.getImagePath(), 500, 500, true, true);
            imageView.setImage(image);
            pageTitle.setText(page.getTitle());
            pageNumber.setText(String.format("Page %d / %d", pageIndex + 1, pagesCount));
        }
    }
}
