package com.serenitynow.USBEncrypter;

import java.io.File;
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
        File volumes = new File("/Volumes");
        File files[] = volumes.listFiles();
        for(File f: files){
            String name = f.getName();
            String path = f.getAbsolutePath();
            long freeSpace = f.getFreeSpace();
            long usableSpace = f.getUsableSpace();
            long totalSpace = f.getTotalSpace();

            Device d = new Device();
            d.setName(name);
            d.setPath(path);
            d.setFreeSpace(freeSpace);
            d.setTotalSpace(totalSpace);
            d.setUsableSpace(usableSpace);

            device_list.add(d);
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
