package com.example.truong.onthi;

import java.io.Serializable;

public class Phone implements Serializable {
    public int id;
    public String name;
    public float price;
    public String pro_id;

    public Phone(int id, String name, float price, String pro_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.pro_id = pro_id;
    }
}
