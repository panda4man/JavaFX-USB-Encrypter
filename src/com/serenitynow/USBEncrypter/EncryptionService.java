package com.serenitynow.USBEncrypter;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Andrew on 4/9/16.
 */
public class EncryptionService {
    private final static String binary = "ls -l";

    public void process(String path, String password, boolean encrypt, ProgressBar bar, Label fileName){
        String command = EncryptionService.buildCommand(path, password, encrypt);

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();

            //Capture the stdout
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            //Capture the stderr
            //Not sure what to do with this yet
            BufferedReader readerErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                System.out.println(line);
                //First line is the file name
                fileName.setText(line);

                //Second line is the progress
                //bar.setProgress(Double.parseDouble(line));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String buildCommand(String path, String password, boolean encrypt){
        String new_path;
        if(encrypt){
            new_path = binary + " " + EncryptionService.fixPath(path);// + " " + password;
        } else {
            new_path = binary + " " + EncryptionService.fixPath(path);// + " " + password;
        }

        return new_path;
    }

    public static String fixPath(String path){
        return path.replace(" ", "\\ ");
    }
}
