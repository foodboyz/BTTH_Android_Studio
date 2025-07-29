package com.example.bai7_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class ketqua extends AppCompatActivity {

    TextView txtkq;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.ketqua);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtkq = findViewById(R.id.txtketqua);
        btnBack = findViewById(R.id.button);
        Intent myIntent = getIntent();
        Bundle mybundle = myIntent.getBundleExtra("mybackage");
        int a = mybundle.getInt("soa");
        int b = mybundle.getInt("sob");
        int kq = a + b;
        txtkq.setText(String.valueOf(kq));
        if(a==0 && b==0){
            txtkq.setText("Vô số nghiệm");
        }
        else if(a==0 && b!=0){
            txtkq.setText("Vô nghiệm");
        }
        else{
            DecimalFormat dcf = new DecimalFormat("0.##");
            txtkq.setText("x = " + dcf.format(-b*1.0/a));
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ketqua.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}