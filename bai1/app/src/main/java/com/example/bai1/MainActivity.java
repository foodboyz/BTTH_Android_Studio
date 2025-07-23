package com.example.bai1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText edt_input1, edt_input2, edt_ketQua;
    Button btn_sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        edt_input1 = findViewById(R.id.edt_input1);
        edt_input2 = findViewById(R.id.edt_input2);
        edt_ketQua = findViewById(R.id.edt_ketQua);
        btn_sum = findViewById(R.id.btn_sum);

        btn_sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int a = Integer.parseInt(edt_input1.getText().toString());
                int b = Integer.parseInt(edt_input2.getText().toString());
                int c = a + b;
                edt_ketQua.setText(String.valueOf(c));
            }
        });
    }
}