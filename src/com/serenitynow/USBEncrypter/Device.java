package com.serenitynow.USBEncrypter;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Andrew on 4/6/16.
 */
public class Device {
    private double mFreeSpace;
    private double mUsableSpace;
    private double mTotalSpace;
    private String mName;
    private String mPath;

    public Device() {

    }

    public double getFreeSpace() {
        return mFreeSpace;
    }

    public void setFreeSpace(long freeSpace) {
        mFreeSpace = (double)freeSpace*1.0;
    }

    public double getUsableSpace() {
        return mUsableSpace;
    }

    public void setUsableSpace(long usableSpace) {
        mUsableSpace = (double)usableSpace*1.0;
    }

    public double getTotalSpace() {
        return mTotalSpace;
    }

    public void setTotalSpace(long totalSpace) {
        mTotalSpace = (double)totalSpace*1.0;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPath() {
        return mPath;
    }

    public void setPath(String path) {
        mPath = path;
    }

    public void printInfo(){
        System.out.println("Name: " + this.mName + " size: " + Device.formatNumber(this.getTotalSpace(), "GB") + " path: " + this.mPath);
    }

    public static String formatNumber(double num, String format){
        if(format.equals("KB")){
            return String.format("%1.2f", num / 1024.0) + " " + format;
        } else if (format.equals("MB")) {
            return String.format("%1.2f", num / 1048576.0) + " " + format;
        } else if (format.equals("GB")) {
            return String.format("%1.2f", num / 1073741824.0) + " " + format;
        }
        return String.format("%1.2f", num) + " B";
    }
}
