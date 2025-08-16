package com.example.bai17;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Tên file CSDL trong thư mục assets
    private static final String DATABASE_NAME = "qlsach.db";
    // Biến CSDL
    SQLiteDatabase database = null;

    // Các biến giao diện và dữ liệu
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ và khởi tạo ListView
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        // Xử lý sao chép và mở CSDL
        processCopy();

        // Sau khi đã có CSDL, tiến hành truy vấn và hiển thị
        loadAllBooks();
    }

    // Hàm xử lý chính: Kiểm tra và sao chép CSDL nếu cần
    private void processCopy() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) {
            try {
                copyDatabaseFromAssets();
                Toast.makeText(this, "Sao chép CSDL thành công!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    // Hàm sao chép file CSDL từ assets vào thư mục CSDL của ứng dụng
    private void copyDatabaseFromAssets() throws IOException {
        // Mở file CSDL trong assets làm input stream
        InputStream myInput = getAssets().open(DATABASE_NAME);

        // Đường dẫn đến file CSDL sẽ được tạo trong hệ thống
        String outFileName = getApplicationInfo().dataDir + "/databases/" + DATABASE_NAME;

        // Kiểm tra và tạo thư mục /databases/ nếu chưa có
        File f = new File(getApplicationInfo().dataDir + "/databases/");
        if (!f.exists()) {
            f.mkdir();
        }

        // Mở CSDL rỗng làm output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // Chuyển bytes từ input stream sang output stream
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Đóng các stream
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    // Hàm truy vấn và tải dữ liệu lên ListView
    private void loadAllBooks() {
        // Mở CSDL để đọc
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.query("tbsach", null, null, null, null, null, null);

        mylist.clear();
        while (cursor.moveToNext()) {
            String masach = cursor.getString(0);
            String tensach = cursor.getString(1);
            String tacgia = cursor.getString(2);
            String line = masach + " - " + tensach + " - " + tacgia;
            mylist.add(line);
        }
        cursor.close();
        myadapter.notifyDataSetChanged();
    }
}