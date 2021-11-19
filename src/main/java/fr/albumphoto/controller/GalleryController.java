package fr.albumphoto.controller;

import fr.albumphoto.model.App;
import fr.albumphoto.model.Page;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

import static fr.albumphoto.model.event.Event.ALBUM_PAGE_ADDED;
import static fr.albumphoto.model.event.Event.GALLERY_IMAGE_ADDED;

public class GalleryController implements Initializable {

    public static final int IMAGE_SIZE = 150;

    @FXML
    public FlowPane imageGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        var app = App.getInstance();
        var gallery = app.getGallery();
        for (String imagePath : gallery.imagePaths) {
            imageGrid.getChildren().add(createImageComponent(imagePath));
        }

        app.events.onEvent(GALLERY_IMAGE_ADDED, imagePath -> {
            // Mise à jour de l'interface
            imageGrid.getChildren().add(createImageComponent(imagePath));
        });
    }

    private VBox createImageComponent(String imagePath) {
        var image = new Image("file:" + imagePath, 500, 500, true, true);
        var imageView = new ImageView(image);
        imageView.setFitWidth(IMAGE_SIZE);
        imageView.setFitHeight(IMAGE_SIZE);
        imageView.setPreserveRatio(true);
        var imageContainer = new VBox(imageView);
        imageContainer.setPrefWidth(IMAGE_SIZE);
        imageContainer.setPrefHeight(IMAGE_SIZE);
        imageContainer.setAlignment(Pos.CENTER);
        imageContainer.setStyle("-fx-border-color: lightgray");

        imageContainer.setOnMouseReleased(event -> {
            var app = App.getInstance();
            var album = app.getAlbum();
            var page = Page.namedFromImagePath(imagePath);
            album.getPages().add(page);
            app.events.fireEvent(ALBUM_PAGE_ADDED, page);
        });

        return imageContainer;
    }

    @FXML
    public void addImages(ActionEvent actionEvent) {
        // Sélection d'un fichier image à ajouter à la galerie
        var fileChooser = new FileChooser();
        var extensionFilter = new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(extensionFilter);
        var file = fileChooser.showOpenDialog(null);

        // Mise à jour du modèle
        var app = App.getInstance();
        var imagePath = file.getAbsolutePath();
        app.getGallery().getImagePaths().add(imagePath);
        app.events.fireEvent(GALLERY_IMAGE_ADDED, imagePath);
    }
}
