package com.frank.myclock.device;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MySensor implements SensorEventListener {
    private Sensor sensor;
    private SensorManager sensorManager;
    private OnLightChanged lightChanged;
    private boolean isLow = false;

    public MySensor(SensorManager sensorManager) {
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        sensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //当感应器精度发生变化
    @Override public void onSensorChanged(SensorEvent event) {
        // values数组中第一个值就是当前的光照强度
        float value = event.values[0];
//        Log.i("MySensor","当前亮度 " + value + " lx(勒克斯)");
        if (value < 3 ){
            if (!isLow){
                isLow = true;
                lightChanged.onLow();
            }
        }else if (value > 3 ){
            if (isLow){
                isLow = false;
                lightChanged.onHeight();
            }
        }
    }

    public void setOnLightChanged(OnLightChanged lightChanged){
        this.lightChanged = lightChanged;
    }
    public interface OnLightChanged{
        public void onHeight();
        public void onLow();
    }
    //当传感器监测到的数值发生变化时
    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
