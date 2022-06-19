package com.mm.moneymanager.request;

import java.math.BigInteger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * The interface Base api service.
 */
public interface BaseApiService {

    /**
     * Login request call.
     *
     * @param username the username
     * @param password the password
     * @return the call
     */
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    /**
     * Register request call.
     *
     * @param username the username
     * @param password the password
     * @return the call
     */
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("username") String username,
                                       @Field("password") String password);

    /**
     * Showincome request call.
     *
     * @return the call
     */
//@FormUrlEncoded
    @GET("showincome")
    Call<ResponseBody> showincomeRequest();

    /**
     * Showexpense request call.
     *
     * @return the call
     */
    @GET("showexpense")
    Call<ResponseBody> showexpenseRequest();

    /**
     * Showcategoryincome request call.
     *
     * @return the call
     */
    @GET("showcategoryincome")
    Call<ResponseBody> showcategoryincomeRequest();

    /**
     * Showcategoryexpense request call.
     *
     * @return the call
     */
    @GET("showcategoryexpense")
    Call<ResponseBody> showcategoryexpenseRequest();

    /**
     * Insert category income call.
     *
     * @param Jenis the jenis
     * @return the call
     */
    @FormUrlEncoded
    @POST("insertCategoryIncome")
    Call<ResponseBody> insertCategoryIncome(@Field("Jenis") String Jenis);

    /**
     * Insert category expense call.
     *
     * @param Jenis the jenis
     * @return the call
     */
    @FormUrlEncoded
    @POST("insertCategoryExpense")
    Call<ResponseBody> insertCategoryExpense(@Field("Jenis") String Jenis);

    /**
     * Update category income call.
     *
     * @param KatPend_Id the kat pend id
     * @param Jenis      the jenis
     * @return the call
     */
    @FormUrlEncoded
    @PUT("updateCategoryIncome")
    Call<ResponseBody> updateCategoryIncome(@Field("KatPend_Id") Integer KatPend_Id,
                                            @Field("Jenis") String Jenis);

    /**
     * Update category expense call.
     *
     * @param KatPeng_Id the kat peng id
     * @param Jenis      the jenis
     * @return the call
     */
    @FormUrlEncoded
    @PUT("updateCategoryExpense")
    Call<ResponseBody> updateCategoryExpense(@Field("KatPeng_Id") Integer KatPeng_Id,
                                              @Field("Jenis") String Jenis);

    /**
     * Insert income call.
     *
     * @param Deskripsi  the deskripsi
     * @param Jumlah     the jumlah
     * @param Tanggal    the tanggal
     * @param KatPend_Id the kat pend id
     * @return the call
     */
    @FormUrlEncoded
    @POST("insertIncome")
    Call<ResponseBody> insertIncome(@Field("Deskripsi") String Deskripsi,
                                    @Field("Jumlah") BigInteger Jumlah,
                                    @Field("Tanggal") String Tanggal,
                                    @Field("KatPend_Id") Integer KatPend_Id);

    /**
     * Insert expense call.
     *
     * @param Deskripsi  the deskripsi
     * @param Jumlah     the jumlah
     * @param Tanggal    the tanggal
     * @param KatPeng_Id the kat peng id
     * @return the call
     */
    @FormUrlEncoded
    @POST("insertExpense")
    Call<ResponseBody> insertExpense(@Field("Deskripsi") String Deskripsi,
                                     @Field("Jumlah") BigInteger Jumlah,
                                     @Field("Tanggal") String Tanggal,
                                     @Field("KatPeng_Id") Integer KatPeng_Id);

    /**
     * Update income call.
     *
     * @param Pendapatan_Id the pendapatan id
     * @param Deskripsi     the deskripsi
     * @param Jumlah        the jumlah
     * @param Tanggal       the tanggal
     * @param KatPend_Id    the kat pend id
     * @return the call
     */
    @FormUrlEncoded
    @PUT("updateIncome")
    Call<ResponseBody> updateIncome(@Field("Pendapatan_Id") Integer Pendapatan_Id,
                                    @Field("Deskripsi") String Deskripsi,
                                    @Field("Jumlah") BigInteger Jumlah,
                                    @Field("Tanggal") String Tanggal,
                                    @Field("KatPend_Id") Integer KatPend_Id);

    /**
     * Update expense call.
     *
     * @param Pengeluaran_Id the pengeluaran id
     * @param Deskripsi      the deskripsi
     * @param Jumlah         the jumlah
     * @param Tanggal        the tanggal
     * @param KatPeng_Id     the kat peng id
     * @return the call
     */
    @FormUrlEncoded
    @PUT("updateExpense")
    Call<ResponseBody> updateExpense(@Field("Pengeluaran_Id") Integer Pengeluaran_Id,
                                        @Field("Deskripsi") String Deskripsi,
                                        @Field("Jumlah") BigInteger Jumlah,
                                        @Field("Tanggal") String Tanggal,
                                        @Field("KatPeng_Id") Integer KatPeng_Id);

    /**
     * Delete income call.
     *
     * @param Pendapatan_Id the pendapatan id
     * @return the call
     */
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "deleteIncome", hasBody = true)
    Call<ResponseBody> deleteIncome(@Field("Pendapatan_Id") Integer Pendapatan_Id);

    /**
     * Delete expense call.
     *
     * @param Pengeluaran_Id the pengeluaran id
     * @return the call
     */
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "deleteExpense", hasBody = true)
    Call<ResponseBody> deleteExpense(@Field("Pengeluaran_Id") Integer Pengeluaran_Id);

    /**
     * Showuser request call.
     *
     * @return the call
     */
    @GET("showuser")
    Call<ResponseBody> showuserRequest();

    /**
     * Delete user call.
     *
     * @return the call
     */
    @DELETE("deleteUser")
    Call<ResponseBody> deleteUser();

}
