package com.example.truong.onthi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity
{
    private EditText edtName,edtPrice;
    private Spinner edtProId;
    private Button btnSua;
    private DBManager sqLiteHepler;
    private PhoneDAO phoneDAO;
    private List<PhoneNameSX> phoneNameSXList;
    private int id;
    private String name;
    private String proID;
    private int price;
    private String maSX;
    private int idSX =2;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initUI();
        sqLiteHepler = new DBManager(this);
        phoneDAO = new PhoneDAO(sqLiteHepler);
        setData();

        clickSpiner();
        click();
    }

    private void setData()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Phone phone = (Phone) bundle.getSerializable("DATA");
        id = phone.id;
        edtName.setText(phone.name);
        edtPrice.setText(String.valueOf(phone.price));
    }

    private void initUI()
    {
        edtName = findViewById(R.id.ed_ud_name);
        edtPrice = findViewById(R.id.ed_ud_price);
        edtProId = findViewById(R.id.ed_ud_pro_id);
        btnSua = findViewById(R.id.btnSua);
    }

    private void click()
    {
        btnSua.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String price = edtPrice.getText().toString().trim();
                int price_1 = Integer.parseInt(price);

                if (name.isEmpty()){
                    edtName.setError("Empty Name");
                    return;
                }
                if (price.isEmpty()){
                    edtPrice.setError("Empty Price");
                    return;
                }

                Phone phone = new Phone(id,name,price_1,maSX);
                long result = phoneDAO.update(phone);
                Toast.makeText(UpdateActivity.this, ""+ result, Toast.LENGTH_SHORT).show();
                setResult(999);
                finish();
            }
        });
    }

    private void clickSpiner()
    {
        phoneNameSXList = phoneDAO.listAllPhoneNameSX();
        ArrayAdapter<PhoneNameSX> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, phoneNameSXList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        edtProId.setAdapter(adapter);

        edtProId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(UpdateActivity.this, edtProId.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                PhoneNameSX phoneNameSX = (PhoneNameSX) adapterView.getItemAtPosition(i);
                maSX = phoneNameSX.getNameSX();
                idSX = phoneNameSX.getIdSX();
                Log.d("maSX", "onItemSelected: "+maSX);
                Log.d("idSX", "onItemSelected: "+idSX);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        Log.d("POSITION", "clickSpiner: "+id);
        edtProId.setSelection(id);
    }
}
