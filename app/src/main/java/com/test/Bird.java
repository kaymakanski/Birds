package com.test;

import android.os.Parcel;
import android.os.Parcelable;

public class Bird implements Parcelable {
    private String name;
    private final String wikiUrl;
    private final String imgPath;
    private boolean isSelected = false;
    private static Bird chosenBird;


    protected Bird(Parcel in) {
        name = in.readString();
        wikiUrl = in.readString();
        imgPath = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<Bird> CREATOR = new Creator<Bird>() {
        @Override
        public Bird createFromParcel(Parcel in) {
            return new Bird(in);
        }

        @Override
        public Bird[] newArray(int size) {
            return new Bird[size];
        }
    };

    public String getImgPath() {
        return imgPath;
    }

    public Bird(String name, String wikiUrl, String imgPath) {
        this.name = name;
        this.wikiUrl = wikiUrl;
        this.imgPath = imgPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(wikiUrl);
        parcel.writeString(imgPath);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
    }

    public void iChooseYou(Bird chosenBird) {
        Bird.chosenBird = chosenBird;
    }

    public void iDeChooseYou() {
        Bird.chosenBird = null;
    }

    public static Bird getChosenBird() {
        return chosenBird;
    }
}
