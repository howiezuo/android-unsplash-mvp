package io.github.howiezuo.unsplash.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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

    private int mBackgroundColor = 0xffffffff;
    private Paint mPaint;

    public CircleImageView(Context context) {
        super(context);

        setUp();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setUp();
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setUp();
    }

    private void setUp() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setFilterBitmap(true);
        mPaint.setDither(true);
        mPaint.setColor(mBackgroundColor);
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

        Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
        Bitmap newBmp = bmp.copy(Bitmap.Config.ARGB_8888, true);

        Bitmap result = circleBitmap(newBmp);
        canvas.drawBitmap(result, 0, 0, null);
    }

    /**
     * Cut bitmap to circle
     * @param src
     * @return
     */
    private Bitmap circleBitmap(Bitmap src) {
        Bitmap result = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(result);

        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, Math.min(getWidth(), getHeight()) / 2, mPaint);

        Rect srcRect = new Rect(0, 0, src.getWidth(), src.getHeight());
        Rect dstRect = new Rect(0, 0, getWidth(), getHeight());

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(src, srcRect, dstRect, mPaint);

        return result;
    }

}
