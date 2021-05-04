package com.example.joaop.carcontrolapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Status extends AppCompatActivity implements View.OnClickListener{
    private float x1, x2, y1, y2;
    private Button goToMenu;
    private ImageButton info;
    private static int other=0;
    public static int notific=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        goToMenu = (Button) findViewById(R.id.goToMenu);
        goToMenu.setOnClickListener(this);

        info = (ImageButton) findViewById(R.id.info);
        info.setOnClickListener(this);

        Intent intent = getIntent();

        if(notific==0){
            notific=1;
            CountDownTimer ct = new CountDownTimer(30000, 1000) {

                @Override
                public void onTick(long l) { }

                public void onFinish() {
                    other=(other==1)?0:1;
                    notificationCall();
                    updateState();
                }
            }.start();
        }
        if(intent.hasExtra("notify")){
            notific=0;
        }

        updateState();
    }

    public void notificationCall(){
        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.Alerts)
                .setContentTitle("New Alerts!!")
                .setContentText("Consult state")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent aint = new Intent(this, Status.class);
        aint.putExtra("notify",1);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, aint, PendingIntent.FLAG_UPDATE_CURRENT);


        mBuilder.setContentIntent(pendingIntent);

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(1, mBuilder.build());
    }


    public void updateState(){
        if(other==0){
            ((ImageView)findViewById(R.id.oleo)).setVisibility(View.INVISIBLE);
            ((ImageView)findViewById(R.id.temp)).setVisibility(View.VISIBLE);
            ((ImageView)findViewById(R.id.tire)).setVisibility(View.VISIBLE);
            ((ImageView)findViewById(R.id.state)).setBackgroundResource(R.drawable.status_text_example);
            Alerts.wone=0;
        }else{
            ((ImageView)findViewById(R.id.oleo)).setVisibility(View.VISIBLE);
            ((ImageView)findViewById(R.id.temp)).setVisibility(View.VISIBLE);
            ((ImageView)findViewById(R.id.tire)).setVisibility(View.INVISIBLE);
            ((ImageView)findViewById(R.id.state)).setBackgroundResource(R.drawable.status_text_example2);
            Alerts.wone=1;
        }
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
        switch (v.getId()){
            case R.id.info:
                startActivity(new Intent(this, helpStatus.class));
                break;

            case R.id.goToMenu:
                startActivity(new Intent(this, Main.class));
                break;
        }
    }

    public void onStop() {
        this.finish();
        super.onStop();
    }

    public void onBackPressed(){
        startActivity(new Intent(this, Main.class));
    }
}
