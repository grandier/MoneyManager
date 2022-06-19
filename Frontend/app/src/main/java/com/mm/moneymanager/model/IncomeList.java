package com.mm.moneymanager.model;

import androidx.annotation.NonNull;

/**
 * The type Income list.
 */
public class IncomeList {
    private int User_Id;
    private int KatPend_Id;
    private int Pendapatan_Id;
    private String Deskripsi;
    private int Jumlah;
    private String Tanggal;
    private String Jenis;

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUser_Id() {
        return User_Id;
    }

    /**
     * Gets kat pend id.
     *
     * @return the kat pend id
     */
    public int getKatPend_Id() {
        return KatPend_Id;
    }

    /**
     * Gets pendapatan id.
     *
     * @return the pendapatan id
     */
    public int getPendapatan_Id() {
        return Pendapatan_Id;
    }

    /**
     * Gets deskripsi.
     *
     * @return the deskripsi
     */
    public String getDeskripsi() {
        return Deskripsi;
    }

    /**
     * Gets jumlah.
     *
     * @return the jumlah
     */
    public int getJumlah() {
        return Jumlah;
    }

    /**
     * Gets tanggal.
     *
     * @return the tanggal
     */
    public String getTanggal() {
        return Tanggal;
    }

    /**
     * Gets jenis.
     *
     * @return the jenis
     */
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
