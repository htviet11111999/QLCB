package com.example.giuaky;

import java.util.Arrays;

public class GiaoVien {
    private String ma, mk, ten;
    private byte[] hinhanh;

    @Override
    public String toString() {
        return "GiaoVien{" +
                "ma='" + ma + '\'' +
                ", mk='" + mk + '\'' +
                ", ten='" + ten + '\'' +
                ", hinhanh=" + Arrays.toString(hinhanh) +
                '}';
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    public GiaoVien(String ma, String mk, String ten, byte[] hinhanh) {
        this.ma = ma;
        this.mk = mk;
        this.ten = ten;
        this.hinhanh = hinhanh;
    }
    public GiaoVien() {
        this.ma = ma;
        this.mk = mk;
        this.ten = ten;
        this.hinhanh = hinhanh;
    }
}
