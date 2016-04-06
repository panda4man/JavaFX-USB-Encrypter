package com.serenitynow;

/**
 * Created by Andrew on 4/6/16.
 */
public class Device {
    private long mSize; //in KB
    private long mFreeSpace;
    private long mUsableSpace;
    private long mTotalSpace;
    private String mName;
    private String mPath;

    public Device() {

    }

    public long getSize() {
        return mSize;
    }

    public void setSize(long size) {
        mSize = size;
    }

    public long getFreeSpace() {
        return mFreeSpace;
    }

    public void setFreeSpace(long freeSpace) {
        mFreeSpace = freeSpace;
    }

    public long getUsableSpace() {
        return mUsableSpace;
    }

    public void setUsableSpace(long usableSpace) {
        mUsableSpace = usableSpace;
    }

    public long getTotalSpace() {
        return mTotalSpace;
    }

    public void setTotalSpace(long totalSpace) {
        mTotalSpace = totalSpace;
    }

    public long getSizeInMb() {
        return mSize / 1024;
    }

    public long getSizeInGb() {
        return getSizeInMb() / 1024;
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
        System.out.println("Name: " + this.mName + " size: " + this.mSize + " path: " + this.mPath);
    }
}
