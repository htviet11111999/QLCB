package com.example.giuaky;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PhieuChamBai {
    private String ma, magv,mamh, soluongbai;
    private int baidacham, baichuacham;
    private String ngaygb;

    @Override
    public String toString() {
        return "PhieuChamBai{" +
                "ma='" + ma + '\'' +
                ", magv='" + magv + '\'' +
                ", mamh='" + mamh + '\'' +
                ", soluongbai='" + soluongbai + '\'' +
                ", baidacham='" + baidacham + '\'' +
                ", baichuacham='" + baichuacham + '\'' +
                ", ngaygb='" + ngaygb + '\'' +
                '}';
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMagv() {
        return magv;
    }

    public void setMagv(String magv) {
        this.magv = magv;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public String getSoluongbai() {
        return soluongbai;
    }

    public void setSoluongbai(String soluongbai) {
        this.soluongbai = soluongbai;
    }

    public int getBaidacham() {
        return baidacham;
    }

    public void setBaidacham(int baidacham) {
        this.baidacham = baidacham;
    }

    public int getBaichuacham() {
        return baichuacham;
    }

    public void setBaichuacham(int baichuacham) {
        this.baichuacham = baichuacham;
    }

    public String getNgaygb() {
        return ngaygb;
    }

    public void setNgaygb(String ngaygb) {
        this.ngaygb = ngaygb;
    }

    public PhieuChamBai(String ma, String magv, String mamh, String soluongbai, int baidacham, int baichuacham, String ngaygb) {
        this.ma = ma;
        this.magv = magv;
        this.mamh = mamh;
        this.soluongbai = soluongbai;
        this.baidacham = baidacham;
        this.baichuacham = baichuacham;
        this.ngaygb = ngaygb;
    }
    public PhieuChamBai() {
        this.ma = ma;
        this.magv = magv;
        this.mamh = mamh;
        this.soluongbai = soluongbai;
        this.baidacham = baidacham;
        this.baichuacham = baichuacham;
        this.ngaygb = ngaygb;
    }
}
