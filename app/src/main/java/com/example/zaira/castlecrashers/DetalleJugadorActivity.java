package com.example.zaira.castlecrashers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetalleJugadorActivity extends AppCompatActivity {
    DBHelper db;
    private Jugador jugador;
    ArrayList<String> gano= new ArrayList<>();
    ArrayList<String> perdio = new ArrayList<>();
    TextView nivel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_jugador);
        db = new DBHelper(this);
        jugador = db.getJugador();
        nivel = findViewById(R.id.nivel);
        nivel.setText("Nivel: "+jugador.getNivel());
        gano= db.getRecord(1);
        perdio = db.getRecord(0);


    }
}
