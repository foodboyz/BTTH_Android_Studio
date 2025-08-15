package com.example.bai13_2_customlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String[] arrayName = {"Ảnh 1", "Ảnh 2", "Ảnh 3", "Ảnh 4", "Ảnh 5",
            "Ảnh 6", "Ảnh 7", "Ảnh 8", "Ảnh 9", "Ảnh 10", "Ảnh 11", "Ảnh 12"};
    public static int[] imageName = {R.drawable.hinh1, R.drawable.hinh2, R.drawable.hinh3,
            R.drawable.hinh4, R.drawable.hinh5, R.drawable.hinh6, R.drawable.hinh7,
            R.drawable.hinh8, R.drawable.hinh9, R.drawable.hinh10, R.drawable.hinh11,
            R.drawable.hinh12};

    GridView gridViewDemo;

    MyArrayAdapter adapterDanhSach;
    ArrayList<Image> arrimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewDemo = (GridView) findViewById(R.id.grid1);
        arrimage = new ArrayList<Image>();

        adapterDanhSach = new MyArrayAdapter(MainActivity.this,
                R.layout.listitem,
                arrimage);

        gridViewDemo.setAdapter(adapterDanhSach);


        for (int i = 0; i < imageName.length; i++) {
            Image myimage = new Image();
            myimage.setName(arrayName[i]);
            myimage.setImg(imageName[i]);
            arrimage.add(myimage);
            adapterDanhSach.notifyDataSetChanged();
        }

        gridViewDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent1 = new Intent(MainActivity.this, SubActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("TITLE", position);
                intent1.putExtras(bundle);
                startActivity(intent1);
            }
        });
    }
}