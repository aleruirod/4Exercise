package aleruirod.myapplication;

import android.app.Activity;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import flepsik.github.com.progress_ring.ProgressRingView;


public class Entrenamiento2 extends Activity {

    private ProgressRingView progress;

    private TextView contador;

    private CountDownTimer reloj;

    private Button startButton;

    private SoundPool soundPool;
    private int finishSound;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrenamiento2);

        startButton = (Button) findViewById(R.id.start);

        progress = (ProgressRingView) findViewById(R.id.progressRing);
        progress.setProgress(1);

        contador = (TextView) findViewById(R.id.countdown);

        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/Playball.ttf");
        contador.setTypeface(typeFace);

        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        finishSound = soundPool.load(getApplicationContext(), R.raw.finish, 1);

        reloj =  new CountDownTimer(30000, 1000){
            public void onTick(long millisUntilFinished) {
                contador.setText(""+millisUntilFinished / 1000);
                int aux = (int) (millisUntilFinished/1000);
                float prog = (float) aux;
                progress.setProgress(prog/30);
            }

            public void onFinish() {
                soundPool.play(finishSound, 1.0f, 1.0f, 1, 0, 1.0f);
                contador.setText("¡FIN!");
                progress.setProgress(0);
            }
        };


    }

    public void comienzo(View view) {
        reloj.start();
        startButton.setVisibility(View.INVISIBLE);
    }

    protected void onPause() {
        super.onPause();
        reloj.cancel();
    }

    protected void onStop() {
        super.onStop();
        reloj.cancel();
    }

    public void volverMain(View view) {
        finish();
    }
}
