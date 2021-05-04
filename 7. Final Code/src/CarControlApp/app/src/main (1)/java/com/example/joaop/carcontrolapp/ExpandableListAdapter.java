package com.example.joaop.carcontrolapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.*;

public class ExpandableListAdapter extends BaseExpandableListAdapter implements View.OnClickListener{
    private static Button fdd, fdt, fed, fet, ped, pet, pdt, pdd, mala, capo;

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, Integer> listHashMap;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, HashMap<String, Integer> listHashMap) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listHashMap = listHashMap;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String header = (String) listDataHeader.get(groupPosition);
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.list_group, null);


        TextView headerText = (TextView) convertView.findViewById(R.id.header);
        headerText.setTypeface(null, Typeface.BOLD);
        headerText.setText(header);
        return  convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate((Integer)getChild(groupPosition, childPosition), null);

        fdd=(Button) convertView.findViewById(R.id.fdd);
        fdt=(Button) convertView.findViewById(R.id.fdt);
        fet=(Button) convertView.findViewById(R.id.fet);
        fed=(Button) convertView.findViewById(R.id.fed);
        ped=(Button) convertView.findViewById(R.id.ped);
        pet=(Button) convertView.findViewById(R.id.pet);
        pdt=(Button) convertView.findViewById(R.id.pdt);
        pdd=(Button) convertView.findViewById(R.id.pdd);
        mala=(Button) convertView.findViewById(R.id.mala);
        capo=(Button) convertView.findViewById(R.id.capo);

        fdd.setOnClickListener(this);
        fdt.setOnClickListener(this);
        fet.setOnClickListener(this);
        fed.setOnClickListener(this);
        ped.setOnClickListener(this);
        pet.setOnClickListener(this);
        pdt.setOnClickListener(this);
        pdd.setOnClickListener(this);
        mala.setOnClickListener(this);
        capo.setOnClickListener(this);

        state();

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public static Toast msgE;
    @Override
    public void onClick(View v) {
        Toast msgC=Controls.msgC;
        switch (v.getId()){
            case R.id.fdd:
                Main.enableRightFrontLight();
                state();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.rightfrontlight==1){
                    msgE= Toast.makeText(this.context, "headlight on", Toast.LENGTH_SHORT);
                }else{
                    msgE= Toast.makeText(this.context, "headlight ff", Toast.LENGTH_SHORT);
                }
                msgE.show();
                break;
            case R.id.fdt:
                Main.enableRightBackLight();
                state();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.rightbacklight==1){
                    msgE= Toast.makeText(this.context, "headlight on", Toast.LENGTH_SHORT);
                }else{
                    msgE= Toast.makeText(this.context, "headlight ff", Toast.LENGTH_SHORT);
                }
                msgE.show();
                break;
            case R.id.fed:
                Main.enableLeftFrontLight();
                state();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.leftfrontlight==1){
                    msgE= Toast.makeText(this.context, "headlight on", Toast.LENGTH_SHORT);
                }else{
                    msgE= Toast.makeText(this.context, "headlight ff", Toast.LENGTH_SHORT);
                }
                msgE.show();
                break;
            case R.id.fet:
                Main.enableLeftBackLight();
                state();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.leftbacklight==1){
                    msgE= Toast.makeText(this.context, "headlight on", Toast.LENGTH_SHORT);
                }else{
                    msgE= Toast.makeText(this.context, "headlight ff", Toast.LENGTH_SHORT);
                }
                msgE.show();
                break;
            case R.id.mala:
                Main.enableTrunk();
                state();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.trunk==1){
                    msgE= Toast.makeText(this.context, "open suitcase", Toast.LENGTH_SHORT);
                }else{
                    msgE= Toast.makeText(this.context, "close suitcase", Toast.LENGTH_SHORT);
                }
                msgE.show();
                break;
            case R.id.capo:
                Main.enableFrontHood();
                state();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.fronthood==1){
                    msgE= Toast.makeText(this.context, "open hood.", Toast.LENGTH_SHORT);
                }else{
                    msgE= Toast.makeText(this.context, "closed hood.", Toast.LENGTH_SHORT);
                }
                msgE.show();
                break;
            case R.id.pdd:
                Main.enableRightFrontDoor();
                state();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.rightfront==1){
                    msgE= Toast.makeText(this.context, "open door", Toast.LENGTH_SHORT);
                }else{
                    msgE= Toast.makeText(this.context, "close door", Toast.LENGTH_SHORT);
                }
                msgE.show();
                break;
            case R.id.pdt:
                Main.enableRightBackDoor();
                state();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.rightback==1){
                    msgE= Toast.makeText(this.context, "open door", Toast.LENGTH_SHORT);
                }else{
                    msgE= Toast.makeText(this.context, "close door", Toast.LENGTH_SHORT);
                }
                msgE.show();
                break;
            case R.id.pet:
                Main.enableLeftBackDoor();
                state();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.leftback==1){
                    msgE= Toast.makeText(this.context, "open door", Toast.LENGTH_SHORT);
                }else{
                    msgE= Toast.makeText(this.context, "close door", Toast.LENGTH_SHORT);
                }
                msgE.show();
                break;
            case R.id.ped:
                Main.enableLeftFrontDoor();
                state();
                if(msgC != null){
                    msgC.cancel();
                }
                if(msgE != null){
                    msgE.cancel();
                }
                if(Main.leftfront==1){
                    msgE= Toast.makeText(this.context, "open door", Toast.LENGTH_SHORT);
                }else{
                    msgE= Toast.makeText(this.context, "close door", Toast.LENGTH_SHORT);
                }
                msgE.show();
                break;
        }
    }


    public static void state(){
        if(Main.rightfrontlight==1){
            fdd.setBackgroundResource(R.drawable.button_selected);
        }else{
            fdd.setBackgroundResource(R.drawable.greybutton);
        }
        if(Main.rightbacklight==1){
            fdt.setBackgroundResource(R.drawable.button_selected);
        }else{
            fdt.setBackgroundResource(R.drawable.greybutton);
        }
        if(Main.leftfrontlight==1){
            fed.setBackgroundResource(R.drawable.button_selected);
        }else{
            fed.setBackgroundResource(R.drawable.greybutton);
        }
        if(Main.leftbacklight==1){
            fet.setBackgroundResource(R.drawable.button_selected);
        }else{
            fet.setBackgroundResource(R.drawable.greybutton);
        }
        if(Main.trunk==1){
            mala.setBackgroundResource(R.drawable.button_selected);
        }else{
            mala.setBackgroundResource(R.drawable.greybutton);
        }
        if(Main.fronthood==1){
            capo.setBackgroundResource(R.drawable.button_selected);
        }else{
            capo.setBackgroundResource(R.drawable.greybutton);
        }
        if(Main.rightfront==1){
            pdd.setBackgroundResource(R.drawable.button_selected);
        }else{
            pdd.setBackgroundResource(R.drawable.greybutton);
        }
        if(Main.rightback==1){
            pdt.setBackgroundResource(R.drawable.button_selected);
        }else{
            pdt.setBackgroundResource(R.drawable.greybutton);
        }
        if(Main.leftback==1){
            pet.setBackgroundResource(R.drawable.button_selected);
        }else{
            pet.setBackgroundResource(R.drawable.greybutton);
        }
        if(Main.leftfront==1){
            ped.setBackgroundResource(R.drawable.button_selected);
        }else{
            ped.setBackgroundResource(R.drawable.greybutton);
        }
    }
}
