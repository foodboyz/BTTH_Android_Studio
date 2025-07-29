package com.example.bai7_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnRequest;
    EditText edtA, edtB, edtKQ;
    ActivityResultLauncher<Intent> myActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    // Đây là nơi xử lý kết quả trả về, thay thế cho hàm onActivityResult cũ
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            int kq = data.getIntExtra("KQ", 0);
                            edtKQ.setText(String.valueOf(kq)); // Cách an toàn hơn để set số vào EditText
                        }
                    }
                }
            });
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
        btnRequest = findViewById(R.id.btnRequest);
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKQ = findViewById(R.id.edtKQ);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strA = edtA.getText().toString();
                String strB = edtB.getText().toString();

                if (strA.isEmpty() || strB.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập cả hai số A và B", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    Intent myIntent = new Intent(MainActivity.this, SubActivity.class);
                    int a = Integer.parseInt(strA);
                    int b = Integer.parseInt(strB);

                    myIntent.putExtra("A", a);
                    myIntent.putExtra("B", b);

                    myActivityResultLauncher.launch(myIntent);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Dữ liệu nhập không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}