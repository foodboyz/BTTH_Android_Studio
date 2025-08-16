package com.example.bai16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Khai báo các biến giao diện
    EditText edta, edtb, edtkq;
    Button btntong, btnclear;
    TextView txtlichsu;

    // Biến để lưu chuỗi lịch sử trong bộ nhớ
    String lichsu = "";
    // Tên file để lưu SharedPreferences
    private static final String MY_PREFS_NAME = "mysave";
    private static final String HISTORY_KEY = "ls"; // Key để lưu lịch sử

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ ID cho các view
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtkq = findViewById(R.id.edtkq);
        btntong = findViewById(R.id.btntong);
        btnclear = findViewById(R.id.btnclear);
        txtlichsu = findViewById(R.id.txtlichsu);

        // --- Lấy dữ liệu đã lưu từ SharedPreferences ---
        SharedPreferences myprefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        lichsu = myprefs.getString(HISTORY_KEY, ""); // Lấy lịch sử, nếu không có thì trả về chuỗi rỗng
        txtlichsu.setText(lichsu);

        // --- Gán sự kiện cho các nút ---
        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra người dùng đã nhập đủ số chưa
                if (edta.getText().toString().isEmpty() || edtb.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ hai số A và B", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Lấy số từ EditText
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                int kq = a + b;
                edtkq.setText(String.valueOf(kq));

                // Nối chuỗi phép tính mới vào lịch sử
                String phepTinh = a + " + " + b + " = " + kq + "\n"; // Thêm \n để xuống dòng
                lichsu += phepTinh; // lichsu = lichsu + phepTinh;

                // Hiển thị lịch sử mới lên TextView
                txtlichsu.setText(lichsu);
            }
        });

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa lịch sử
                lichsu = "";
                txtlichsu.setText(lichsu);

                SharedPreferences myprefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = myprefs.edit();
                editor.remove(HISTORY_KEY);
                editor.apply();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        // --- Lưu lịch sử vào SharedPreferences khi ứng dụng bị tạm dừng (ví dụ: khi đóng app) ---
        SharedPreferences myprefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = myprefs.edit();

        editor.putString(HISTORY_KEY, lichsu);

        editor.apply(); // apply() tốt hơn commit() vì nó chạy bất đồng bộ
    }
}