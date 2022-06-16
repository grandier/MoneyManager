package com.mm.moneymanager.model;

import androidx.annotation.NonNull;

public class IncomeList {
    private int User_Id;
    private int KatPend_Id;
    private int Pendapatan_Id;
    private String Deskripsi;
    private int Jumlah;
    private String Tanggal;
    private String Jenis;

    public int getUser_Id() {
        return User_Id;
    }

    public int getKatPend_Id() {
        return KatPend_Id;
    }

    public int getPendapatan_Id() {
        return Pendapatan_Id;
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

    @NonNull
    public String toString() {
        return Deskripsi + "\n " + Jumlah;
    }

    private String intToString(int jumlah) {
        return String.valueOf(jumlah);
    }

}
