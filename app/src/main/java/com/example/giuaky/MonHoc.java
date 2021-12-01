package com.example.giuaky;

import java.util.Arrays;

public class MonHoc {
    private String ma, ten;
    private byte[] hinhanh;

    @Override
    public String toString() {
        return "MonHoc{" +
                "ma='" + ma + '\'' +
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

    public MonHoc(String ma, String ten, byte[] hinhanh) {
        this.ma = ma;
        this.ten = ten;
        this.hinhanh = hinhanh;
    }
    public MonHoc() {
        this.ma = ma;
        this.ten = ten;
        this.hinhanh = hinhanh;
    }
}
