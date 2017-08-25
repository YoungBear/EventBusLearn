package com.example.eventbuslearn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.eventbuslearn.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_message)
    TextView mTvMessage;
    @BindView(R.id.btn_message)
    Button mBtnMessage;
    @BindView(R.id.btn_subscription)
    Button mBtnSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        mTvMessage.setText(messageEvent.getMessage());
    }

    @OnClick({R.id.btn_message, R.id.btn_subscription})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_message:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            case R.id.btn_subscription:
                EventBus.getDefault().register(MainActivity.this);
                break;
        }
    }
}
