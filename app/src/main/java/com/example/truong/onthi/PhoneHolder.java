package com.example.truong.onthi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

class PhoneHolder extends RecyclerView.ViewHolder
{
    public TextView tv_id;
    public TextView tv_price;
    public TextView tv_name;
    public TextView tv_pro_id;
    public Button btnDelete,btnUpdate;
    public LinearLayout linerlayout;

    public PhoneHolder(View itemView)
    {
        super(itemView);
        tv_id = itemView.findViewById(R.id.tv_id);
        tv_name = itemView.findViewById(R.id.tv_name);
        tv_price = itemView.findViewById(R.id.tv_price);
        tv_pro_id = itemView.findViewById(R.id.tv_pro_id);
        btnDelete = itemView.findViewById(R.id.btnDelete);
        btnUpdate = itemView.findViewById(R.id.btnUpdate);
        linerlayout = itemView.findViewById(R.id.linerlayout);
    }

}
