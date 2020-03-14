package com.example.sshopappui;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class cartClass implements Parcelable {
    private String title;
    private String price;
    private int image;


    public cartClass(String title, String price, int image) {
        this.title = title;
        this.price = price;
        this.image = image;

    }

    protected cartClass(Parcel in) {
        title = in.readString();
        price = in.readString();
        image = in.readInt();
    }

    public static final Creator<cartClass> CREATOR = new Creator<cartClass>() {
        @Override
        public cartClass createFromParcel(Parcel in) {
            return new cartClass(in);
        }

        @Override
        public cartClass[] newArray(int size) {
            return new cartClass[size];
        }
    };

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(price);
        dest.writeInt(image);
    }
}
