package com.koti.apple.retrofit_mvp_network_call;

import retrofit2.Call;

public class IpModel implements IpMvp.Model {
    private NetworkHandler networkHandler;

    public IpModel(NetworkHandler networkHandler) {
        this.networkHandler = networkHandler;
    }

    @Override
    public Call<IpApiPojoBean> fetchIpInfo() {
        return networkHandler.ipInfoApi().getIpInfo();
    }
}
