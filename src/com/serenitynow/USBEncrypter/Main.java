package com.serenitynow.USBEncrypter;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main extends Application {
    private DeviceHandler mDeviceHandler;

    @FXML
    private BorderPane anchor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Initialize the device handler
        //Some caching is done initially to make it faster
        mDeviceHandler = DeviceHandler.getInstance();

        //Grab instance of main controller
        MainController MainController = new MainController();

        //Load the main anchor pane which is of type BorderPane
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setController(MainController);
        Parent root = (Parent) loader.load();

        //Set the window title and scene size and then show off our hard work
        primaryStage.setTitle("Super Secure USB Encrypting");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
