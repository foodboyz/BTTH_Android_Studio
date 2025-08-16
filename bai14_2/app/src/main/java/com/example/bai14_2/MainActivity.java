package com.example.bai14_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edttim;
    ListView lv1, lv2, lv3;
    TabHost tabHost;

    ArrayList<Item> list1, list2, list3;
    MyArrayAdapter myarray1, myarray2, myarray3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();

        // Tải dữ liệu mặc định cho tab đầu tiên khi ứng dụng khởi chạy
        loadDataForTab1();
    }

    private void addControls() {
        // Ánh xạ TabHost
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup(); // Phải có lệnh này trước khi thêm tab

        // Tạo các tab
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.search));
        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.list));
        tabHost.addTab(tab2);

        TabHost.TabSpec tab3 = tabHost.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("", getResources().getDrawable(R.drawable.favourite));
        tabHost.addTab(tab3);

        // Ánh xạ các control khác
        edttim = findViewById(R.id.edttim);
        lv1 = findViewById(R.id.lv1);
        lv2 = findViewById(R.id.lv2);
        lv3 = findViewById(R.id.lv3);

        // Khởi tạo các ArrayList
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();

        // Khởi tạo các Adapter
        myarray1 = new MyArrayAdapter(MainActivity.this, R.layout.listitem, list1);
        myarray2 = new MyArrayAdapter(MainActivity.this, R.layout.listitem, list2);
        myarray3 = new MyArrayAdapter(MainActivity.this, R.layout.listitem, list3);

        // Gán Adapter cho các ListView
        lv1.setAdapter(myarray1);
        lv2.setAdapter(myarray2);
        lv3.setAdapter(myarray3);
    }

    private void addEvents() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equalsIgnoreCase("t1")) {
                    loadDataForTab1();
                } else if (tabId.equalsIgnoreCase("t2")) {
                    loadDataForTab2();
                } else if (tabId.equalsIgnoreCase("t3")) {
                    loadDataForTab3();
                }
            }
        });
    }


    private void loadDataForTab1() {
        list1.clear();
        // Dữ liệu đúng theo ảnh mẫu
        list1.add(new Item("52300", "Em là ai Tôi là ai", 0));
        list1.add(new Item("52600", "Bài ca đất Phương Nam", 0)); // Sửa lại tiêu đề và trạng thái
        list1.add(new Item("52567", "Buồn của Anh", 1)); // Thêm bài hát này
        myarray1.notifyDataSetChanged();
    }

    // Nạp dữ liệu cho Tab 2
    // Sửa lại hàm này
    private void loadDataForTab2() {
        list2.clear();
        // Dữ liệu đúng theo ảnh mẫu
        list2.add(new Item("57236", "Gởi em ở cuối sông hồng", 1)); // Sửa lại trạng thái
        list2.add(new Item("51548", "Quê hương tuổi thơ tôi", 0)); // Sửa lại tiêu đề
        list2.add(new Item("51748", "Em ơi", 1)); // Thêm bài hát này
        myarray2.notifyDataSetChanged();
    }

    // Nạp dữ liệu cho Tab 3
    // Sửa lại hàm này
    private void loadDataForTab3() {
        list3.clear();
        // Dữ liệu đúng theo ảnh mẫu
        list3.add(new Item("57689", "Hát với dòng sông", 1));
        list3.add(new Item("58716", "Say tình _ Remix", 0));
        list3.add(new Item("58916", "Người hãy quên em đi", 1)); // Thêm bài hát này
        myarray3.notifyDataSetChanged();
    }
}