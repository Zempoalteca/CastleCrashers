package com.example.zaira.castlecrashers;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ArenaActivity extends Activity {

    //Se declaran los elementos visuales
    private ImageView imagenJugador;
    private ImageView imagenOponente;
    private TextView nombreJugador;
    private TextView nombreOponente;
    private ProgressBar vidaJugador;
    private ProgressBar vidaOponente;
    private Button btn_pelea;
    private DBHelper db;
    private Jugador jugador;
    private Animal oponente;
    private Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        //Se recibe el id del oponente a enfrentar (enviado por el intento)
        int id_animal = getIntent().getIntExtra("id_animal",0);

        //Se asocia los elementos visuales on la lógica
        imagenJugador = findViewById(R.id.iv_imagen_jugador);
        imagenOponente = findViewById(R.id.iv_imagen_oponente);
        nombreJugador = findViewById(R.id.tv_nombre_jugador);
        nombreOponente= findViewById(R.id.tv_nombre_oponente);
        vidaJugador = findViewById(R.id.pb_vida_jugador);
        vidaOponente = findViewById(R.id.pb_vida_oponente);
        btn_pelea = findViewById(R.id.btn_iniciaBatalla);
        v = (Vibrator) getSystemService(VIBRATOR_SERVICE);


        //Se instancia la Base de Datos
        db = new DBHelper(this);

        //Se obtiene la información del jugador
        jugador = db.getJugador();

        //Se obtiene la información del Animal a enfrentar
        oponente = db.getAnimalById(id_animal);

        //Se añade la información del Jugador a la Arena
        imagenJugador.setImageResource(jugador.getImagen());
        nombreJugador.setText(jugador.getNombre());
        vidaJugador.setMax(100);
        vidaJugador.setProgress(100);

        //Se añade la información del Animal oponente a la Arena
        imagenOponente.setImageResource(oponente.getImagen());
        nombreOponente.setText(oponente.getNombre());
        vidaOponente.setMax(100);
        vidaOponente.setProgress(100);

        btn_pelea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ArenaActivity.this, "Inicia la pelea!", Toast.LENGTH_SHORT).show();
                Batalla batalla = new Batalla();
                btn_pelea.setEnabled(false);
                batalla.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,null);
            }
        });
    }

    public class Batalla extends AsyncTask<Void, Integer, Boolean>{

        Random aleatorio;

        public Batalla(){
            aleatorio = new Random(System.currentTimeMillis());
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            int vida_jugador = vidaJugador.getProgress();
            int vida_oponente = vidaOponente.getProgress();
            do {
                try {
                    //Ataca primero el JUGADOR
                    Thread.sleep(1000);
                    vida_oponente -= (aleatorio.nextInt(10)+1);
                    v.vibrate(50);
                    publishProgress(new Integer[]{vida_jugador,vida_oponente});
                    //Ataca en segundo el OPONENETE
                    Thread.sleep(1000);
                    vida_jugador -= (aleatorio.nextInt(10)+1);
                    v.vibrate(75);
                    publishProgress(new Integer[]{vida_jugador,vida_oponente});
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (vidaJugador.getProgress() > 0 && vidaOponente.getProgress() > 0);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            vidaJugador.setProgress(values[0]);
            vidaOponente.setProgress(values[1]);
            if (vidaJugador.getProgress() > 25 && vidaJugador.getProgress()<70)
                vidaJugador.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
            if (vidaOponente.getProgress()>25 && vidaOponente.getProgress()<70)
                vidaOponente.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
            if (vidaJugador.getProgress()<=25)
                vidaJugador.setProgressTintList(ColorStateList.valueOf(Color.RED));
            if (vidaOponente.getProgress() <= 25)
                vidaOponente.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            //Guardar el resultado de la pelea en la base de datos
            String vencedor;
            if (vidaJugador.getProgress() > 0){
                vencedor = jugador.getNombre();
                db.setResultadoPelea(jugador.getId_jugador(),oponente.getId_animal(),true);
            }else{
                vencedor = oponente.getNombre();
                db.setResultadoPelea(jugador.getId_jugador(),oponente.getId_animal(),false);
            }
            Toast.makeText(ArenaActivity.this, "El vencedor es: "+vencedor+"!!!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
