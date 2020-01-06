package com.example.android.miwok;

/**
 * Created by MR.Laptop Point on 6/29/2017.
 */

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int imageResourceId;
    private int audioResourceId;

    public Word() {
        mDefaultTranslation = null;
        mMiwokTranslation = null;
        imageResourceId = 0;
        audioResourceId = 0;
    }

    public Word(String mDefaultTranslation, String mMiwokTranslation, int imageResourceId, int audioResourceId) {
        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.imageResourceId = imageResourceId;
        this.audioResourceId = audioResourceId;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public  int getAudioResource() {
        return audioResourceId;
    }

}
