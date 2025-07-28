package com.example.bai5_2;

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

public class MainActivity extends AppCompatActivity {
    Button btntieptuc, btngiaipt, btnthoat;
    EditText edita, editb, editc;
    TextView txtkq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.TableLayout2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btntieptuc = findViewById(R.id.btntieptuc);
        btngiaipt = findViewById(R.id.btngiaipt);
        btnthoat = findViewById(R.id.btnthoat);
        edita = findViewById(R.id.edita);
        editb = findViewById(R.id.editb);
        editc = findViewById(R.id.editc);
        txtkq = findViewById(R.id.textView5);

        btngiaipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edita.getText().toString());
                int b = Integer.parseInt(editb.getText().toString());
                int c = Integer.parseInt(editc.getText().toString());
                String kq = "";
                DecimalFormat df = new DecimalFormat("0.00");
                if (a == 0) {
                    if (b == 0) {
                        if (c == 0) {
                            kq = "Phương trình vô số nghiệm";
                        } else {
                            kq = "Phương trình vô nghiệm";
                        }
                    } else {
                        kq = "Phương trình có nghiệm x = " + (-c / b);
                    }
                }
                else {
                    double delta = b * b - 4 * a * c;
                    if (delta < 0) {
                        kq = "Phương trình vô nghiệm";
                    } else if (delta == 0) {
                        kq = "Phương trình có nghiệm kép x1 = x2 = " + df.format(-b / (2 * a));
                    } else {
                        kq = "Phương trình có 2 nghiệm x1 = " + df.format((-b + Math.sqrt(delta)) / (2 * a)) + " và x2 = " + df.format((-b - Math.sqrt(delta)) / (2 * a));
                    }
                }
                txtkq.setText(kq);
            }
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edita.setText("");
                editb.setText("");
                editc.setText("");
                edita.requestFocus();
            }
        });
    }
}