package com.example.bai7_3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends AppCompatActivity {

    EditText edtAA, edtBB;
    Button btnSendTONG, btnSendHIEU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtAA = findViewById(R.id.edtAA);
        edtBB = findViewById(R.id.edtBB);
        btnSendTONG = findViewById(R.id.btnSendTONG);
        btnSendHIEU = findViewById(R.id.btnSendHIEU);

        Intent myIntent = getIntent();
        int a = myIntent.getIntExtra("A", 0);
        int b = myIntent.getIntExtra("B", 0);
        edtAA.setText(String.valueOf(a));
        edtBB.setText(String.valueOf(b));

        btnSendTONG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = a + b;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("KQ", sum);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        btnSendHIEU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sub = a - b;
                Intent resultIntent = new Intent();
                resultIntent.putExtra("KQ", sub);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}