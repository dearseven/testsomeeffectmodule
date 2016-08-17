package com.teetaa2.testsomeeffectmodule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.teetaa2.testsomeeffectmodule.gradually_fill_view_animation.GraduallyFillViewAnimationActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
       setContentView(R.layout.activity_main);
    }

    public void goGraduallyFillViewAnimationActivivy(View view){
        Intent intent=new Intent(this, GraduallyFillViewAnimationActivity.class);
        startActivity(intent);
    }
}
