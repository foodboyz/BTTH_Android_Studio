package com.example.bai13_1_customlayout;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<phone> {
    Activity context;
    int idlayout;
    ArrayList<phone> mylist;

    public MyArrayAdapter(Activity context, int idlayout, ArrayList<phone> mylist) {
        super(context, idlayout, mylist);
        this.context = context;
        this.idlayout = idlayout;
        this.mylist = mylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(idlayout, null);
        phone myPhone = mylist.get(position);
        ImageView imgPhone = convertView.findViewById(R.id.imgphone);
        TextView txtNamePhone = convertView.findViewById(R.id.txtnamephone);
        imgPhone.setImageResource(Integer.parseInt(myPhone.getImagePhone()));
        txtNamePhone.setText(myPhone.getNamePhone());
        return convertView;
    }
}
