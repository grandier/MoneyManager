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

public interface BaseApiService {

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("username") String username,
                                       @Field("password") String password);

    //@FormUrlEncoded
    @GET("showincome")
    Call<ResponseBody> showincomeRequest();

    @GET("showexpense")
    Call<ResponseBody> showexpenseRequest();

    @GET("showcategoryincome")
    Call<ResponseBody> showcategoryincomeRequest();

    @GET("showcategoryexpense")
    Call<ResponseBody> showcategoryexpenseRequest();

    @FormUrlEncoded
    @POST("insertCategoryIncome")
    Call<ResponseBody> insertCategoryIncome(@Field("Jenis") String Jenis);

    @FormUrlEncoded
    @POST("insertCategoryExpense")
    Call<ResponseBody> insertCategoryExpense(@Field("Jenis") String Jenis);

    @FormUrlEncoded
    @PUT("updateCategoryIncome")
    Call<ResponseBody> updateCategoryIncome(@Field("KatPend_Id") Integer KatPend_Id,
                                            @Field("Jenis") String Jenis);

    @FormUrlEncoded
    @PUT("updateCategoryExpense")
    Call<ResponseBody> updateCategoryExpense(@Field("KatPeng_Id") Integer KatPeng_Id,
                                              @Field("Jenis") String Jenis);

    @FormUrlEncoded
    @POST("insertIncome")
    Call<ResponseBody> insertIncome(@Field("Deskripsi") String Deskripsi,
                                    @Field("Jumlah") BigInteger Jumlah,
                                    @Field("Tanggal") String Tanggal,
                                    @Field("KatPend_Id") Integer KatPend_Id);

    @FormUrlEncoded
    @POST("insertExpense")
    Call<ResponseBody> insertExpense(@Field("Deskripsi") String Deskripsi,
                                     @Field("Jumlah") BigInteger Jumlah,
                                     @Field("Tanggal") String Tanggal,
                                     @Field("KatPeng_Id") Integer KatPeng_Id);

    @FormUrlEncoded
    @PUT("updateIncome")
    Call<ResponseBody> updateIncome(@Field("Pendapatan_Id") Integer Pendapatan_Id,
                                    @Field("Deskripsi") String Deskripsi,
                                    @Field("Jumlah") BigInteger Jumlah,
                                    @Field("Tanggal") String Tanggal,
                                    @Field("KatPend_Id") Integer KatPend_Id);

    @FormUrlEncoded
    @PUT("updateExpense")
    Call<ResponseBody> updateExpense(@Field("Pengeluaran_Id") Integer Pengeluaran_Id,
                                        @Field("Deskripsi") String Deskripsi,
                                        @Field("Jumlah") BigInteger Jumlah,
                                        @Field("Tanggal") String Tanggal,
                                        @Field("KatPeng_Id") Integer KatPeng_Id);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "deleteIncome", hasBody = true)
    Call<ResponseBody> deleteIncome(@Field("Pendapatan_Id") Integer Pendapatan_Id);

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "deleteExpense", hasBody = true)
    Call<ResponseBody> deleteExpense(@Field("Pengeluaran_Id") Integer Pengeluaran_Id);

    @GET("showuser")
    Call<ResponseBody> showuserRequest();

    @DELETE("deleteUser")
    Call<ResponseBody> deleteUser();

}
