package com.serenitynow;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.filechooser.FileSystemView;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UsbEncrypter extends Application{

    Stage mWindow;
    Scene mScene1, mScene2;
    private DeviceHandler mDeviceHandler;

    public static void main(String[] args){
        launch(args);
        System.out.println("Launching main");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        mDeviceHandler = DeviceHandler.getInstance();

        mWindow = primaryStage;

        Label label1 = new Label("Welcome to the first scene");
        Button button1 = new Button("Go to scene 2");
        button1.setOnAction(e -> {
            mWindow.setScene(mScene2);
        });

        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, button1);

        mScene1 = new Scene(layout1, 200, 200);

        Button button2 = new Button("This sucks, Go back to scene 1.");
        button2.setOnAction(e -> {
            mWindow.setScene(mScene1);
        });

        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(button2);

        mScene2 = new Scene(layout2, 500, 300);

        mWindow.setScene(mScene1);
        mWindow.setTitle("Hallo");
        mWindow.show();

        grabDevices();

    }

    private void grabDevices() {
        mDeviceHandler.listDevices();
    }
}
