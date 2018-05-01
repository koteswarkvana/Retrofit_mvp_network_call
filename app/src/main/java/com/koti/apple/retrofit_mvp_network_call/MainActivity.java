package com.koti.apple.retrofit_mvp_network_call;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IpMvp.View {

    private static final String TAG = "MainActivity";
    private TextView tv_city_name;
    private IpModel mModel;
    private IpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mModel = new IpModel(NetworkHandler.instance());
        mPresenter = new IpPresenter(mModel);
        tv_city_name = findViewById(R.id.tv_city_name);
        findViewById(R.id.bt_get_ip_info).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get_ip_info:
                Log.e(TAG, "onClick: button clicked >>");
                mPresenter.getIpInfo();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
    }

    @Override
    public void dispalyInfo(IpApiPojoBean ipApiPojoBean) {
        tv_city_name.setText(ipApiPojoBean.getCity());
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
