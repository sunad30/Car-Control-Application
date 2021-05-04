package com.example.joaop.carcontrolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private static String USER="", PASS="";

    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView loginAttempts;
    private static int attempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.login_password);
        loginButton = (Button)findViewById(R.id.login_button);
        loginAttempts = (TextView)findViewById(R.id.login_attempts);

        attempts = 3;

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });

    }

    private void validate(String userUsername, String userPassword) {

        if(userUsername.equals(USER) && userPassword.equals(PASS)) {
            Intent intent = new Intent(Login.this, Main.class);
            startActivity(intent);
        } else {
            attempts--;

            loginAttempts.setText(String.valueOf(attempts) + "remaining attempt(s)");

            if(attempts == 0) {
                loginAttempts.setText("No more attempts remaining.\nPlease contact your car company.");
                loginButton.setEnabled(false);
            }
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                startActivity(new Intent(this, Main.class));
                break;
        }
    }

    @Override
    public void onBackPressed() { }

    public void onStop() {
        this.finish();
        super.onStop();
    }

    public static void changePass(String p){
        PASS=p;
    }

    public static void changeUser(String p){
        USER=p;
    }

    public static String getPASS(){
        return PASS;
    }
}
