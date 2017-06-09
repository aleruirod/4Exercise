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


public class Entrenamiento1 extends Activity implements SensorEventListener {

    private TextView numReps;
    private Sensor sensorEj1;
    private SensorManager sm1;
    private Integer sitUpAux = 0, numAbd = 0, done = 0;

    private ProgressBar mProgress;

    private SoundPool soundPool;
    private int repSound;
    private int finishSound;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrenamiento1);

        //creamos el sensor manager
        sm1 = (SensorManager) getSystemService(SENSOR_SERVICE);

        //seleccionamos el acelerometro
        sensorEj1 = sm1.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //Registrar Event listener
        sm1.registerListener(this,sensorEj1,SensorManager.SENSOR_DELAY_NORMAL);

        //asignar textview
        numReps = (TextView) findViewById(R.id.numreps);

        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Playball.ttf");
        numReps.setTypeface(typeFace);

        mProgress = (ProgressBar) findViewById(R.id.progressBarEj1);

        mProgress.setMax(10); //aqui debemos usar el número de repeticiones objetivo.

        mProgress.setProgress(0); // y aquí se especifica que la barra se inicialice a 0.

        mProgress.setScaleY(5f); //en esta líne especificamos el grosor de la barra de progreso.

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        repSound = soundPool.load(getApplicationContext(),R.raw.repetition,1);
        finishSound = soundPool.load(getApplicationContext(), R.raw.finish, 1);


    }


    public void onSensorChanged(SensorEvent event) {

        if (event.values[1] < 1.0 && sitUpAux == 0) {
            sitUpAux++; //En sitUpAux vamos contando cuando el sensor llega a los distintos valores
            // que alcanza en las dos fases al hacer un abdominal, arriba y abajo
        }

        if (event.values[1] > 6.5 && sitUpAux == 1) {
            sitUpAux++;
        }

        if (sitUpAux == 2) { //cuando sitUpAux ha contado las dos fases del abdominal, se registra
                            // la repetición y se realizan todas las tareas que ello conlleva.
            soundPool.play(repSound, 1.0f, 1.0f, 1, 0, 1.0f);
            numAbd++;
            mProgress.setProgress(numAbd);
            numReps.setText("Número de abdominales: "+numAbd);
            sitUpAux = 0;
        }

        if (numAbd == 10 && done == 0) {// aqui se puede especificar el número objetivo de repeticiones.
            numReps.setText("Ejercicio Terminado");
            done = 1; // La variable done es un flag para asegurar que el sonido  se reproduce 1 vez.

            // al llegar al objetivo la barra de progreso se colorea de verde.
            mProgress.getProgressDrawable().setColorFilter(
                    Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
            SystemClock.sleep(1100);// se retrasa la reproducción del sonido para que no se
                                    // el sonido anterior al contar la repetición.
            soundPool.play(finishSound, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //no lo usamos
    }

    protected void onStop() {
        super.onStop();
        sm1.unregisterListener(this);
    }

    protected void onResume() {
        super.onResume();
        sm1.registerListener(this,sensorEj1,SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void volverMain(View view) {
        finish();
    }
}
