package com.koti.apple.retrofit_mvp_network_call;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IpApi {
    String URL = "https://ipapi.co";

    @GET("/json")
    Call<IpApiPojoBean> getIpInfo();
}
