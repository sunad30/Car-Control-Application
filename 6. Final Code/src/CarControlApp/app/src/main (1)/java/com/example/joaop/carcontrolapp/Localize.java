package com.example.joaop.carcontrolapp;

import android.content.*;
import android.hardware.*;
import android.graphics.*;
import android.os.Environment;
import android.view.View;
import android.widget.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Localize extends AppCompatActivity implements View.OnClickListener {
    private float x1, x2, y1, y2;
    private Sensor rot;
    private SensorManager sensorManager;
    private SensorEventListener rotListener;
    private EditText et;
    private Button goToMenu;
    private ImageButton info;

    private static String nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localize);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        rot = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        et = (EditText) findViewById(R.id.localnote);
        et.setText(nota);


        goToMenu = (Button) findViewById(R.id.goToMenu);
        goToMenu.setOnClickListener(this);

        info = (ImageButton) findViewById(R.id.info);
        info.setOnClickListener(this);

        rotListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                ((ImageView) findViewById(R.id.imagem)).setRotation(-(1 - event.values[2]) * 180);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        sensorManager.registerListener(rotListener, rot, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if (y1 < y2 && y2 - y1 > 200 && y2 - y1 > x1 - x2 && y2 - y1 > x2 - x1) {
                    startActivity(new Intent(this, Main.class));
                }
                break;
        }
        return false;
    }

    @Override
    public void onPause() {
        nota = et.getText().toString();
        super.onPause();
        sensorManager.unregisterListener(rotListener);
    }

    public void onStop() {
        this.finish();
        super.onStop();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info:
                startActivity(new Intent(this, helpLocalize.class));
                break;

            case R.id.goToMenu:
                startActivity(new Intent(this, Main.class));
                break;
        }
    }


    public void onBackPressed(){
        startActivity(new Intent(this, Main.class));
    }
}

