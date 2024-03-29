package com.example.tdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    // Screen size
    private int screenWidth;
    private int screenHeight;

    // Images
    private ImageView dog;
    private ImageView road;

    // Path
    int path[][];
    int pathCorners[][];
    int total;
    int pathLength;

    // Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create dog
        final Mob dog = new Mob((ImageView)findViewById(R.id.dog));
        dog.pos = 0;


        // Screen Size
        WindowManager wm = getWindowManager();
        Display mdisp = wm.getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        int maxX = mdispSize.x;
        int maxY = mdispSize.y;

        //Creates corners of the path - create map here
        pathCorners = new int[4][4];
        pathCorners[0][0] = maxX/2 - dog.icon.getWidth();
        pathCorners[0][1] = maxY;
        pathCorners[1][0] = maxX/2 - dog.icon.getWidth();
        pathCorners[1][1] = 0;
        pathCorners[2][0] = maxX/2 - dog.icon.getWidth() - 500;
        pathCorners[2][1] = 0;
        pathCorners[3][0] = maxX/2 - dog.icon.getWidth() - 500;
        pathCorners[3][1] = maxY;
        //Finds the length of the path
        for (int i = 0; i < pathCorners.length - 1; i++) {
            if (pathCorners[i][0] == pathCorners[i+1][0]) {
                pathLength += Math.abs(pathCorners[i][1] - pathCorners[i+1][1]);
            }
            else {
                pathLength += Math.abs(pathCorners[i][0] - pathCorners[i+1][0]);
            }
        }
        //Creates the path itself, with each array within the larger array being a coordinate
        path = new int[pathLength][2];
        total = 0;
        for (int i = 0; i< (pathCorners.length - 1); i++) {
            //This is checking if x stays the same
            if (pathCorners[i][0] == pathCorners[i+1][0]) {
                //If the path moves down
                if (pathCorners[i][1] - pathCorners[i+1][1] > 0) {
                    for (int j = 0; j < (pathCorners[i][1] - pathCorners[i+1][1]); j++) {
                        path[total + j][0] = pathCorners[i][0];
                        path[total + j][1] = pathCorners[i][1] - j;

                    }
                    total += pathCorners[i][1] - pathCorners[i+1][1];
                }
                else {
                    for (int j = 0; j < (pathCorners[i+1][1] - pathCorners[i][1]); j++) {
                        path[total + j][0] = pathCorners[i][0];
                        path[total + j][1] = pathCorners[i][1] + j;
                    }
                    total += pathCorners[i+1][1] - pathCorners[i][1];
                }
            }
            else {
                if (pathCorners[i][0] - pathCorners[i+1][0] > 0) {
                    for (int k = 0; k < (pathCorners[i][0] - pathCorners[i+1][0]); k++) {
                        path[total + k][0] = pathCorners[i][0] - k;
                        path[total + k][1] = pathCorners[i][1];
                    }
                    total += pathCorners[i][0] - pathCorners[i+1][0];
                }
                else {
                    for (int k = 0; k < (pathCorners[i+1][0] - pathCorners[i][0]); k++) {
                        path[total + k][0] = pathCorners[i][0] + k;
                        path[total + k][1] = pathCorners[i][1];
                    }
                    total += pathCorners[i+1][0] - pathCorners[i][0];
                }
            }
        }
        path[maxY - 1][0] = pathCorners[1][0];
        path[maxY - 1][1] = pathCorners[1][1];

        //Add a tower to test with
        final Tower testTower = new Tower((ImageView)findViewById(R.id.tower));
        testTower.X = maxX/2 - 100;
        testTower.Y = maxY/2 - 100;
        testTower.icon.setX(testTower.X);
        testTower.icon.setY(testTower.Y);

        // Start Timer
        timer.schedule(new TimerTask() {
            public void run() {
                handler.post(new Runnable(){
                    public void run() {
                        changePos(dog);
                    }
                });
            }
        }, 0, 20);

    }
    public void changePos(Mob mob) {
        mob.pos += mob.speed;

        if (mob.pos >= path.length) mob.pos = path.length - 1;

        mob.X = path[mob.pos][0];
        mob.Y = path[mob.pos][1];
        mob.icon.setX(mob.X);
        mob.icon.setY(mob.Y);
    }
}
class Mob {
    int pos = 0;
    int X = 0;
    int Y = 0;
    int speed = 10;
    ImageView icon;
    public Mob (ImageView icon){
        this.icon = icon;
    }
}

class Tower {
    int X = 0;
    int Y = 0;
    int range = 400;
    ImageView icon;
    public Tower (ImageView icon){ this.icon = icon;  }
}

//class Projectile {
  //  int[] coordinate = {};
    //ImageView icon;
    //public Projectile (ImageView icon) {this.icon = icon; }
//}

