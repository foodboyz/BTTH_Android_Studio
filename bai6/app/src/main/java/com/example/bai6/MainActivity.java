package com.example.bai6;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.activity.OnBackPressedCallback;

public class MainActivity extends AppCompatActivity {

    EditText editHoten, editCMND, editBosung;
    CheckBox chkdocbao, chkdocsach, chkdocoding;
    RadioGroup radioGroup1;
    Button btnsend;

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
        editHoten = findViewById(R.id.editHoten);
        editCMND = findViewById(R.id.editCMND);
        editBosung = findViewById(R.id.editBosung);
        chkdocbao = findViewById(R.id.chkdocbao);
        chkdocsach = findViewById(R.id.chkdocsach);
        chkdocoding = findViewById(R.id.chkdocoding);
        radioGroup1 = findViewById(R.id.radioGroup1);
        btnsend = findViewById(R.id.btnguitt);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                doShowInformation();
            }
        });

        setupOnBackPressed();
    }
    public void doShowInformation(){
        String hoten = editHoten.getText().toString();
        String cmnd = editCMND.getText().toString();

        hoten = hoten.trim();
        if (hoten.length() < 3){
            editHoten.requestFocus();
            editHoten.selectAll();
            Toast.makeText(this, "Họ tên phải có ít nhất 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        cmnd = cmnd.trim();
        if (cmnd.length() != 9){
            editCMND.requestFocus();
            editCMND.selectAll();
            Toast.makeText(this, "CMND phải có 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        // mac dinh chon Dai Hoc
        RadioButton raddh = findViewById(R.id.raddh);
        raddh.setChecked(true);
        // Kiem tra bang cap
        String bangcap = "";
        int selectedId = radioGroup1.getCheckedRadioButtonId();
        if (selectedId == -1){
            Toast.makeText(this, "Bạn chưa chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }

        RadioButton rad = findViewById(selectedId);
        bangcap = rad.getText().toString();

        // Kiem tra so thich
        String soThich = "";
        if (!chkdocbao.isChecked() && !chkdocsach.isChecked() && !chkdocoding.isChecked()) {
            Toast.makeText(this, "Bạn phải chọn ít nhất một sở thích", Toast.LENGTH_LONG).show();
            return;
        }
        if(chkdocbao.isChecked()){
            soThich += chkdocbao.getText().toString() + "\n";
        }
        if(chkdocsach.isChecked()){
            soThich += chkdocsach.getText().toString() + "\n";
        }
        if(chkdocoding.isChecked()){
            soThich += chkdocoding.getText().toString() + "\n";
        }
        String boSung = editBosung.getText().toString();

        String msg = hoten + "\n";
        msg += cmnd +"\n";
        msg += bangcap + "\n";
        msg += soThich;
        msg += "--------------------------------------------\n";
        msg += "Thông tin bổ sung:\n";
        msg += boSung + "\n";
        msg += "--------------------------------------------";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setMessage(msg);
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }
    private void setupOnBackPressed() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Question");
                b.setMessage("Are you sure you want to exit?");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                b.create().show();
            }
        };
        // Đăng ký callback này với hệ thống
        getOnBackPressedDispatcher().addCallback(this, callback);
    }
}