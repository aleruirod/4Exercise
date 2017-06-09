package aleruirod.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.ViewFlipper;


public class MainActivity extends Activity {

    private Animation fadeIn, fadeOut;
    private ViewFlipper presentacionFotos;
    private TextView selEjercicio;

    //las siguientes variables no tienen utilidad en esta versión de la aplicación pero pueden usarse en futuras
    //versiones para manejar datos de las distintos ejercicios realizados.
    //Integer numero_entr_1 = 0;
    //Integer numero_entr_2 = 0;
    //Integer numero_entr_3 = 0;
    //Integer numero_entr_4 = 0;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selEjercicio = (TextView) findViewById(R.id.exercise_selection);

        Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/HomemadeApple.ttf");
        selEjercicio.setTypeface(typeFace);

        presentacionFotos = (ViewFlipper) this.findViewById(R.id.bckgrndViewFlipper);

        fadeIn = AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(this,android.R.anim.fade_out);

        presentacionFotos.setInAnimation(fadeIn);
        presentacionFotos.setOutAnimation(fadeOut);

        presentacionFotos.setAutoStart(true);
        presentacionFotos.setFlipInterval(5000);

    }


    public void aEntrenamiento1(View view) {
        Intent myIntent;
        myIntent = new Intent(getApplicationContext(), Entrenamiento1.class);

        //myIntent.putExtra("num_entr_1", numero_entr_1); esta línea se encarga de asignar una
        // variable a este nuevo Intent para guardar los datos de este al volver a la MainActivity

        startActivityForResult(myIntent, 101);

    }

    public void aEntrenamiento2(View view) {
        Intent myIntent;
        myIntent = new Intent(getApplicationContext(), Entrenamiento2.class);

        //myIntent.putExtra("num_entr_2", numero_entr_2);

        startActivityForResult(myIntent, 102);

    }

    public void aEntrenamiento3(View view) {
        Intent myIntent;
        myIntent = new Intent(getApplicationContext(), Entrenamiento3.class);

        //myIntent.putExtra("num_entr_3", numero_entr_3);

        startActivityForResult(myIntent, 103);

    }

    public void aEntrenamiento4(View view) {
        Intent myIntent;
        myIntent = new Intent(getApplicationContext(), Entrenamiento4.class);

        //myIntent.putExtra("num_entr_4", numero_entr_4);

        startActivityForResult(myIntent, 104);

    }



}
