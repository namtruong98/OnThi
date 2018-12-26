package com.example.truong.onthi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneHolder> {
    private Context mContext;
    private List<Phone> list;
    private List<PhoneNameSX> phoneNameSXES;
    public PhoneAdapter(Context mContext, List<Phone> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public PhoneHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_layout,null,false);
        return new PhoneHolder(view);
    }

    @Override
    public void onBindViewHolder(PhoneHolder holder, final int position) {
        final Phone phone = list.get(position);
        holder.tv_id.setText(String.valueOf(phone.id));
        holder.tv_name.setText(phone.name);
        holder.tv_price.setText(String.valueOf(phone.price) + "$");
        holder.tv_pro_id.setText(phone.pro_id);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBManager dbManager = new DBManager(mContext);
                PhoneDAO phoneDAO = new PhoneDAO(dbManager);
                phoneDAO.delete(phone);
                list.remove(position);
                notifyDataSetChanged();
                Toast.makeText(mContext, "Delete", Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,UpdateActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("DATA", phone);
                intent.putExtras(bundle);
                ((Activity)mContext).startActivityForResult(intent,999);
            }
        });
        holder.linerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ViewLayout.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Phone", phone);
                intent.putExtras(bundle);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
