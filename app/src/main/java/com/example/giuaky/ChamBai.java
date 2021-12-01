package com.example.giuaky;

import com.github.mikephil.charting.data.PieEntry;

public class ChamBai {
    private String sophieu, mamh, soluongbai;
    private  int baidacham, baichuacham;

    @Override
    public String toString() {
        return "ChamBai{" +
                "sophieu='" + sophieu + '\'' +
                ", mamh='" + mamh + '\'' +
                ", soluongbai='" + soluongbai + '\'' +
                ", baidacham='" + baidacham + '\'' +
                ", baichuacham='" + baichuacham + '\'' +
                '}';
    }

    public String getSophieu() {
        return sophieu;
    }

    public void setSophieu(String sophieu) {
        this.sophieu = sophieu;
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

    public ChamBai(String sophieu, String mamh, String soluongbai, int baidacham, int baichuacham) {
        this.sophieu = sophieu;
        this.mamh = mamh;
        this.soluongbai = soluongbai;
        this.baidacham = baidacham;
        this.baichuacham = baichuacham;
    }
    public ChamBai() {
        this.sophieu = sophieu;
        this.mamh = mamh;
        this.soluongbai = soluongbai;
        this.baidacham = baidacham;
        this.baichuacham = baichuacham;
    }
}
