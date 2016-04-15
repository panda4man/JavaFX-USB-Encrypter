package com.serenitynow.USBEncrypter;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Andrew on 4/9/16.
 */
public class EncryptionService {
    private final static File executorDirectory = new File("/Users/Andrew/Desktop");

    public void process(String path, String password, String encrypt, TextArea textArea){
        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Process p;
                        ProcessBuilder processBuilder = buildCommand(path, password, encrypt);
                        //processBuilder.directory(executorDirectory);

                        p = processBuilder.start();
                        p.waitFor();

                        InputStream in = p.getInputStream();


                        //Capture the stdout
                        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

                        //Capture the stderr
                        //Not sure what to do with this yet
                        BufferedReader readerErr = new BufferedReader(new InputStreamReader(p.getErrorStream()));

                        String line = "";
                        textArea.setText("");
                        while ((line = reader.readLine()) != null) {
                            textArea.appendText(line + "\n");
                        }
                    } catch (final Throwable t){
                        t.printStackTrace();
                    }
                }
            });
        } catch (final IllegalStateException e){
            e.printStackTrace();
        }
    }

    private ProcessBuilder buildCommand(String path, String password, String encrypt){
        String new_path = this.getClass().getResource("/Darwin_poc_newer.bin").getPath();
        new_path = fixPath(new_path);
        return new ProcessBuilder("/Users/Andrew/Desktop/binaries1.5/binaries/Darwin_poc_1-3.7.bin", path, encrypt, password);
    }

    public static String fixPath(String path){
        return path.replace("%20", "\\ ");
    }
}
