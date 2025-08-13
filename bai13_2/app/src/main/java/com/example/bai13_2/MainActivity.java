package com.example.bai13_2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    String arr1[] = {"Ipad", "Iphone", "Samsung", "Oppo", "Vivo", "Xiaomi", "Nokia", "Realme", "Asus", "Lenovo", "Acer", "Dell", "HP", "Macbook", "Sony"};
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
        final TextView selection = findViewById(R.id.selection);
        final GridView gv = findViewById(R.id.gridView1);
        ArrayAdapter<String>da = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr1);
        gv.setAdapter(da);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selection.setText(arr1[position]);
            }
        });
    }
}