package com.example.fitbit;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StepCount extends AppCompatActivity implements SensorEventListener {

    private TextView textViewStepCounter;
    private SensorManager sensorManager;

    private boolean isCounterSensorPresent=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_count);

        textViewStepCounter=findViewById(R.id.finalSteps);
       sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        isCounterSensorPresent=true;
        Sensor countSensor=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor!=null)
        {
            sensorManager.registerListener(this,countSensor,sensorManager.SENSOR_DELAY_UI);
        }
        else{
            Toast.makeText(this,"Sensor not found",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isCounterSensorPresent=false;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
            if (isCounterSensorPresent) {
                textViewStepCounter.setText(String.valueOf(event.values[0]));
            }
        }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
