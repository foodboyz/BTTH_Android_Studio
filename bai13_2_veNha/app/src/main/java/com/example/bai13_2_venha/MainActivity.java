package com.example.bai13_2_venha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Thay mảng String bằng mảng Integer chứa ID của các ảnh trong thư mục /drawable
    Integer[] imageIds = {
            R.drawable.hinh1, R.drawable.hinh2, R.drawable.hinh3,
            R.drawable.hinh4, R.drawable.hinh5, R.drawable.hinh6,
            R.drawable.hinh7, R.drawable.hinh8, R.drawable.hinh9
            // Thêm các ảnh khác của bạn vào đây
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Không cần code EdgeToEdge cho bài tập này, có thể xóa đi cho đơn giản
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridView1);

        // Tạo và gán ImageAdapter tùy chỉnh cho GridView
        ImageAdapter adapter = new ImageAdapter(this, imageIds);
        gridView.setAdapter(adapter);

        // Xử lý sự kiện khi một item được click
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Tạo một Intent để mở ImageDetailActivity
                Intent intent = new Intent(MainActivity.this, ImageDetailActivity.class);

                // Gửi ID của ảnh được click qua cho Activity mới
                // position chính là chỉ số của ảnh trong mảng imageIds
                intent.putExtra("imageId", imageIds[position]);

                // Bắt đầu Activity mới
                startActivity(intent);
            }
        });
    }
}