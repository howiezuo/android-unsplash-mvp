package io.github.howiezuo.unsplash.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;


/**
 * Circle Image View
 */
public class CircleImageView extends ImageView {

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        int w = getWidth();
        int h = getHeight();

        Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
        Bitmap newBmp = bmp.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap result = circleBitmap(newBmp, w, h);
        canvas.drawBitmap(result, 0, 0, null);
    }

    /**
     * Cut bitmap to circle
     * @param src
     * @return
     */
    private static Bitmap circleBitmap(Bitmap src, int w, int h) {
        Bitmap result = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor("#000000"));

        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(w / 2, h / 2, Math.min(w, h) / 2, paint);

        Rect srcRect = new Rect(0, 0, src.getWidth(), src.getHeight());
        Rect dstRect = new Rect(0, 0, w, h);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(src, srcRect, dstRect, paint);

        return result;
    }

}
