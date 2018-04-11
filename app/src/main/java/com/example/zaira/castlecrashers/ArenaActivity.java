package com.example.zaira.castlecrashers;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ArenaActivity extends Activity {

    //Se declaran los elementos visuales
    private ImageView imagenJugador;
    private ImageView imagenOponente;
    private TextView nombreJugador;
    private TextView nombreOponente;
    private ProgressBar vidaJugador;
    private ProgressBar vidaOponente;
    private Button btn_pelea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arena);

        //Se asocia los elementos visuales on la lógica
        imagenJugador = findViewById(R.id.iv_imagen_jugador);
        imagenOponente = findViewById(R.id.iv_imagen_oponente);
        nombreJugador = findViewById(R.id.tv_nombre_jugador);
        nombreOponente= findViewById(R.id.tv_nombre_oponente);
        vidaJugador = findViewById(R.id.pb_vida_jugador);
        vidaOponente = findViewById(R.id.pb_vida_oponente);
        btn_pelea = findViewById(R.id.btn_iniciaBatalla);

        //Se recibe el id del oponente a enfrentar



        btn_pelea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ArenaActivity.this, "Inicia la pelea", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
