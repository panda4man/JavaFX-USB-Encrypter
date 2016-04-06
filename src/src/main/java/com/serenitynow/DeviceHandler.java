package com.serenitynow;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Andrew on 4/6/16.
 */
public class DeviceHandler {
    private static DeviceHandler instance = null;

    /**
     * Prevent instantiation of this class
     */
    private DeviceHandler(){
        OsUtils.getOsName();
    }

    /**
     * Get an instance of this class
     * @return  {@code instance}
     */
    public static synchronized DeviceHandler getInstance(){
        if(instance == null) return new DeviceHandler();

        return instance;
    }

    /**
     * This method will handle calling the proper method, depending on OS,
     * to grab the list of internal/external storage devices.
     *
     * @return      ArrayList<Device>
     */
    public ArrayList<Device> listDevices() {
        ArrayList<Device> device_list = new ArrayList<Device>();

        if(OsUtils.isMac()){
            getDevicesMac(device_list);
        } else if (OsUtils.isWindows()){
            getDevicesWindows(device_list);
        } else if (OsUtils.isUnix()){
            getDevicesUnix(device_list);
        }

        return device_list;
    }

    private void getDevicesMac(ArrayList<Device> device_list){
        System.out.println(DeviceHandler.OsUtils.getOsName());
        System.out.println(DeviceHandler.OsUtils.isMac());
        String[] cmd = {"diskutil", "list"};
        String line = "";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void getDevicesWindows(ArrayList<Device> device_list){

    }

    private void getDevicesUnix(ArrayList<Device> device_list){

    }

    /**
     * This class handles getting information about the OS.
     */
    public static final class OsUtils
    {
        private static String OS = null;
        private static String OS_lower = null;

        public static String getOsName()
        {
            if(OS == null) { OS = System.getProperty("os.name"); }
            if(OS_lower == null) {OS_lower = System.getProperty("os.name").toLowerCase();}

            return OS;
        }
        public static boolean isWindows() {

            return (OS_lower.contains("win"));

        }

        public static boolean isMac() {
            return (OS_lower.contains("mac"));

        }

        public static boolean isUnix() {

            return (OS_lower.contains("nix") || OS_lower.contains("nux")|| OS_lower.contains("aix") );

        }

        public static boolean isSolaris() {

            return (OS_lower.contains("sunos"));

        }
    }
}
