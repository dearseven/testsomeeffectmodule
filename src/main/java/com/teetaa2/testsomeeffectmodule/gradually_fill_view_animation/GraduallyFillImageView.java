package com.teetaa2.testsomeeffectmodule.gradually_fill_view_animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/8/17.
 */
public class GraduallyFillImageView extends ImageView {

    private Bitmap bitmap;
    private Paint paint0;
    private Paint paint1;

    public GraduallyFillImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint0 = new Paint();
        paint1 = new Paint();
        paint1.setColor(Color.RED);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        totalHeight = bitmap.getHeight();
        super.onDraw(canvas);
        try {
            //裁剪
            Bitmap bitmap1 = bitmap.extractAlpha();
            //当bitmap.getHeight()==percentHeight的时候，则是全部绘制
            //Log.i("percentHeight2", "percentHeight:" + percentHeight);
            canvas.clipRect(0, bitmap.getHeight() - percentHeight, bitmap.getWidth(),
                    bitmap.getHeight());
            canvas.drawBitmap(bitmap1, 0, 0, paint1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    int totalHeight = 0;
    int percentHeight = 0;

    public void setPercent(int percent) {
        percentHeight = totalHeight * percent / 100;
        //Log.i("percentHeight1", "percentHeight:" + percentHeight);
        invalidate();
    }

    public void setPercentOnPost(int percent) {
        percentHeight = totalHeight * percent / 100;
        //Log.i("percentHeight1", "percentHeight:" + percentHeight);
        postInvalidate();
    }
}
