package com.example.giuaky;

public class GiaoBaiSpinner {
    private byte[] hinh;
    private String magv;

    @Override
    public String toString() {
        return "GiaoBaiSpinner{" +
                "hinh=" + hinh +
                ", tengv='" + magv + '\'' +
                '}';
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }

    public String getMagv() {
        return magv;
    }

    public void setMagv(String magv) {
        this.magv = magv;
    }

    public GiaoBaiSpinner(byte[] hinh, String magv) {
        this.hinh = hinh;
        this.magv = magv;
    }
    public GiaoBaiSpinner() {
        this.hinh = hinh;
        this.magv = magv;
    }
}
