package com.serenitynow.USBEncrypter;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    @FXML
    private Button refresh;

    @FXML
    private ListView<String> deviceListView;

    @FXML
    private Label selectedDrive;

    @FXML
    private Label deviceFreeSpace;

    @FXML
    private Label deviceTotalSpace;

    @FXML
    private Label deviceUsedSpace;

    @FXML
    private Button encrypt;

    @FXML
    private Button decrypt;

    @FXML
    private TextField password;

    @FXML
    private TextArea processOutput;

    @FXML
    private VBox processFinishedLog;

    private DeviceHandler mDeviceHandler;
    private ArrayList<Device> mDeviceArrayList;
    private Device mDevice;
    private static final String DriveSelectText = "No Drive Selected";
    private int selectedDeviceIndex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        selectedDeviceIndex = -1;
        mDeviceHandler = DeviceHandler.getInstance();

        toggleContent(true);

        //create observable list of items
        ObservableList<String> devicesObs = FXCollections.observableArrayList();

        //Watch the list
        devicesObs.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                //If we have more than 0 items then go ahead and enable all the things
                if(devicesObs.size() > 0){
                    toggleContent(false);
                } else {
                    toggleContent(true);
                }
            }
        });
        deviceListView.setItems(devicesObs);

        //When user clicks this button refresh the drive list
        refresh.setOnAction((ActionEvent event) -> {
            refreshDevices();
        });

        //Encrypt button listener
        encrypt.setOnAction((ActionEvent action) -> {
            if(password.getText().length() > 0){
                EncryptionService ES = new EncryptionService();
                ES.process(mDevice.getPath(), password.getText().toString(), "0", processOutput);
                Label l = new Label("Encrypted");
                processFinishedLog.getChildren().addAll(l);
            } else {
                //Show pop up error
                Alert alert = new Alert(Alert.AlertType.ERROR, "You must supply a password first.", ButtonType.CANCEL);
                alert.showAndWait();
            }
        });

        //Decrypt button listener
        decrypt.setOnAction((ActionEvent action) -> {
            if(password.getText().length() > 0){
                EncryptionService ES = new EncryptionService();
                ES.process(mDevice.getPath(), password.getText().toString(), "1", processOutput);
                Label l = new Label("Decrypted");
                processFinishedLog.getChildren().addAll(l);
            } else {
                //Show pop up error
                Alert alert = new Alert(Alert.AlertType.ERROR, "You must supply a password first.", ButtonType.CANCEL);
                alert.showAndWait();
            }
        });

        //Capture when a user clicks on a listview item
        deviceListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Your action here
                int index = deviceListView.getSelectionModel().getSelectedIndex();

                if(index > -1) {
                    mDevice = mDeviceArrayList.get(index);
                    setSelectedContent(mDevice);
                } else {
                    mDevice = null;
                }
            }
        });

        refreshDevices();
    }

    private void setSelectedContent(Device selectedDevice){
        if(selectedDevice != null) {
            selectedDrive.setText(selectedDevice.getName());
            deviceTotalSpace.setText(Device.formatNumber(selectedDevice.getTotalSpace(), "GB"));
            deviceFreeSpace.setText(Device.formatNumber(selectedDevice.getFreeSpace(), "GB"));
            deviceUsedSpace.setText(Device.formatNumber(selectedDevice.getTotalSpace() - selectedDevice.getFreeSpace(), "GB"));
        } else {
            selectedDrive.setText(DriveSelectText);
            deviceTotalSpace.setText("");
            deviceFreeSpace.setText("");
            deviceUsedSpace.setText("");
            password.setText("");
        }
    }

    private void toggleContent(boolean disable){
        encrypt.setDisable(disable);
        decrypt.setDisable(disable);
        password.setDisable(disable);
    }

    private void refreshDevices() {
        //
        setSelectedContent(null);

        mDeviceArrayList = mDeviceHandler.listDevices();
        ArrayList<String> mDeviceNames = new ArrayList<String>();
        for(Device d : mDeviceArrayList){
            mDeviceNames.add(d.getName());
        }

        //clear the list
        deviceListView.getItems().clear();

        //readd the devices
        deviceListView.getItems().addAll(mDeviceNames);
    }
}
