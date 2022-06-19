package com.mm.moneymanager.model;

/**
 * The type Income category list.
 */
public class IncomeCategoryList {
    private int KatPend_Id;
    private String Jenis;
    private int User_Id;

    /**
     * Gets kat pend id.
     *
     * @return the kat pend id
     */
    public int getKatPend_Id() {
        return KatPend_Id;
    }

    /**
     * Gets jenis.
     *
     * @return the jenis
     */
    public String getJenis() {
        return Jenis;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUser_Id() {
        return User_Id;
    }

    public String toString() {
        return Jenis;
    }
}
