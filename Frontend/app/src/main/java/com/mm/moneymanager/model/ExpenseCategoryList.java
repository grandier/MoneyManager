package com.mm.moneymanager.model;

/**
 * The type Expense category list.
 */
public class ExpenseCategoryList {
    private int KatPeng_Id;
    private String Jenis;
    private int User_Id;

    /**
     * Gets kat peng id.
     *
     * @return the kat peng id
     */
    public int getKatPeng_Id() {
        return KatPeng_Id;
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
