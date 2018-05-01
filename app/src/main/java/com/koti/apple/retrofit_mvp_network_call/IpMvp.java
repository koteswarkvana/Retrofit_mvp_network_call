package com.koti.apple.retrofit_mvp_network_call;

import retrofit2.Call;

public class IpMvp {
    public interface View {
        void dispalyInfo(IpApiPojoBean ipApiPojoBean);

        void displayError(String message);
    }

    public interface Presenter {
        void getIpInfo();

        void setView(View view);
    }

    public interface Model {
        Call<IpApiPojoBean> fetchIpInfo();
    }
}
