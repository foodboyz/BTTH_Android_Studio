package com.example.bai4_2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btnBMI;
    EditText edtten, edtchieucao, edtcannag, edtBMI, edtChuanDoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TableLayout1), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBMI = findViewById(R.id.btnBMI);
        edtten = findViewById(R.id.edtten);
        edtchieucao = findViewById(R.id.edtchieucao);
        edtcannag = findViewById(R.id.edtcannag);
        edtBMI = findViewById(R.id.edtBMI);
        edtChuanDoan = findViewById(R.id.edtChuanDoan);

        btnBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double chieucao = Double.parseDouble(edtchieucao.getText().toString());
                double cannag = Double.parseDouble(edtcannag.getText().toString());
                double BMI = cannag / Math.pow(chieucao, 2);
                String chandoan = "";
                if (BMI < 18) {
                    chandoan = "Bạn gầy";
                } else if (BMI < 25) {
                    chandoan = "Bạn bình thường";
                } else if (BMI < 30) {
                    chandoan = "Bạn béo phì độ 1";
                } else if (BMI < 35) {
                    chandoan = "Bạn béo phì độ 2";
                } else {
                    chandoan = "Bạn béo phì độ 3";
                }
                DecimalFormat dcf = new DecimalFormat("#.0");
                edtBMI.setText(dcf.format(BMI));
                edtChuanDoan.setText(chandoan);
            }
        });
    }
}