package com.example.bai13_2_customlayout;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Image> {
    Activity context = null;
    ArrayList<Image> myArray = null;
    int LayoutId;

    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Image> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.LayoutId = layoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);

        final Image myimage = myArray.get(position);


        final ImageView imgitem = (ImageView) convertView.findViewById(R.id.imageView1);
        imgitem.setImageResource(myimage.getImg());


        final TextView myname = (TextView) convertView.findViewById(R.id.textView1);
        myname.setText(myimage.getName());

        return convertView;
    }
}