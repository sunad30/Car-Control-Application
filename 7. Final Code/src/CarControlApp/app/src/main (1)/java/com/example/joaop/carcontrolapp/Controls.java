package com.example.joaop.carcontrolapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Controls extends AppCompatActivity implements View.OnClickListener{
    private float x1, x2, y1, y2;
    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> list;
    private HashMap<String,Integer> listHash;

    private Button trunk, lock, headlight, honk, goToMenu, fdd, fdt, fed, fet, ped, pet, pdt, pdd, mala, capo;

    private ImageButton info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controls);

        listView = (ExpandableListView) findViewById(R.id.expList);
        initData();
        listAdapter = new ExpandableListAdapter(this,list,listHash);
        listView.setAdapter(listAdapter);


        trunk=(Button) findViewById(R.id.trunk);
        lock=(Button) findViewById(R.id.lock);
        headlight=(Button) findViewById(R.id.headlight);
        honk=(Button) findViewById(R.id.honk);

        honk.setOnClickListener(this);
        headlight.setOnClickListener(this);
        trunk.setOnClickListener(this);
        lock.setOnClickListener(this);


        goToMenu = (Button) findViewById(R.id.goToMenu);
        goToMenu.setOnClickListener(this);

        info = (ImageButton) findViewById(R.id.info);
        info.setOnClickListener(this);
        state();
    }

    private void initData() {
        list = new ArrayList<>();
        listHash = new HashMap<>();


        list.add("Advance controls");

        listHash.put(list.get(0), R.layout.list_item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                if(y1 < y2 && y2-y1>200 && y2-y1>x1-x2 && y2-y1>x2-x1) {
                    startActivity(new Intent(Controls.this, Main.class));
                }
                break;
        }
        return true;
    }


    public static Toast msgC;
    @Override
    public void onClick(View v) {
        Drawable d;
        Toast msgE=ExpandableListAdapter.msgE;
        switch(v.getId()){
            case R.id.info:
                startActivity(new Intent(this, helpControls.class));
                break;

            case R.id.goToMenu:
                startActivity(new Intent(this, Main.class));
                break;

            case R.id.trunk:
                Main.enableTrunk();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.trunkOpened()){
                    state();
                    msgC=Toast.makeText(this, "Open suitcase", Toast.LENGTH_SHORT);
                }else{
                    state();
                    msgC=Toast.makeText(this, "closed suitcase.", Toast.LENGTH_SHORT);
                }
                msgC.show();
                break;
            case R.id.honk:
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                msgC=Toast.makeText(this, "action submitted", Toast.LENGTH_SHORT);
                msgC.show();
                break;
            case R.id.lock:
                Main.doors();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.doorsOpened()){
                    state();
                    msgC=Toast.makeText(this, "open doors", Toast.LENGTH_SHORT);
                }else{
                    state();
                    msgC=Toast.makeText(this, "closed doors.", Toast.LENGTH_SHORT);
                }
                msgC.show();
                break;
            case R.id.headlight:
                Main.headlights();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.headlightsOn()){
                    state();
                    msgC=Toast.makeText(this, "headlights on", Toast.LENGTH_SHORT);
                }else{
                    state();
                    msgC=Toast.makeText(this, "headlights off", Toast.LENGTH_SHORT);
                }
                msgC.show();
                break;
        }
    }

    private void state(){
        Drawable d;
        if(Main.trunkOpened()){
            d = trunk.getCompoundDrawables()[1];
            d.setTintList(null);
        }else{
            d = trunk.getCompoundDrawables()[1];
            d.setTint(Color.parseColor("#696969"));
        }
        if(Main.doorsOpened()){
            d = lock.getCompoundDrawables()[1];
            d.setTintList(null);
        }else{
            d = lock.getCompoundDrawables()[1];
            d.setTint(Color.parseColor("#696969"));
        }
        if(Main.headlightsOn()){
            d = headlight.getCompoundDrawables()[1];
            d.setTintList(null);
        }else{
            d = headlight.getCompoundDrawables()[1];
            d.setTint(Color.parseColor("#696969"));
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
