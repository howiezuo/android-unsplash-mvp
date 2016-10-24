package io.github.howiezuo.unsplash.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

public class UIUtils {

    public static Drawable view2Drawable(Context context, @LayoutRes int layoutId) {
        View v = LayoutInflater.from(context).inflate(layoutId, null);
        v.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());

        v.setDrawingCacheEnabled(true);
        Bitmap bmp = v.getDrawingCache();
        Drawable drawable = new BitmapDrawable(context.getResources(), bmp);
        return drawable;
    }
}
