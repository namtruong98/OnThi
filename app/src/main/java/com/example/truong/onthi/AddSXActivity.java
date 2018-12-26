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

public class AddSXActivity extends AppCompatActivity
{
    private EditText edtId,edtName;
    private Button btnThemSX;
    private Spinner spinnerProID;
    private DBManager dbManager;
    private PhoneDAO phoneDAO;
    private List<PhoneNameSX> phonenameSXList;
    private String maSX;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addsx_activity);
        initUI();
        dbManager  = new DBManager(this);
        phoneDAO = new PhoneDAO(dbManager);
        clickAdd();
    }

    private void initUI()
    {
        edtId = findViewById(R.id.ed_sx_id);
        edtName = findViewById(R.id.ed_sx_name);
        spinnerProID = findViewById(R.id.spn_pro_id);
        btnThemSX = findViewById(R.id.btnSXThem);
    }

    private void clickAdd()
    {
        btnThemSX.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                int id_1 = Integer.parseInt(id);
                String name = edtName.getText().toString().trim();

                if (id.isEmpty()){
                    edtId.setError("Empty ID");
                    return;
                }
                if (name.isEmpty()){
                    edtName.setError("Empty Name");
                    return;
                }

                PhoneNameSX phoneNameSX = new PhoneNameSX(id_1,name);
                long result = phoneDAO.insertSX(phoneNameSX);
                Toast.makeText(AddSXActivity.this, "Đã thêm" +result, Toast.LENGTH_SHORT).show();
                setResult(999);
                finish();

            }
        });
    }

}