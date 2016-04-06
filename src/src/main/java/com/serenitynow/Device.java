package com.serenitynow;

/**
 * Created by Andrew on 4/6/16.
 */
public class Device {
    private int mSize; //in KB
    private String mName;
    private String mPath;

    public Device(int size, String name, String path){
        this.mSize = size;
        this.mName = name;
        this.mPath = path;
    }

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
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
