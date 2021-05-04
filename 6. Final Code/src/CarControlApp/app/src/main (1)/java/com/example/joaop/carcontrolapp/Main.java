package com.example.joaop.carcontrolapp;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import android.content.*;

public class Main extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener{

    private float x1, x2, y1, y2;
    public static int  trunk=0,
                        rightfront=1,
                        rightback=0,
                        leftfront=0,
                        leftback=1,
                        fronthood=0,
                        fuel=0,
                        rightfrontlight=0,
                        rightbacklight=0,
                        leftfrontlight=0,
                        leftbacklight=0,
                        acOnControl=0;

    private ImageButton alertleftfront, alertleftback, alertleftfrontlight, alertleftbacklight,
                        alertrightfront, alertrightback, alertrightfrontlight, alertrightbacklight,
                        alertfronthood, alerttrunk, alertfuel, acOn, info;

    private Button burger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertleftfront = (ImageButton) findViewById(R.id.alertleftfront);
        alertleftfront.setOnClickListener(this);
        alertleftfront.setOnTouchListener(this);
        alertleftback = (ImageButton) findViewById(R.id.alertleftback);
        alertleftback.setOnClickListener(this);
        alertleftback.setOnTouchListener(this);
        alertleftfrontlight = (ImageButton) findViewById(R.id.alertleftfrontlight);
        alertleftfrontlight.setOnClickListener(this);
        alertleftfrontlight.setOnTouchListener(this);
        alertleftbacklight = (ImageButton) findViewById(R.id.alertleftbacklight);
        alertleftbacklight.setOnClickListener(this);
        alertleftbacklight.setOnTouchListener(this);
        alertrightfront = (ImageButton) findViewById(R.id.alertrightfront);
        alertrightfront.setOnClickListener(this);
        alertrightfront.setOnTouchListener(this);
        alertrightback = (ImageButton) findViewById(R.id.alertrightback);
        alertrightback.setOnClickListener(this);
        alertrightback.setOnTouchListener(this);
        alertrightfrontlight = (ImageButton) findViewById(R.id.alertrightfrontlight);
        alertrightfrontlight.setOnClickListener(this);
        alertrightfrontlight.setOnTouchListener(this);
        alertrightbacklight = (ImageButton) findViewById(R.id.alertrightbacklight);
        alertrightbacklight.setOnClickListener(this);
        alertrightbacklight.setOnTouchListener(this);
        alertfronthood = (ImageButton) findViewById(R.id.alertfronthood);
        alertfronthood.setOnClickListener(this);
        alertfronthood.setOnTouchListener(this);
        alerttrunk = (ImageButton) findViewById(R.id.alerttrunk);
        alerttrunk.setOnClickListener(this);
        alerttrunk.setOnTouchListener(this);
        alertfuel = (ImageButton) findViewById(R.id.alertfuel);
        alertfuel.setOnClickListener(this);
        alertfuel.setOnTouchListener(this);


        acOn = (ImageButton) findViewById(R.id.acOn);
        acOn.setOnClickListener(this);
        acOn.setOnTouchListener(this);



        info = (ImageButton) findViewById(R.id.info);
        info.setOnClickListener(this);

        updateState();

        burger=((Button) findViewById(R.id.burger));
        burger.setOnClickListener(this);

    }


    @Override
    public void onResume() {
        updateState();
        super.onResume();
    }

    @Override
    public void onBackPressed() { }


    public static void enableAc(){
        acOnControl=1;
    }

    public static void disableAc(){
        acOnControl=0;
    }

    public static void enableTrunk(){
        trunk=((trunk==0)? 1:0);
    }

    public static void enableFrontHood(){
        fronthood=((fronthood==0)? 1:0);
    }

    public static void enableRightFrontDoor(){
        rightfront=((rightfront==0)? 1:0);
    }

    public static void enableRightBackDoor(){
        rightback=((rightback==0)? 1:0);
    }

    public static void enableRightFrontLight(){
        rightfrontlight=((rightfrontlight==0)? 1:0);
    }

    public static void enableRightBackLight(){
        rightbacklight=((rightbacklight==0)? 1:0);
    }

    public static void enableLeftFrontDoor(){
        leftfront=((leftfront==0)? 1:0);
    }

    public static void enableLeftBackDoor(){
        leftback=((leftback==0)? 1:0);
    }

    public static void enableLeftFrontLight(){
        leftfrontlight=((leftfrontlight==0)? 1:0);
    }

    public static void enableLeftBackLight(){
        leftbacklight=((leftbacklight==0)? 1:0);
    }

    public static void enableFuel(){
        fuel=((fuel==0)? 1:0);
    }

    public static boolean isDoorsOpen(){
        return ((leftback==1)||(leftfront==1)||(rightback==1)||(rightfront==1));
    }

    public static boolean isHeadlightOn(){
        return ((leftfrontlight==1)||(rightfrontlight==1));
    }

    private void updateState(){
        if(acOnControl==1){
            acOn.setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.temperatura)).setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.temperatura)).setText(String.valueOf(AirConditioner.getTemp())+" ºC");
        }else{
            acOn.setVisibility(View.INVISIBLE);
            ((TextView)findViewById(R.id.temperatura)).setVisibility(View.INVISIBLE);
        }

        if(trunk==1){
            ((ImageView)findViewById(R.id.trunkdoor)).setVisibility(View.VISIBLE);
            alerttrunk.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.trunkdoor)).setVisibility(View.INVISIBLE);
            alerttrunk.setVisibility(View.INVISIBLE);
        }
        if(rightback==1){
            ((ImageView)findViewById(R.id.rightback)).setVisibility(View.VISIBLE);
            alertrightback.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.rightback)).setVisibility(View.INVISIBLE);
            alertrightback.setVisibility(View.INVISIBLE);
        }
        if(rightfront==1){
            ((ImageView)findViewById(R.id.rightfront)).setVisibility(View.VISIBLE);
            alertrightfront.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.rightfront)).setVisibility(View.INVISIBLE);
            alertrightfront.setVisibility(View.INVISIBLE);
        }
        if(leftback==1){
            ((ImageView)findViewById(R.id.leftback)).setVisibility(View.VISIBLE);
            alertleftback.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.leftback)).setVisibility(View.INVISIBLE);
            alertleftback.setVisibility(View.INVISIBLE);
        }
        if(leftfront==1){
            ((ImageView)findViewById(R.id.leftfront)).setVisibility(View.VISIBLE);
            alertleftfront.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.leftfront)).setVisibility(View.INVISIBLE);
            alertleftfront.setVisibility(View.INVISIBLE);
        }
        if(fronthood==1){
            ((ImageView)findViewById(R.id.fronthood)).setVisibility(View.VISIBLE);
            alertfronthood.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.fronthood)).setVisibility(View.INVISIBLE);
            alertfronthood.setVisibility(View.INVISIBLE);
        }
        if(rightbacklight==1){
            ((ImageView)findViewById(R.id.rightbacklight)).setVisibility(View.VISIBLE);
            alertrightbacklight.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.rightbacklight)).setVisibility(View.INVISIBLE);
            alertrightbacklight.setVisibility(View.INVISIBLE);
        }
        if(rightfrontlight==1){
            ((ImageView)findViewById(R.id.rightfrontlight)).setVisibility(View.VISIBLE);
            alertrightfrontlight.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.rightfrontlight)).setVisibility(View.INVISIBLE);
            alertrightfrontlight.setVisibility(View.INVISIBLE);
        }
        if(leftbacklight==1){
            ((ImageView)findViewById(R.id.leftbacklight)).setVisibility(View.VISIBLE);
            alertleftbacklight.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.leftbacklight)).setVisibility(View.INVISIBLE);
            alertleftbacklight.setVisibility(View.INVISIBLE);
        }
        if(leftfrontlight==1){
            ((ImageView)findViewById(R.id.leftfrontlight)).setVisibility(View.VISIBLE);
            alertleftfrontlight.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.leftfrontlight)).setVisibility(View.INVISIBLE);
            alertleftfrontlight.setVisibility(View.INVISIBLE);
        }
        if(fuel==1){
            ((ImageView)findViewById(R.id.fuelcap)).setVisibility(View.VISIBLE);
            alertfuel.setVisibility(View.VISIBLE);
        }else{
            ((ImageView)findViewById(R.id.fuelcap)).setVisibility(View.INVISIBLE);
            alertfuel.setVisibility(View.INVISIBLE);
        }
    }

    public static void doors(){
        if((leftback==1)||(leftfront==1)||(rightback==1)||(rightfront==1)||(trunk==1)){
            if(leftback==1)
                enableLeftBackDoor();
            if(leftfront==1)
                enableLeftFrontDoor();
            if(rightback==1)
                enableRightBackDoor();
            if(rightfront==1)
                enableRightFrontDoor();
            if(trunk==1)
                enableTrunk();
        }else{
            enableTrunk();
            enableLeftBackDoor();
            enableLeftFrontDoor();
            enableRightBackDoor();
            enableRightFrontDoor();
        }

    }

    public static boolean doorsOpened(){
        if((leftback==1)||(leftfront==1)||(rightback==1)||(rightfront==1)||(trunk==1)){
            return true;
        }else{
            return false;
        }
    }


    public static boolean headlightsOn(){
        if((leftfrontlight==1)||(rightfrontlight==1)){
            return true;
        }else {
            return false;
        }

    }


    public static void headlights(){
        if((leftfrontlight==1)||(rightfrontlight==1)){
            if(leftfrontlight==1)
                enableLeftFrontLight();
            if(rightfrontlight==1)
                enableRightFrontLight();
        }else {
            enableLeftFrontLight();
            enableRightFrontLight();
        }

    }

    public static boolean trunkOpened(){
        if(trunk==1){
            return true;
        }else{
            return false;
        }
    }


    private Toast msg;
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.burger:
                startActivity(new Intent(this, SideMenu.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

            case R.id.info:
                startActivity(new Intent(this, helpMain.class));
                break;

            case R.id.alertfronthood:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Hood open / unlocked. \nGo to Controls.", Toast.LENGTH_SHORT);
                msg.show();

                break;
            case R.id.alertrightfront:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Hanging door open / unlocked. \nGo to Controls.", Toast.LENGTH_SHORT);
                msg.show();
                break;
            case R.id.alertleftfront:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Driver's door open / unlocked. \nGo to Controls.", Toast.LENGTH_SHORT);
                msg.show();
                break;
            case R.id.alertrightback:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Door open / unlocked. \nGo to Controls.", Toast.LENGTH_SHORT);
                msg.show();
                break;
            case R.id.alertleftback:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Door open / unlocked. \nGo to Controls.", Toast.LENGTH_SHORT);
                msg.show();
                break;
            case R.id.alerttrunk:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Car trunk open / unlocked. \nGo to Controls.", Toast.LENGTH_SHORT);
                msg.show();
                break;
            case R.id.alertrightbacklight:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Damaged / on right taillight. \nGo to Controls.", Toast.LENGTH_SHORT);
                msg.show();
                break;
            case R.id.alertrightfrontlight:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Damaged / on right front light. \nGo to Controls.", Toast.LENGTH_SHORT);
                msg.show();
                break;
            case R.id.alertleftbacklight:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Left rear light damaged / on. \nGo to Controls.", Toast.LENGTH_SHORT);
                msg.show();
                break;
            case R.id.alertleftfrontlight:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Left front light damaged / on. \nGo to Controls", Toast.LENGTH_SHORT);
                msg.show();
                break;
            case R.id.alertfuel:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Tank lid open. \nGo to Controls.", Toast.LENGTH_SHORT);
                msg.show();
                break;

            case R.id.acOn:
                if(msg != null){
                    msg.cancel();
                }
                msg=Toast.makeText(this, "Air Conditioning on. \nGo to Air Conditioning.", Toast.LENGTH_SHORT);
                msg.show();
                break;

        }
    }

    public void onStop() {
        this.finish();
        super.onStop();
    }

    private long i;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(view.getId()){
            case R.id.alertfronthood:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.alertrightfront:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.alertleftfront:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.alertrightback:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.alertleftback:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.alerttrunk:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.alertrightbacklight:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.alertrightfrontlight:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.alertleftbacklight:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.alertleftfrontlight:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.alertfuel:
                if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    i=System.currentTimeMillis();
                }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if(System.currentTimeMillis()-i>800){
                        startActivity(new Intent(this, Controls.class));
                    }else{
                        onClick(view);
                    }
                }
                break;
            case R.id.acOn:if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                i=System.currentTimeMillis();
            }else if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                if(System.currentTimeMillis()-i>800){
                    startActivity(new Intent(this, Status.class));
                }else{
                    onClick(view);
                }
            }
                break;
        }
        return true;
    }

}
