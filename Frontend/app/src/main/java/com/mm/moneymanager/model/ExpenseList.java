package com.mm.moneymanager.model;

/**
 * The type Expense list.
 */
public class ExpenseList {
    private int User_Id;
    private int KatPeng_Id;
    private int Pengeluaran_Id;
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
     * Gets kat peng id.
     *
     * @return the kat peng id
     */
    public int getKatPeng_Id() {
        return KatPeng_Id;
    }

    /**
     * Gets pengeluaran id.
     *
     * @return the pengeluaran id
     */
    public int getPengeluaran_Id() {
        return Pengeluaran_Id;
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

    public String toString() {
        return Deskripsi + "\n " + Jumlah;
    }

}
