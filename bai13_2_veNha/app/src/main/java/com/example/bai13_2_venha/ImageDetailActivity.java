package com.example.bai13_2_venha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ImageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        ImageView imageViewDetail = findViewById(R.id.image_view_detail);
        Button buttonBack = findViewById(R.id.button_back);

        // Nhận Intent đã được gửi từ MainActivity
        Intent intent = getIntent();

        // Lấy ID của ảnh từ Intent
        // -1 là giá trị mặc định nếu không tìm thấy key "imageId"
        int imageId = intent.getIntExtra("imageId", -1);

        if (imageId != -1) {
            // Nếu nhận được ID ảnh hợp lệ, hiển thị nó lên ImageView
            imageViewDetail.setImageResource(imageId);
        }

        // Xử lý sự kiện click cho nút Back
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Đóng Activity hiện tại và quay về màn hình trước đó (MainActivity)
                finish();
            }
        });
    }
}