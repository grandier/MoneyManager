package com.mm.moneymanager.request;

/**
 * The type Utils api.
 */
public class UtilsApi {

    /**
     * The constant BASE_URL_API.
     */
// 10.0.2.2 ini adalah localhost.
    public static final String BASE_URL_API = "http://10.0.2.2:3000/mm/";

    /**
     * Get api service base api service.
     *
     * @return the base api service
     */
// Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }

}
