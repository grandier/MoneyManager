package com.mm.moneymanager.request;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
}
