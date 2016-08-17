package com.teetaa2.testsomeeffectmodule.gradually_fill_view_animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.teetaa2.testsomeeffectmodule.R;

public class GraduallyFillView extends SurfaceView implements
        SurfaceHolder.Callback, Runnable {
    private SurfaceHolder holder;
    private Bitmap bitmap;
    private Paint paint1;
    private Paint paint2;
    public boolean flag = true;
    private int y = 100;

    public GraduallyFillView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        holder = this.getHolder();
        holder.addCallback(this);
        paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint2 = new Paint();
        paint2.setColor(Color.YELLOW);
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        bitmap = bitmap1.extractAlpha();// 获取一个透明图片
        y = bitmap.getWidth();//初始化y轴坐标
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        new Thread(this).start();//开启绘制线程
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void run() {
        while (flag) {
            drawLoadingAnimator();
            playAnimator();
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
    }

    private void drawLoadingAnimator() {
        Canvas canvas = null;
        try {
            canvas = holder.lockCanvas();
            if (canvas != null) {
                canvas.drawBitmap(bitmap, 100, 100, null);
                canvas.drawColor(Color.GREEN);
                canvas.drawBitmap(bitmap, 100, 100, paint2);
                canvas.save();
                //裁剪
                Log.i("GraduallyFillView","Y:"+y);
                canvas.clipRect(100, y + 100, bitmap.getWidth() + 100,
                        bitmap.getHeight() + 100);
                canvas.drawBitmap(bitmap, 100, 100, paint1);
                canvas.restore();
            }
            /*
             * Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
             * Rect dst = new Rect(100, 100, bitmap.getWidth()+100, y+100);
             * canvas.drawBitmap(bitmap, src, dst, paint2);
             */
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (holder != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            } catch (Exception e) {
                //e.printStackTrace();
            }

        }
    }

    private void playAnimator() {
        if (y > 0) {
            y -= 3;
        }
    }
}
