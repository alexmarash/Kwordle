package com.example.kwordle;

import android.os.Parcel;
import android.os.Parcelable;

public class AlphaWrapper implements Parcelable {

    private int letter;
    private int color;

    public AlphaWrapper(int letter, int color) {
        this.letter = letter;
        this.color = color;
    }

    protected AlphaWrapper(Parcel in) {
        letter = in.readInt();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(letter);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AlphaWrapper> CREATOR = new Creator<AlphaWrapper>() {
        @Override
        public AlphaWrapper createFromParcel(Parcel in) {
            return new AlphaWrapper(in);
        }

        @Override
        public AlphaWrapper[] newArray(int size) {
            return new AlphaWrapper[size];
        }
    };

    public int getColor() {return this.color;}
    public int getLetter() {return this.letter;}

    public void setLetter(int letter){
        this.letter = letter;
    }

    public void setColor(int color){
        this.color = color;
    }

}




