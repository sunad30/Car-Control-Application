package com.example.joaop.carcontrolapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements View.OnClickListener{
    private Button goToMenu, addNewUser, changePassword, end, cancel;
    private float x1,x2,y1,y2;
    private TextView addUserBtn, changePasswordBtn, logoutBtn;
    private EditText newUser, oldPassword, newPassword;
    private String password;
    private static int som=0, not=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        goToMenu = (Button) findViewById(R.id.goToMenu);
        goToMenu.setOnClickListener(this);

        addUserBtn = (TextView) findViewById(R.id.addUser);
        addUserBtn.setOnClickListener(this);
        newUser = (EditText) findViewById(R.id.newUser);
        addNewUser = (Button) findViewById(R.id.addNewUser);
        addNewUser.setOnClickListener(this);

        password = "admin";
        changePasswordBtn = (TextView) findViewById(R.id.changePass);
        changePasswordBtn.setOnClickListener(this);
        oldPassword = (EditText) findViewById(R.id.oldPass);
        newPassword = (EditText) findViewById(R.id.newPass);
        changePassword = (Button) findViewById(R.id.confirmChangePass);
        changePassword.setOnClickListener(this);

        logoutBtn = (TextView) findViewById(R.id.logout);
        logoutBtn.setOnClickListener(this);

        end = (Button) findViewById(R.id.end);
        end.setOnClickListener(this);

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);


        oldPassword.setVisibility(View.INVISIBLE);
        newPassword.setVisibility(View.INVISIBLE);
        changePassword.setVisibility(View.INVISIBLE);
        newUser.setVisibility(View.INVISIBLE);
        addNewUser.setVisibility(View.INVISIBLE);
        end.setVisibility(View.INVISIBLE);
        cancel.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onResume() {

        super.onResume();
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

    private Toast msg;
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmChangePass:
                if(Login.getPASS().equals(oldPassword.getText().toString())) {
                    Login.changePass(newPassword.getText().toString());
                    if(msg != null){
                        msg.cancel();
                    }
                    msg= Toast.makeText(this, "Action Submitted", Toast.LENGTH_SHORT);
                    msg.show();
                } else {
                    if(msg != null){
                        msg.cancel();
                    }
                    msg= Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT);
                    msg.show();
                }
                break;
            case R.id.addNewUser:
                if(msg != null){
                    msg.cancel();
                }
                msg= Toast.makeText(this, "Action Submitted", Toast.LENGTH_SHORT);
                msg.show();
                break;
            case R.id.goToMenu:
                newUser.setVisibility(View.INVISIBLE);
                addNewUser.setVisibility(View.INVISIBLE);
                startActivity(new Intent(this, Main.class));
                break;
            case R.id.addUser:
                if(addNewUser.getVisibility() == View.VISIBLE) {
                    newUser.setVisibility(View.INVISIBLE);
                    addNewUser.setVisibility(View.INVISIBLE);
                } else {
                    newUser.setVisibility(View.VISIBLE);
                    addNewUser.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.changePass:
                if(changePassword.getVisibility() == View.VISIBLE) {
                    oldPassword.setVisibility(View.INVISIBLE);
                    newPassword.setVisibility(View.INVISIBLE);
                    changePassword.setVisibility(View.INVISIBLE);
                } else {
                    oldPassword.setVisibility(View.VISIBLE);
                    newPassword.setVisibility(View.VISIBLE);
                    changePassword.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.logout:
                if(end.getVisibility() == View.VISIBLE) {
                    end.setVisibility(View.INVISIBLE);
                    cancel.setVisibility(View.INVISIBLE);
                } else {
                    end.setVisibility(View.VISIBLE);
                    cancel.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.cancel:
                end.setVisibility(View.INVISIBLE);
                cancel.setVisibility(View.INVISIBLE);
                break;
            case R.id.end:
                startActivity(new Intent(this, Login.class));
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
