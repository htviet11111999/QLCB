package com.example.giuaky;

import java.util.Arrays;

public class ChiTietTTCB {
    private String mamh, baiso, diem,sophieu;
    private byte[] hinhanh;

    @Override
    public String toString() {
        return "ChiTietTTCB{" +
                "mamh='" + mamh + '\'' +
                ", baiso='" + baiso + '\'' +
                ", diem='" + diem + '\'' +
                ", sophieu='" + sophieu + '\'' +
                ", hinhanh=" + Arrays.toString(hinhanh) +
                '}';
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
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

    public String getSophieu() {
        return sophieu;
    }

    public void setSophieu(String sophieu) {
        this.sophieu = sophieu;
    }

    public byte[] getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(byte[] hinhanh) {
        this.hinhanh = hinhanh;
    }

    public ChiTietTTCB(String mamh, String baiso, String diem, String sophieu, byte[] hinhanh) {
        this.mamh = mamh;
        this.baiso = baiso;
        this.diem = diem;
        this.sophieu = sophieu;
        this.hinhanh = hinhanh;
    }
    public ChiTietTTCB() {
        this.mamh = mamh;
        this.baiso = baiso;
        this.diem = diem;
        this.sophieu = sophieu;
        this.hinhanh = hinhanh;
    }
}
