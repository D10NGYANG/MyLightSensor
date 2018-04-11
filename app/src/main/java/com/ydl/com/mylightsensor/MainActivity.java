package com.ydl.com.mylightsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SensorManager sensorManager;
    //初始化指针盘
    private DashboardView mDashboardView;

    public static TextView txttip;
    public static String POSI = "0.75m水平面";
    public static int NUM = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //声明手机传感器管理器
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //选择光线传感器
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mDashboardView = (DashboardView) findViewById(R.id.dashboard_view);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);

        txttip = (TextView)findViewById(R.id.txtTips);
        txttip.setText("请将手机放置于 "+POSI+"\r\n当前照度标准值为 "+NUM+"lux");
    }

    //Activity被销毁
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销监听器
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }

    //感应器事件监听器
    private SensorEventListener listener = new SensorEventListener() {

        //当感应器精度发生变化
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        //当传感器监测到的数值发生变化时
        @Override
        public void onSensorChanged(SensorEvent event) {
            // values数组中第一个值就是当前的光照强度
            float value = event.values[0];
            mDashboardView.setCreditValue((int) value);
        }
    };
}
