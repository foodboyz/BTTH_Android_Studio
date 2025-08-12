package com.example.bai13_1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    TextView selection;
    AutoCompleteTextView singleTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;

    String arr[] = {"hà nội", "Huế", "Sài gòn", "hà giang", "Hội an", "Kien giang", "Bắc Ninh"};

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

        // 1. Ánh xạ (liên kết) các biến Java với các View trong file XML
        selection = (TextView) findViewById(R.id.selection);
        singleTextView = (AutoCompleteTextView) findViewById(R.id.editauto);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);

        // 2. Tạo một ArrayAdapter để kết nối dữ liệu (mảng arr) với giao diện
        // Adapter này sẽ được tái sử dụng cho cả hai AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, // Context của ứng dụng
                android.R.layout.simple_list_item_1,
                arr // Nguồn dữ liệu
        );

        // 3. Cấu hình cho AutoCompleteTextView (chỉ chọn 1)
        singleTextView.setAdapter(adapter);

        // 4. Cấu hình cho MultiAutoCompleteTextView (chọn nhiều, cách nhau bởi dấu phẩy)
        multiAutoCompleteTextView.setAdapter(adapter);
        // Đặt Tokenizer để nó biết cách phân tách các mục (ở đây là bằng dấu phẩy)
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        // 5. Thêm một Listener để theo dõi sự thay đổi văn bản trong singleTextView
        singleTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Khi văn bản trong singleTextView thay đổi, cập nhật TextView 'selection'
                selection.setText(singleTextView.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                
            }
        });
    }
}