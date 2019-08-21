package com.example.tdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    //Screen Size
    private int screenWidth;
    private int screenHeight;

    //Images
    private ImageView puppy;

    //Position
    private float puppyX;
    private float puppyY;

    //Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        puppy = (ImageView)findViewById(R.id.puppy);

        //Gets Screen Size
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        //Timer Start
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                    }
                });
            }
        }, 0, 20);

    }

    public void changePos() {

    }
}
