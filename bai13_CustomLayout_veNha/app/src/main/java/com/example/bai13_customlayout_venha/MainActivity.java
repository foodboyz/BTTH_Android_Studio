package com.example.bai13_customlayout_venha;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Contact> contactList;
    ListView lvContacts;
    ContactAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Dùng lại activity_main.xml

        lvContacts = findViewById(R.id.lv);
        contactList = new ArrayList<>();

        contactList.add(new Contact("1-Nguyễn Văn Nam", "0932588634"));
        contactList.add(new Contact("2-Trần Văn Tú", "0932588635"));
        contactList.add(new Contact("3-Nguyễn Thị Lan", "0932588636"));
        contactList.add(new Contact("4-Nguyễn Kim Ngân", "0932588637"));

        contactAdapter = new ContactAdapter(this, contactList);
        lvContacts.setAdapter(contactAdapter);
    }
}