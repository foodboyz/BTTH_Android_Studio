package com.example.bai13_1_customlayout;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String namePhone[] = {"Điện thoại Sky", "Điện thoại SamSung", "Điện thoại IP", "Điện thoại HTC", "Điện thoại LG", "Điện thoại WP"};
    int imagePhone[] = {R.drawable.sky, R.drawable.samsung, R.drawable.ip, R.drawable.htc, R.drawable.lg, R.drawable.wp};
    String pricePhone[] = {"5000000", "10000000", "20000000", "8000000", "8500000", "6000000"}; // THÊM MỚI

    ArrayList<phone> mylist;
    ListView lv;
    MyArrayAdapter myArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();

        for (int i = 0; i < namePhone.length; i++) {
            mylist.add(new phone(namePhone[i], imagePhone[i], pricePhone[i]));
        }

        myArrayAdapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_listview, mylist);
        lv.setAdapter(myArrayAdapter);

    }
}