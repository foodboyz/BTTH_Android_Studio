package com.example.bai14_1;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity; // SỬ DỤNG AppCompatActivity

import java.util.ArrayList;

// SỬA LẠI: Kế thừa từ AppCompatActivity, không dùng TabActivity
public class MainActivity extends AppCompatActivity {

    EditText edta, edtb;
    Button btncong;
    ListView lv1;
    ArrayList<String> list;
    ArrayAdapter<String> myarray;
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();
    }

    private void addEvent() {
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyPhepCong();
            }
        });
    }

    private void xuLyPhepCong() {
        try {
            int a = Integer.parseInt(edta.getText().toString());
            int b = Integer.parseInt(edtb.getText().toString());
            String ketqua = a + " + " + b + " = " + (a + b);

            list.add(ketqua);
            myarray.notifyDataSetChanged();

            edta.setText("");
            edtb.setText("");
            edta.requestFocus(); // Di chuyển con trỏ về ô nhập a

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập đủ hai số hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addControl() {
        // Ánh xạ các control
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        btncong = findViewById(R.id.btncong);
        lv1 = findViewById(R.id.lv1);

        // Khởi tạo ListView
        list = new ArrayList<>();
        myarray = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        lv1.setAdapter(myarray);

        // Cài đặt TabHost
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup(); // Phải có lệnh này

        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);

        tab1.setIndicator("", getResources().getDrawable(R.drawable.cong));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.lichsu));
        tabHost.addTab(tab2);
    }
}