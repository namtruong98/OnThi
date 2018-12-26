package com.example.truong.onthi;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private DBManager dbManager;
    private PhoneDAO phoneDAO;
    private Button btnThem,btnSXThem;
    private List<Phone> list;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private PhoneAdapter phoneAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        dbManager = new DBManager(this);
        phoneDAO = new PhoneDAO(dbManager);
        insertDemo();
        list = phoneDAO.getAllPhone();
        phoneAdapter = new PhoneAdapter(this,list);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(phoneAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(intent,999);
            }
        });
        btnSXThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddSXActivity.class);
                startActivityForResult(intent,999);
            }
        });

    }

    private void initUI()
    {
        recyclerView = findViewById(R.id.recyclerview);
        btnThem = findViewById(R.id.btnAdd);
        btnSXThem = findViewById(R.id.btnAddSX);
    }

    private void insertDemo()
    {
        phoneDAO.insert(new Phone(1,"SHARP",2000,"SAMSUNG"));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999){
            list = new ArrayList<>();
            list = phoneDAO.getAllPhone();
            phoneAdapter = new PhoneAdapter(this,list);
            linearLayoutManager = new LinearLayoutManager(this);

            recyclerView.setAdapter(phoneAdapter);
            recyclerView.setLayoutManager(linearLayoutManager);

        }
    }
}
