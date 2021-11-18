package fr.albumphoto.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class NavbarController {

    @FXML
    public void exportAlbum() {
        System.out.println("NavbarController.exportAlbum");
    }

    @FXML
    public void importAlbum(ActionEvent actionEvent) {
        System.out.println("NavbarController.importAlbum");
    }

    @FXML
    public void quitApplication(ActionEvent actionEvent) {
        System.out.println("NavbarController.quitApplication");
    }

    @FXML
    public void renameAlbum(ActionEvent actionEvent) {
        System.out.println("NavbarController.renameAlbum");
    }
}