package com.example.giuaky;

import java.util.Arrays;

public class detailCTCB {
    private  byte[] hinhanh;
    private String baiso,diem;

    @Override
    public String toString() {
        return "detailCTCB{" +
                "hinhanh=" + Arrays.toString(hinhanh) +
                ", baiso='" + baiso + '\'' +
                ", diem='" + diem + '\'' +
                '}';
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getBaiso() {
        return baiso;
    }

    public void setBaiso(String baiso) {
        this.baiso = baiso;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public detailCTCB(byte[] hinhanh, String baiso, String diem) {
        this.hinhanh = hinhanh;
        this.baiso = baiso;
        this.diem = diem;
    }
    public detailCTCB() {
        this.hinhanh = hinhanh;
        this.baiso = baiso;
        this.diem = diem;
    }
}
