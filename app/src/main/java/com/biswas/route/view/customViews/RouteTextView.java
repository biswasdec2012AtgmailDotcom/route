package com.biswas.route.view.customViews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.biswas.route.R;
import com.biswas.route.RoutesApplication;

public class RouteTextView extends AppCompatTextView {


    public static final int FONT_REDMART_REGULAR = 0x00;
    public static final int FONT_REDMART_REGULAR_MEDIUM = 0x01;
    public static final int FONT_REDMART_REGULAR_BOLD = 0x02;


    RoutesApplication mApp;

    public RouteTextView(Context context) {
        super(context);
        init(null);
    }

    public RouteTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public RouteTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setFont(int fontName) {
        switch (fontName) {
            case FONT_REDMART_REGULAR:
                setTypeface(mApp.getRouteFontRegular());
                break;
            case FONT_REDMART_REGULAR_BOLD:
                setTypeface(mApp.getRedmartFontBold());
                break;
            case FONT_REDMART_REGULAR_MEDIUM:
                setTypeface(mApp.getRouteFontMedium());
                break;
            default:
                setTypeface(mApp.getRouteFontRegular());
        }
    }

    private void init(AttributeSet attrs) {
        mApp = (RoutesApplication) getContext().getApplicationContext();
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RouteTextView);
            int fontName = a.getInteger(R.styleable.RouteTextView_fontName, FONT_REDMART_REGULAR);
            setFont(fontName);
            a.recycle();
        } else
            setTypeface(mApp.getRouteFontRegular());
    }
}
