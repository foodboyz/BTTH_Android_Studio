package com.example.bai15;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Khai báo các biến giao diện
    EditText edtmalop, edttenlop, edtsiso;
    Button btninsert, btndelete, btnupdate, btnquery;
    ListView lv;

    // Khai báo các biến cho ListView
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    // Khai báo biến cho CSDL
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ ID cho các view
        edtmalop = findViewById(R.id.edtmalop);
        edttenlop = findViewById(R.id.edttenlop);
        edtsiso = findViewById(R.id.edtsiso);
        btninsert = findViewById(R.id.btninsert);
        btndelete = findViewById(R.id.btndelete);
        btnupdate = findViewById(R.id.btnupdate);
        btnquery = findViewById(R.id.btnquery);
        lv = findViewById(R.id.lv);

        // Thiết lập ListView
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        // Tạo hoặc mở CSDL
        mydatabase = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);

        // Tạo table nếu chưa tồn tại
        try {
            String sql = "CREATE TABLE IF NOT EXISTS tbllop(malop TEXT PRIMARY KEY, tenlop TEXT, siso INTEGER)";
            mydatabase.execSQL(sql);
        } catch (Exception e) {
            Log.e("Error", "Bảng đã tồn tại");
        }

        // Gán sự kiện cho các nút
        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ EditText
                String malop = edtmalop.getText().toString();
                String tenlop = edttenlop.getText().toString();
                int siso = Integer.parseInt(edtsiso.getText().toString());

                // Tạo đối tượng chứa dữ liệu
                ContentValues myvalue = new ContentValues();
                myvalue.put("malop", malop);
                myvalue.put("tenlop", tenlop);
                myvalue.put("siso", siso);

                // Thêm dữ liệu vào bảng
                String msg = "";
                if (mydatabase.insert("tbllop", null, myvalue) == -1) {
                    msg = "Thêm bản ghi thất bại!";
                } else {
                    msg = "Thêm bản ghi thành công!";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                // Cập nhật lại ListView
                loadData();
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtmalop.getText().toString();
                int n = mydatabase.delete("tbllop", "malop = ?", new String[]{malop});
                String msg = "";
                if (n == 0) {
                    msg = "Không có bản ghi nào để xóa!";
                } else {
                    msg = n + " bản ghi đã được xóa.";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                loadData();
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String malop = edtmalop.getText().toString();
                String tenlop = edttenlop.getText().toString();
                int siso = Integer.parseInt(edtsiso.getText().toString());

                ContentValues myvalue = new ContentValues();
                // Không cập nhật khóa chính (malop)
                myvalue.put("tenlop", tenlop);
                myvalue.put("siso", siso);

                int n = mydatabase.update("tbllop", myvalue, "malop = ?", new String[]{malop});
                String msg = "";
                if (n == 0) {
                    msg = "Không có bản ghi nào để cập nhật!";
                } else {
                    msg = n + " bản ghi đã được cập nhật.";
                }
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                loadData();
            }
        });

        btnquery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        // Tải dữ liệu lần đầu khi ứng dụng khởi chạy
        loadData();
    }

    // Hàm tải dữ liệu và cập nhật ListView
    private void loadData() {
        mylist.clear(); // Xóa dữ liệu cũ
        Cursor c = mydatabase.query("tbllop", null, null, null, null, null, null);
        c.moveToFirst(); // Di chuyển con trỏ về bản ghi đầu tiên

        while (!c.isAfterLast()) {
            String data = c.getString(0) + " - " + c.getString(1) + " - " + c.getString(2);
            mylist.add(data);
            c.moveToNext();
        }
        c.close(); // Đóng con trỏ để giải phóng tài nguyên
        myadapter.notifyDataSetChanged(); // Báo cho Adapter biết dữ liệu đã thay đổi
    }
}