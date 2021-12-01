package com.example.giuaky;

public class ThongTinChamBai {
    private String ma, mamh, sobai;

    @Override
    public String toString() {
        return "ThongTinChamBai{" +
                "ma='" + ma + '\'' +
                ", mamh='" + mamh + '\'' +
                ", sobai='" + sobai + '\'' +
                '}';
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMamh() {
        return mamh;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public String getSobai() {
        return sobai;
    }

    public void setSobai(String sobai) {
        this.sobai = sobai;
    }

    public ThongTinChamBai(String ma, String mamh, String sobai) {
        this.ma = ma;
        this.mamh = mamh;
        this.sobai = sobai;
    }

    public ThongTinChamBai() {
        this.ma = ma;
        this.mamh = mamh;
        this.sobai = sobai;
    }
}
