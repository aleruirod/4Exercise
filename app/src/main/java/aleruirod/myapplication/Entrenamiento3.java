package aleruirod.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Entrenamiento3 extends Activity {

    private Integer numFlex = 0;
    private TextView numPushups;

    private ProgressBar mProgress;

    private SoundPool soundPool;
    private int repSound;
    private int finishSound;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrenamiento3);

        numPushups = (TextView) findViewById(R.id.numpushups);

        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Playball.ttf");
        numPushups.setTypeface(typeFace);

        mProgress = (ProgressBar) findViewById(R.id.progressBarEj3);

        mProgress.setMax(10);

        mProgress.setProgress(0);

        mProgress.setScaleY(5f);

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        repSound = soundPool.load(getApplicationContext(),R.raw.repetition,1);
        finishSound = soundPool.load(getApplicationContext(), R.raw.finish, 1);


    }

    public void flexion(View view) {
        soundPool.play(repSound, 1.0f, 1.0f, 1, 0, 1.0f);
        numFlex++;
        mProgress.setProgress(numFlex);
        numPushups.setText("Número de flexiones: "+numFlex);

        if (numFlex == 10) {
            numPushups.setText("Ejercicio Terminado");
            mProgress.getProgressDrawable().setColorFilter(
                    Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
            SystemClock.sleep(1100);
            soundPool.play(finishSound, 1.0f, 1.0f, 1, 0, 1.0f);
        }
    }

    public void volverMain(View view) {
        finish();
    }

}
