package com.koti.apple.retrofit_mvp_network_call;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IpPresenter implements IpMvp.Presenter {

    private IpMvp.View mView;
    private IpMvp.Model mModel;

    public IpPresenter(IpMvp.Model model) {
        this.mModel = model;
    }

    @Override
    public void getIpInfo() {
        mModel.fetchIpInfo().enqueue(new Callback<IpApiPojoBean>() {
            @Override
            public void onResponse(Call<IpApiPojoBean> call, Response<IpApiPojoBean> response) {
                if (response.isSuccessful())
                    mView.dispalyInfo(response.body());
                else
                    mView.displayError(response.message());
            }

            @Override
            public void onFailure(Call<IpApiPojoBean> call, Throwable t) {
                mView.displayError(t.getMessage());
            }
        });
    }

    @Override
    public void setView(IpMvp.View view) {
        this.mView = view;
    }
}
