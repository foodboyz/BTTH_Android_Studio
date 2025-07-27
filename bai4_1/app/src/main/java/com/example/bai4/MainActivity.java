package com.example.bai4;

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
    EditText edtdoC, edtdoF;
    Button btnCF, btnFC, btnClear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.LinearLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtdoC = findViewById(R.id.txtCel);
        edtdoF = findViewById(R.id.txtFar);
        btnCF = findViewById(R.id.btnFar);
        btnFC = findViewById(R.id.btnCel);
        btnClear = findViewById(R.id.btnClear);

        btnCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doF_str = edtdoF.getText().toString();
                if (!doF_str.isEmpty()) {
                    DecimalFormat df = new DecimalFormat("#.00");
                    double doF_val = Double.parseDouble(doF_str);
                    double doC_val = (doF_val * 9.0 / 5.0) + 32;
                    edtdoC.setText(df.format(doC_val));
                }
            }
        });
        btnFC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doC_str = edtdoC.getText().toString();
                if (!doC_str.isEmpty()) {
                    DecimalFormat df = new DecimalFormat("#.00");
                    double doC_val = Double.parseDouble(doC_str);
                    double doF_val = (doC_val -32) * 5.0 / 9.0;
                    edtdoF.setText(df.format(doF_val));
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtdoC.setText("");
                edtdoF.setText("");
            }
        });
    }
}