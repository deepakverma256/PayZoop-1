package com.objectedge.restroappproto.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerTabStrip;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.objectedge.restroappproto.R;

/**
 * Created by deepak.verma on 11-02-2017.
 */
public class CustomPagerTabStrip extends PagerTabStrip
{
    public CustomPagerTabStrip(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        final TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CustomPagerTabStrip);

        TypedValue typedValue = new TypedValue();

       // TypedArray a = context.obtainStyledAttributes(typedValue.data, new int[] { R.attr.indicatorColor });
        int color = a.getColor(0, 0);


        setTabIndicatorColor(a.getColor(
                R.styleable.CustomPagerTabStrip_indicatorColor, getResources().getColor(R.color.colorPrimaryDark)));
        a.recycle();
    }

}