package com.example.android_assignment1;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;


public class History implements Parcelable {
    private String typeTran;
    private String numTran;
    private String dateTime;
    public History(String typeTran, String numTran, String dateTime){
        this.typeTran = typeTran;
        this.numTran = numTran;
        this.dateTime = dateTime;
    }

    protected History(Parcel in) {
        typeTran = in.readString();
        numTran = in.readString();
        dateTime = in.readString();
    }

    public static final Creator<History> CREATOR = new Creator<History>() {
        @Override
        public History createFromParcel(Parcel in) {
            return new History(in);
        }

        @Override
        public History[] newArray(int size) {
            return new History[size];
        }
    };

    public String getTypeTran() {
        return typeTran;
    }

    public void setTypeTran(String typeTran) {
        this.typeTran = typeTran;
    }

    public String getNumTran() {
        return numTran;
    }

    public void setNumTran(String numTran) {
        this.numTran = numTran;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(typeTran);
        dest.writeString(numTran);
        dest.writeString(dateTime);
    }
}
