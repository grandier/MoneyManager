package com.mm.moneymanager.model;

public class ExpenseList {
    private int User_Id;
    private int KatPeng_Id;
    private int Pengeluaran_Id;
    private String Deskripsi;
    private int Jumlah;
    private String Tanggal;
    private String Jenis;

    public int getUser_Id() {
        return User_Id;
    }

    public int getKatPeng_Id() {
        return KatPeng_Id;
    }

    public int getPengeluaran_Id() {
        return Pengeluaran_Id;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public int getJumlah() {
        return Jumlah;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public String getJenis() {
        return Jenis;
    }

    public String toString() {
        return Deskripsi + "\n " + Jumlah;
    }

}
