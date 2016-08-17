package com.teetaa2.testsomeeffectmodule.gradually_fill_view_animation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.teetaa2.testsomeeffectmodule.R;

import java.util.Random;

public class GraduallyFillViewAnimationActivity extends Activity {
    private GraduallyFillImageView view;
    private View testButton;

    private boolean go = true;
    private boolean finish = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradually_fill_view_animation);
        view = (GraduallyFillImageView) findViewById(R.id.graduallyfillimageview);
        testButton = findViewById(R.id.graduallyfillimageviewButton);
        testButton.setOnTouchListener(new View.OnTouchListener() {
            int p = 0;
            float downY=0f;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY=event.getRawY();
                        Log.i("GraduallyFillView", "p:" + p);
                        go = true;
                        go();
                        break;
                    case MotionEvent.ACTION_UP:
                        go = false;
                        view.setPercentOnPost(0);
                        p = 0;
                        //stop
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float moveY = event.getRawY();
                        if (downY - moveY > 150) {
                            go = false;
                            view.setPercentOnPost(0);
                            p = 0;
                        }
//                        if (downY - moveY < 20) {
//                            isCanceled = false;
//                        }
                        break;
                }
                return true;
            }

            private void go() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        boolean goUp = true;
                        while (go) {
                            if (goUp) {
                                if (++p > 100) {
                                    goUp = !goUp;
                                }
                            } else {
                                if (--p < 0) {
                                    goUp = !goUp;
                                }
                            }
                            view.setPercentOnPost(p);
                            //Log.i("GraduallyFillView", "p:" + p);
                            try {
                                Thread.sleep(30);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        finish = true;
                    }
                }).start();
            }
        });
    }


    @Override
    public void onBackPressed() {
        go = false;
        while (!finish) {

        }
        super.onBackPressed();

    }

}
