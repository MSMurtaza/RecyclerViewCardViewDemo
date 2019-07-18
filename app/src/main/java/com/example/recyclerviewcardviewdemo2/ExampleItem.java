package com.example.recyclerviewcardviewdemo2;

public class ExampleItem {
    private int mImageResource;
    private String mText1, mText2;

    public ExampleItem(int imageResource, String text1, String text2) {
        mImageResource = imageResource;
        mText1 = text1;
        mText2 = text2;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmText1() {
        return mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void changeText1(String text) {
        mText1 = text;
    }

    public void changeText2(String text) {
        mText2 = text;
    }
}
