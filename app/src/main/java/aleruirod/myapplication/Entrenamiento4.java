package aleruirod.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Entrenamiento4 extends Activity implements SensorEventListener {

    private Sensor sensorEj4;
    private SensorManager sm4;
    private TextView numSquats;
    private Integer squatAux = 0, numSent = 0, done = 0;

    private ProgressBar mProgress;

    private SoundPool soundPool;
    private int repSound;
    private int finishSound;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrenamiento4);

        sm4 = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorEj4 = sm4.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sm4.registerListener(this,sensorEj4,SensorManager.SENSOR_DELAY_NORMAL);

        numSquats = (TextView) findViewById(R.id.numsquats);

        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Playball.ttf");
        numSquats.setTypeface(typeFace);

        mProgress = (ProgressBar) findViewById(R.id.progressBarEj4);

        mProgress.setMax(10);

        mProgress.setProgress(0);

        mProgress.setScaleY(5f);

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        repSound = soundPool.load(getApplicationContext(),R.raw.repetition,1);
        finishSound = soundPool.load(getApplicationContext(), R.raw.finish, 1);


    }

    public void onSensorChanged(SensorEvent event) {

        if (event.values[1] < 10.0 && squatAux == 0){
            squatAux++;
        }

        if (event.values[1] > 12.5 && squatAux == 1) {
            squatAux++;
        }

        if (squatAux == 2) {
            numSent++;
            mProgress.setProgress(numSent);
            numSquats.setText("Número de sentadillas: "+numSent);
            squatAux = 0;
            soundPool.play(repSound, 1.0f, 1.0f, 1, 0, 1.0f);
        }

        if (numSent == 10 && done == 0) {
            numSquats.setText("Ejercicio Terminado");
            done = 1;
            mProgress.getProgressDrawable().setColorFilter(
                    Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
            SystemClock.sleep(1100);
            soundPool.play(finishSound, 1.0f, 1.0f, 1, 0, 1.0f);
        }

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //no lo usamos
    }

    protected void onStop() {
        super.onStop();
        sm4.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        sm4.registerListener(this,sensorEj4,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void volverMain(View view) {
        finish();
    }
}
