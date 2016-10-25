package com.tm.arifin.timbangan.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tm.arifin.timbangan.MyApplication;

/**
 * Created by aldieemaulana on 10/01/2016
 */
public class RalewayTextViewLight extends TextView {
    public RalewayTextViewLight(Context context) {
        this(context, null);
    }

    public RalewayTextViewLight(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RalewayTextViewLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface(MyApplication.ralewayLight);
    }

}
