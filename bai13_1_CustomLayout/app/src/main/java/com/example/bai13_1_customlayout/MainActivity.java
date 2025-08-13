package com.example.bai13_1_customlayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String namePhone[] = {"Điện thoại Iphone 12", "Điện thoại Iphone 13", "Điện thoại Iphone 14", "Điện thoại Iphone 15", "Điện thoại Iphone 16", "Điện thoại Iphone 17",};
    int imagePhone[] = {R.drawable.ip, R.drawable.samsung, R.drawable.htc, R.drawable.lg, R.drawable.wp, R.drawable.sky};
    ArrayList<phone> mylist;
    ListView lv;
    MyArrayAdapter myArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        for (int i = 0; i < namePhone.length; i++) {
            mylist.add(new phone(namePhone[i], String.valueOf(imagePhone[i])));
        }
        myArrayAdapter = new MyArrayAdapter(MainActivity.this, R.layout.layout_listview, mylist);
        lv.setAdapter(myArrayAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                phone selectedPhone = mylist.get(i);


                Intent intent = new Intent(MainActivity.this, SubActivity.class);

                intent.putExtra("name", selectedPhone.getNamePhone());

                startActivity(intent);
            }
        });
    }
}