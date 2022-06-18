package com.mm.moneymanager.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
}
