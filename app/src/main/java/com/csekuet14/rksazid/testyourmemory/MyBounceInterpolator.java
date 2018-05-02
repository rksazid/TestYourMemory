package com.csekuet14.rksazid.testyourmemory;

/**
 * Created by ASUS on 12/7/2016.
 */

public class MyBounceInterpolator implements android.view.animation.Interpolator {

    double mAmplitude = 1;
    double mFrequency = 10;

    public MyBounceInterpolator(double mAplitude, double mFrequency) {
        this.mFrequency = mFrequency;
        this.mAmplitude = mAplitude;
    }

    @Override
    public float getInterpolation(float time) {
        return (float)(-1* Math.pow(Math.E, -time/mAmplitude)* Math.cos(mFrequency*time)+1);
    }
}
