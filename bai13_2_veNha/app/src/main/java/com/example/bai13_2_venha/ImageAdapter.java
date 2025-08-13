package com.example.bai13_2_venha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    private Integer[] imageIds;

    public ImageAdapter(Context c, Integer[] imageIds) {
        this.context = c;
        this.imageIds = imageIds;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }

    @Override
    public Object getItem(int position) {
        return imageIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // Nếu view chưa được tạo, "thổi phồng" nó từ layout grid_item_layout.xml
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_item_layout, null);
        }

        // Lấy ImageView từ layout và gán ảnh cho nó
        imageView = convertView.findViewById(R.id.grid_image);
        imageView.setImageResource(imageIds[position]);

        return convertView;
    }
}