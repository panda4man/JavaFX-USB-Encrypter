package com.serenitynow.USBEncrypter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage mWindow;
    private DeviceHandler mDeviceHandler;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mDeviceHandler = DeviceHandler.getInstance();
        mWindow = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        mWindow.setTitle("Hello World");
        mWindow.setScene(new Scene(root, 600, 400));
        mWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
