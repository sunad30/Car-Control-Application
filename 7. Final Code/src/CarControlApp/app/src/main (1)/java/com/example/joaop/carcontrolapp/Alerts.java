package com.example.joaop.carcontrolapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class Alertas extends AppCompatActivity implements View.OnClickListener{
    private Button ports, alert1, alert2;
    public static int wone=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertas);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.7), (int)(height*0.7));

       ports=((Button)findViewById(R.id.portas));
        alert1=((Button)findViewById(R.id.alert1));
        alert2=((Button)findViewById(R.id.alert2));

       ports.setOnClickListener(this);
        alert1.setOnClickListener(this);
        alert2.setOnClickListener(this);

        if(Main.doorsOpened()){
           ports.setVisibility(View.VISIBLE);
        }else{portas.setVisibility(View.INVISIBLE);}

        if(wone==0){
            alert1.setText("Coolent level Low");
            alert2.setText("Low Tire Preassure");
        }else{
            alert1.setText("Low Oil level");
            alert2.setText("Coolent level low");
        }
    }

    public void onStop() {
        this.finish();
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.alert1:
            case R.id.alert2:
                startActivity(new Intent(this, Status.class));
                break;
            case R.id.portas:
                startActivity(new Intent(this, Controls.class));
                break;
        }
    }
}
