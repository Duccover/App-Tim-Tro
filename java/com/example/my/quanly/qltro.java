package com.example.my.quanly;

public class qltro {
    public String getTieude() {
        return Tieude;
    }

    public void setTieude(String tieude) {
        Tieude = tieude;
    }

    public String getGiaphong() {
        return Giaphong;
    }

    public void setGiaphong(String giaphong) {
        Giaphong = giaphong;
    }

    public String getDiadiem() {
        return Diadiem;
    }

    public void setDiadiem(String diadiem) {
        Diadiem = diadiem;
    }

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public qltro() {
    }

    public qltro(String tieude, String giaphong, String diadiem, byte[] anh) {
        Tieude = tieude;
        Giaphong = giaphong;
        Diadiem = diadiem;
        this.anh = anh;
    }

    String Tieude,Giaphong,Diadiem;
    byte[]anh;
}
