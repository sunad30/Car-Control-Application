package com.example.joaop.carcontrolapp;

import android.content.Intent;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.*;

public class AirConditioner extends AppCompatActivity implements View.OnClickListener{
    private Bitmap b;
    private float x1, x2, y1, y2;
    private Button up, down, ac, goToMenu, up2, down2;
    private static int temp=20;
    private static int acOn=0;

    private ImageButton info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioner);
        b = BitmapFactory.decodeResource(getResources(), R.drawable.tempgradient);
        up = (Button) findViewById(R.id.up);
        down = (Button) findViewById(R.id.down);
        up.setOnClickListener(this);
        down.setOnClickListener(this);

        up2 = (Button) findViewById(R.id.up2);
        down2 = (Button) findViewById(R.id.down2);
        up2.setOnClickListener(this);
        down2.setOnClickListener(this);

        ac = (Button) findViewById(R.id.acOnOff);
        ac.setOnClickListener(this);

        goToMenu = (Button) findViewById(R.id.goToMenu);
        goToMenu.setOnClickListener(this);

        info = (ImageButton) findViewById(R.id.info);
        info.setOnClickListener(this);

        if(acOn==0){
            disableButton();
        }else{
            enableButton();
            ((TextView) findViewById(R.id.temperature)).setText(String.valueOf(temp));
            setImage(temp);
        }



    }

    public void setImage(int color)
    {
        int cor = b.getHeight()-((color-10)*b.getHeight()/20);
        if(cor>=b.getHeight())cor=b.getHeight()-1;
        if(cor<0)cor=0;
        ((ImageView)findViewById(R.id.temp)).setColorFilter(b.getPixel(0, cor));
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch(touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(y1 < y2 && y2-y1>200 && y2-y1>x1-x2 && y2-y1>x2-x1) {
                    startActivity(new Intent(this, Main.class));
                }
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        TextView text = (TextView) findViewById(R.id.temperature);
        switch(v.getId()){
            case R.id.info:
                startActivity(new Intent(this, helpAC.class));
                break;

            case R.id.goToMenu:
                startActivity(new Intent(this, Main.class));
                break;

            case R.id.up:
                if(temp>=30){
                    return;
                }
                temp+=1;
                text.setText(Integer.toString(temp));
                setImage(temp);
                break;
            case R.id.down:
                if(temp<=10){
                    return;
                }
                temp-=1;
                text.setText(Integer.toString(temp));
                setImage(temp);
                break;

            case R.id.up2:
                if(temp>=30){
                    return;
                }
                temp+=2;
                text.setText(Integer.toString(temp));
                setImage(temp);
                break;
            case R.id.down2:
                if(temp<=10){
                    return;
                }
                temp-=2;
                text.setText(Integer.toString(temp));
                setImage(temp);
                break;
            case R.id.acOnOff:
                if(ac.getBackground().getConstantState().equals((getResources().getDrawable(R.drawable.button_selected, null)).getConstantState())){
                    acOn=0;
                    Main.disableAc();
                    disableButton();
                    break;
                }else{
                    acOn=1;
                    Main.enableAc();
                    enableButton();
                    text.setText(Integer.toString(temp));
                    setImage(temp);
                    break;
                }
        }
    }

    private void disableButton(){
        ((ImageView)findViewById(R.id.temp)).setColorFilter(Color.parseColor("#696969"));
        ac.setText("Connect");
        TextView text = (TextView) findViewById(R.id.temperature);
        up.setVisibility(View.INVISIBLE);
        down.setVisibility(View.INVISIBLE);
        up2.setVisibility(View.INVISIBLE);
        down2.setVisibility(View.INVISIBLE);
        text.setVisibility(View.INVISIBLE);
        ((TextView) findViewById(R.id.graus)).setVisibility(View.INVISIBLE);
        ac.setBackgroundResource(R.drawable.button_unselected);
    }

    private void enableButton(){
        ((ImageView)findViewById(R.id.temp)).setColorFilter(null);
        ac.setText("Switch Off");
        TextView text = (TextView) findViewById(R.id.temperature);
        ac.setBackgroundResource(R.drawable.button_selected);
        up.setVisibility(View.VISIBLE);
        down.setVisibility(View.VISIBLE);
        up2.setVisibility(View.VISIBLE);
        down2.setVisibility(View.VISIBLE);
        text.setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.graus)).setVisibility(View.VISIBLE);
    }

    public void onStop() {
        this.finish();
        super.onStop();
    }

    public void onBackPressed(){
        startActivity(new Intent(this, Main.class));
    }

    public static int getTemp(){
        return temp;
    }
}
