package com.mohammedfahadkaleem.compass;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
  private ImageView compassImage;
  private float currentDegree = 0f;
  private SensorManager sensorManager;
  TextView tvDegree;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    compassImage = findViewById(R.id.compassView);
    tvDegree = findViewById(R.id.tvDegree);
    sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

  }

  @Override
  protected void onPause() {
    super.onPause();
    sensorManager.unregisterListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),sensorManager.SENSOR_DELAY_GAME);
  }

  @Override
  public void onSensorChanged(SensorEvent sensorEvent) {
    float degree = Math.round(sensorEvent.values[0]);
    tvDegree.setText(Float.toString(degree));

    RotateAnimation rotateAnimation = new RotateAnimation(
        currentDegree,
        -degree,
        Animation.RELATIVE_TO_SELF, 0.5f,
        Animation.RELATIVE_TO_SELF, 0.5f);
    rotateAnimation.setDuration(210);
    rotateAnimation.setFillAfter(true);
    compassImage.startAnimation(rotateAnimation);
    currentDegree = -degree;
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int i) {

  }
}
