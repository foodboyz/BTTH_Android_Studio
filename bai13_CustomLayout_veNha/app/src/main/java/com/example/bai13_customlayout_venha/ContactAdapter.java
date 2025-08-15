package com.example.bai13_customlayout_venha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_contact, parent, false);
        }

        TextView tvName = convertView.findViewById(R.id.textViewContactName);
        TextView tvPhone = convertView.findViewById(R.id.textViewContactPhone);
        ImageButton btnCall = convertView.findViewById(R.id.buttonCall);
        ImageButton btnSms = convertView.findViewById(R.id.buttonSms);

        tvName.setText(contact.getName());
        tvPhone.setText(contact.getPhoneNumber());

        btnCall.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Calling " + contact.getPhoneNumber(), Toast.LENGTH_SHORT).show();
            // Thêm code để thực hiện cuộc gọi ở đây
        });

        btnSms.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Messaging " + contact.getPhoneNumber(), Toast.LENGTH_SHORT).show();
        });

        return convertView;
    }
}