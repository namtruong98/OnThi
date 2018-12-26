package com.example.truong.onthi;

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

import java.util.List;

public class AddActivity extends AppCompatActivity
{
    private EditText edtId,edtName,edtPrice;
    private Button btnThem;
    private Spinner spinnerProID;
    private DBManager dbManager;
    private PhoneDAO phoneDAO;
    private List<PhoneNameSX> phonenameSXList;
    private String maSX;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initUI();
        dbManager  = new DBManager(this);
        phoneDAO = new PhoneDAO(dbManager);
        insertData();
        clickSpiner();
        clickAdd();
    }

    private void initUI()
    {
        edtId = findViewById(R.id.ed_id);
        edtName = findViewById(R.id.ed_name);
        edtPrice = findViewById(R.id.ed_price);
        spinnerProID = findViewById(R.id.spn_pro_id);
        btnThem = findViewById(R.id.btnThem);
    }

    private void insertData()
    {
        phoneDAO.insertSX(new PhoneNameSX(1,"XIAOMI"));
        phoneDAO.insertSX(new PhoneNameSX(2,"IPHONE"));
        phoneDAO.insertSX(new PhoneNameSX(3,"SAMSUNG"));

    }

    private void clickAdd()
    {
        btnThem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                int id_1 = Integer.parseInt(id);
                String name = edtName.getText().toString().trim();
                String price = edtPrice.getText().toString().trim();
                int price_1 = Integer.parseInt(price);
                if (id.isEmpty()){
                    edtId.setError("Empty ID");
                    return;
                }
                if (name.isEmpty()){
                    edtName.setError("Empty Name");
                    return;
                }
                if (price.isEmpty() && price.length() > 10){
                    edtPrice.setError("Empty Price");
                    return;
                }

                Phone phone = new Phone(id_1,name,price_1, maSX);
                long result = phoneDAO.insert(phone);
                Toast.makeText(AddActivity.this, "Đã thêm" +result, Toast.LENGTH_SHORT).show();
                setResult(999);
                finish();

            }
        });
    }

    private void clickSpiner()
    {
        phonenameSXList = phoneDAO.listAllPhoneNameSX();

        Log.d("CCGIDAY", "onCreate: " +phonenameSXList.get(0).nameSX);
        ArrayAdapter<PhoneNameSX> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, phonenameSXList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinnerProID.setAdapter(adapter);

        spinnerProID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AddActivity.this, spinnerProID.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                PhoneNameSX phoneNameSX = (PhoneNameSX) adapterView.getItemAtPosition(i);
                maSX = phoneNameSX.getNameSX();
                Log.d("maSX", "onItemSelected: "+maSX);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
            }
        });
    }
}