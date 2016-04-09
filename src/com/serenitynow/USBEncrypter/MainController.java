package com.serenitynow.USBEncrypter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

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

    private DeviceHandler mDeviceHandler;
    private ArrayList<Device> mDeviceArrayList;
    private static final String DriveSelectText = "No Drive Selected";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mDeviceHandler = DeviceHandler.getInstance();

        //create observable list of items
        ObservableList<String> devicesObs = FXCollections.observableArrayList();
        deviceListView.setItems(devicesObs);

        //When user clicks this button refresh the drive list
        refresh.setOnAction((ActionEvent event) -> {
            selectedDrive.setText(DriveSelectText);

            mDeviceArrayList = mDeviceHandler.listDevices();
            ArrayList<String> mDeviceNames = new ArrayList<String>();
            for(Device d : mDeviceArrayList){
                mDeviceNames.add(d.getName());
            }

            deviceListView.getItems().clear();

            deviceListView.getItems().addAll(mDeviceNames);
        });

        deviceListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Your action here
                int index = deviceListView.getSelectionModel().getSelectedIndex();

                if(index > -1) {
                    setSelectedContent(mDeviceArrayList.get(index));
                }
            }
        });
    }

    private void setSelectedContent(Device selectedDevice){
        if(selectedDevice != null) {
            selectedDrive.setText(selectedDevice.getName());
            deviceTotalSpace.setText(Device.formatNumber(selectedDevice.getTotalSpace(), "GB"));
            deviceFreeSpace.setText(Device.formatNumber(selectedDevice.getFreeSpace(), "GB"));
            deviceUsedSpace.setText(Device.formatNumber(selectedDevice.getTotalSpace() - selectedDevice.getFreeSpace(), "GB"));
        } else {
            selectedDrive.setText("");
            deviceTotalSpace.setText("");
            deviceFreeSpace.setText("");
            deviceUsedSpace.setText("");
        }
    }
}
