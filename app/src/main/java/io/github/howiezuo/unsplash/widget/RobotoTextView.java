package io.github.howiezuo.unsplash.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.util.FontUtils;


public class RobotoTextView extends TextView {

    private static final String FONT_NAME = "Roboto-%s.ttf";

    private String mTextStyle = TextStyle.REGULAR.getName();

    public RobotoTextView(Context context) {
        super(context);

        setFont(context);
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        stylize(context, attrs);
        setFont(context);
    }

    public RobotoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        stylize(context, attrs);
        setFont(context);
    }

    private void stylize(Context context, AttributeSet attrs) {

        TypedArray ta = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.RobotoTextView,
                0,
                0
        );

        try {
            int style = ta.getInt(R.styleable.RobotoTextView_rtv_textStyle, 0);
            mTextStyle = TextStyle.getName(style);
        } finally {
            ta.recycle();
        }
    }

    private void setFont(Context context) {
        if (mTextStyle == null) mTextStyle = TextStyle.REGULAR.getName();
        Typeface tf = FontUtils.get(String.format(FONT_NAME, mTextStyle), context);
        setTypeface(tf);
    }

    private enum TextStyle {
        REGULAR("Regular", 0),
        MEDIUM("Medium", 4),
        BOLD("Bold", 5);

        private final String name;
        private final int value;

        TextStyle(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }

        public static String getName(int value) {
            TextStyle[] styles = TextStyle.values();
            for (TextStyle ts : styles) {
                if (ts.getValue() == value) {
                    return ts.getName();
                }
            }
            return null;
        }
    }
}
