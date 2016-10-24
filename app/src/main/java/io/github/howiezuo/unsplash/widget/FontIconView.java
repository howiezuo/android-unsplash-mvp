package io.github.howiezuo.unsplash.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import io.github.howiezuo.unsplash.util.FontUtils;


public class FontIconView extends TextView {

    private static final String FONT_NAME = "fontawesome-webfont.ttf";

    public FontIconView(Context context) {
        super(context);

        setFont(context);
    }

    public FontIconView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setFont(context);
    }

    public FontIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setFont(context);
    }

    private void setFont(Context context) {
        Typeface tf = FontUtils.get(FONT_NAME, context);
        setTypeface(tf);
    }

}
