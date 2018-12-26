package com.example.truong.onthi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewLayout extends AppCompatActivity
{
    public TextView tv_id;
    public TextView tv_price;
    public TextView tv_name;
    public TextView tv_pro_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_layout);
        tv_id = findViewById(R.id.tv_view_id);
        tv_name = findViewById(R.id.tv_view_name);
        tv_price = findViewById(R.id.tv_view_price);
        tv_pro_id = findViewById(R.id.tv_pro_view_id);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Phone phone = (Phone) bundle.getSerializable("Phone");
        tv_id.setText(String.valueOf(phone.id));
        tv_name.setText(phone.name);
        tv_price.setText(String.valueOf(phone.price));
        tv_pro_id.setText(phone.pro_id);
    }
}