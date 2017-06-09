package aleruirod.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.TextView;

public class LoadingScreen extends Activity {

    private CountDownTimer carga;
    private TextView bienvenida;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);

        bienvenida = (TextView) findViewById(R.id.cargando);

        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/HomemadeApple.ttf");
        bienvenida.setTypeface(typeFace);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 3000);

    }
}
