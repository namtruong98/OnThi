package com.example.truong.onthi;

import java.io.Serializable;

public class PhoneNameSX implements Serializable {
    public int idSX;
    public String nameSX;

    public PhoneNameSX(int idSX, String nameSX) {
        this.idSX = idSX;
        this.nameSX = nameSX;
    }

    public int getIdSX() {
        return idSX;
    }

    public void setIdSX(int idSX) {
        this.idSX = idSX;
    }

    public String getNameSX() {
        return nameSX;
    }

    public void setNameSX(String nameSX) {
        this.nameSX = nameSX;
    }

    @Override
    public String toString() {
        return nameSX;
    }
}
