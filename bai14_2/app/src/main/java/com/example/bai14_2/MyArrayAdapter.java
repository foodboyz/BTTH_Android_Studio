package com.example.bai14_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Item> {
    Activity context;
    int layoutID;
    ArrayList<Item> arr;

    public MyArrayAdapter(Activity context, int layoutID, ArrayList<Item> arr) {
        super(context, layoutID, arr);
        this.context = context;
        this.layoutID = layoutID;
        this.arr = arr;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID, null);

        Item myItem = arr.get(position);

        TextView txtmaso = convertView.findViewById(R.id.txtmaso);
        TextView txttieude = convertView.findViewById(R.id.txttieude);
        ImageButton btnlike = convertView.findViewById(R.id.btnlike);

        txtmaso.setText(myItem.getMaso());
        txttieude.setText(myItem.getTieude());

        if (myItem.getThich() == 1) {
            btnlike.setImageResource(R.drawable.like);
        } else {
            btnlike.setImageResource(R.drawable.unlike);
        }

        // --- THÊM PHẦN CODE NÀY ĐỂ NÚT LIKE CÓ THỂ BẤM ĐƯỢC ---
        btnlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đảo ngược trạng thái thích (từ 1 thành 0, và từ 0 thành 1)
                if (myItem.getThich() == 1) {
                    myItem.setThich(0);
                    btnlike.setImageResource(R.drawable.unlike);
                } else {
                    myItem.setThich(1);
                    btnlike.setImageResource(R.drawable.like);
                }
            }
        });

        return convertView;
    }
}