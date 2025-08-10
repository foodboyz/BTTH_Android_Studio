package com.example.bai12_2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<String> arrayWork;
    ArrayAdapter<String> arrayAdapter;
    EditText edtwork, edtHour, edtMinute;
    TextView txtdate;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lv = findViewById(R.id.listView);
        edtwork = findViewById(R.id.edtwork);
        edtHour = findViewById(R.id.edtHour);
        edtMinute = findViewById(R.id.edtMinute);
        txtdate = findViewById(R.id.txtdate);
        btnAdd = findViewById(R.id.btnAdd);


        arrayWork = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayWork);
        lv.setAdapter(arrayAdapter);


        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        txtdate.setText("Hôm Nay: " + sdf.format(currentTime));


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String work = edtwork.getText().toString().trim();
                String hour = edtHour.getText().toString().trim();
                String minute = edtMinute.getText().toString().trim();


                if (work.isEmpty() || hour.isEmpty() || minute.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Thiếu thông tin");
                    builder.setMessage("Vui lòng nhập đầy đủ công việc, giờ và phút.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                } else {

                    String item = work + " - " + hour + ":" + minute;
                    arrayWork.add(item);
                    arrayAdapter.notifyDataSetChanged();


                    edtwork.setText("");
                    edtHour.setText("");
                    edtMinute.setText("");
                    edtwork.requestFocus();
                }
            }
        });
    }
}