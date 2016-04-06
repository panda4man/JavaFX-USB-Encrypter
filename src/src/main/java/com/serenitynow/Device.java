package com.serenitynow;

/**
 * Created by Andrew on 4/6/16.
 */
public class Device {
    private double mSize; //in KB
    private String mName;
    private String mPath;

    public Device(double size, String name, String path){
        this.mSize = size;
        this.mName = name;
        this.mPath = path;
    }

    public double getSize() {
        return mSize;
    }

    public double getSizeInMb() {
        return mSize / 1024.0;
    }

    public double getSizeInGb() {
        return getSizeInMb() / 1024.0;
    }

    public void setSize(double size) {
        mSize = size;
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
}
