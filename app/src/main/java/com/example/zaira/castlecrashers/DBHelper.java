package com.example.zaira.castlecrashers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "castle_crashers";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLA_JUGADOR = "jugador";
    public static final String TABLA_ANIMAL = "animal";
    public static final String TABLA_RECORD = "record";
    public static final String NOMBRE = "nombre";
    public static final String IMAGEN = "imagen";
    public static final String NIVEL = "nivel";
    public static final String LATITUD = "latitud";
    public static final String LONGITUD = "longitud";
    public static final String GANO = "gano";
    public static final String ID_JUGADOR = "id_jugador";
    public static final String ID_ANIMAL = "id_animal";
    public static final String ID = "_id";
    private Cursor cursor;
    //private SQLiteDatabase db;

    public DBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String tabla_jugador = "CREATE TABLE "+TABLA_JUGADOR+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOMBRE+" TEXT,"+IMAGEN+" INTEGER,"+NIVEL+" INTEGER)";
        db.execSQL(tabla_jugador);
        String tabla_animal = "CREATE TABLE "+TABLA_ANIMAL+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NOMBRE+" TEXT, "+IMAGEN+" INTEGER, "+NIVEL+" INTEGER, "+LATITUD+" INTEGER, "+LONGITUD+" INTEGER)";
        db.execSQL(tabla_animal);
        String tabla_record = "CREATE TABLE "+TABLA_RECORD+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ID_JUGADOR+" INTEGER, " +
                ID_ANIMAL+" INTEGER, " +
                GANO+" INTEGER, " +
                "FOREIGN KEY ("+ID_JUGADOR+") REFERENCES "+TABLA_JUGADOR+" (_id), " +
                "FOREIGN KEY ("+ID_ANIMAL+") REFERENCES "+TABLA_ANIMAL+" (_id))";
        db.execSQL(tabla_record);
        //Se agregan los elementos a la Base de Datos
        ContentValues cv = new ContentValues();
        /********** Jugador **********/
        cv.put(NOMBRE,"Caballero Azul");
        cv.put(IMAGEN, R.drawable.caballero);
        cv.put(NIVEL, 1);
        db.insert(TABLA_JUGADOR,NOMBRE,cv);

        /********** Animales **********/
        cv.put(NOMBRE,"Cabra");
        cv.put(IMAGEN,R.drawable.c1);
        cv.put(NIVEL,3);
        cv.put(LATITUD,19.408173);
        cv.put(LONGITUD,-99.171261);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Mandril");
        cv.put(IMAGEN,R.drawable.c2);
        cv.put(NIVEL,6);
        cv.put(LATITUD,19.408312);
        cv.put(LONGITUD,-99.171610);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Gato");
        cv.put(IMAGEN,R.drawable.c3);
        cv.put(NIVEL,4);
        cv.put(LATITUD,19.408448);
        cv.put(LONGITUD,-99.171295);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Caballero Vikingo");
        cv.put(IMAGEN,R.drawable.c4);
        cv.put(NIVEL,8);
        cv.put(LATITUD,19.409733);
        cv.put(LONGITUD,-99.169385);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Murcielago");
        cv.put(IMAGEN,R.drawable.c5);
        cv.put(NIVEL,6);
        cv.put(LATITUD,19.410724);
        cv.put(LONGITUD,-99.169453);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Jirafa");
        cv.put(IMAGEN,R.drawable.c6);
        cv.put(NIVEL,2);
        cv.put(LATITUD,19.407445);
        cv.put(LONGITUD,-99.174392);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Elote");
        cv.put(IMAGEN,R.drawable.c7);
        cv.put(NIVEL,7);
        cv.put(LATITUD,19.407820);
        cv.put(LONGITUD,-99.169737);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Venado");
        cv.put(IMAGEN,R.drawable.c8);
        cv.put(NIVEL,5);
        cv.put(LATITUD,19.405907);
        cv.put(LONGITUD,-99.171660);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Gallina");
        cv.put(IMAGEN,R.drawable.c9);
        cv.put(NIVEL,2);
        cv.put(LATITUD,19.410987);
        cv.put(LONGITUD,-99.172329);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Perro");
        cv.put(IMAGEN,R.drawable.c10);
        cv.put(NIVEL,8);
        cv.put(LATITUD,19.412517);
        cv.put(LONGITUD,-99.169160);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Troll");
        cv.put(IMAGEN,R.drawable.c11);
        cv.put(NIVEL,9);
        cv.put(LATITUD,19.415920);
        cv.put(LONGITUD,-99.171530);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);

        cv.put(NOMBRE,"Vampiro");
        cv.put(IMAGEN,R.drawable.c12);
        cv.put(NIVEL,4);
        cv.put(LATITUD,19.420025);
        cv.put(LONGITUD,-99.182296);
        db.insert(TABLA_ANIMAL,NOMBRE,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Este método consulta la información de un Animal
     * @param id_animal El id del Animal a consultar
     * @return Un objeto de tipo Animal, null en caso de que no exista Animal con el id dado.
     */
    public Animal getAnimalById(int id_animal){
        Animal oponente = null;
        SQLiteDatabase db = this.getReadableDatabase();

        cursor = db.rawQuery("SELECT "+ID+","+NOMBRE+", "+IMAGEN+", "+NIVEL+", "+LATITUD+","+LONGITUD+"  FROM "+TABLA_ANIMAL+" WHERE _id=?",new String[]{String.valueOf(id_animal)});

        if (cursor != null){
            cursor.moveToFirst();
            oponente = new Animal(
                    Integer.parseInt(cursor.getString(0)),
                    String.valueOf(cursor.getString(1)),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3)),
                    Double.parseDouble(cursor.getString(4)),
                    Double.parseDouble(cursor.getString(5)));
        }
        cursor.close();
        return oponente;
    }

    /**
     * Este método consulta todos los Animales de la base de datos y regresa un listado de objetos Animal.
     * @return Un arreglo de objetos Animal, null en caso de que no haya ningún animal en la Base de Datos.
     */
    public ArrayList<Animal> getAllAnimales(){
        ArrayList<Animal> listaAnimales = null;
        Animal index;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+ID+","+NOMBRE+", "+IMAGEN+", "+NIVEL+", "+LATITUD+","+LONGITUD+" FROM "+TABLA_ANIMAL,null);

        if (cursor != null){
            cursor.moveToFirst();
            do {
                index = new Animal(
                        Integer.parseInt(cursor.getString(0)),
                        String.valueOf(cursor.getString(1)),
                        Integer.parseInt(cursor.getString(2)),
                        Integer.parseInt(cursor.getString(3)),
                        Double.parseDouble(cursor.getString(4)),
                        Double.parseDouble(cursor.getString(5))
                        );
                listaAnimales.add(index);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listaAnimales;
    }

    /**
     *Este método consulta en la base de datos las victorias o derrotas del jugador.
     * @param gano Un entero infdicador: '0' si se requieren las Victorias, '1' si se requieren las derrotas.
     * @return Arreglo de String, con los nombres de los Animales, null en caso de que no exista record.
     */
    public ArrayList<String> getRecord(int gano){
        ArrayList<String> record = null;
        if (gano==0 || gano==1){
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery("" +
                    "SELECT "+NOMBRE+" FROM "+TABLA_ANIMAL+" WHERE ? = ?"
                    ,new String[]{"record.id_animal","animal._id"});
            while (cursor.moveToNext()){
                record.add(String.valueOf(cursor.getString(1)));
            }
            cursor.close();
            db.close();
        }
        return record;
    }

    /**
     * Este método consulta la información del jugador.
     * @return Un objeto de tipo Jugador, con la información del Jugador, null en caso de que no encuentre ningun Jugador.
     */
    public Jugador getJugador(){
        Jugador jugagor = null;
        SQLiteDatabase db = this.getReadableDatabase();
        cursor = db.rawQuery("SELECT "+ID+","+NOMBRE+","+IMAGEN+","+NIVEL+" FROM "+TABLA_JUGADOR,null);    //Quitar el null?
        if (cursor != null){
            cursor.moveToFirst();
            return new Jugador(
                    Integer.parseInt(cursor.getString(0)),
                    String.valueOf(cursor.getString(1)),
                    Integer.parseInt(cursor.getString(2)),
                    Integer.parseInt(cursor.getString(3))
            );
        }
        cursor.close();
        db.close();
        return jugagor;
    }

    /**
     * Este método agrega la información del resultado de una pelea a la Base de Datos.
     * @param id_jugador El id del jugador actual
     * @param id_oponente El id del animal con el que se peleó
     * @param gano El resultado de la pelea, true si el Jugador GANO, false si el Jugador PERDIO
     */
    public void setResultadoPelea(int id_jugador, int id_oponente, boolean gano){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ID_JUGADOR,id_jugador);
        cv.put(ID_ANIMAL,id_oponente);
        if (gano){
            cv.put(GANO,1);
        }else{
            cv.put(GANO,0);
        }
        db.insert(TABLA_RECORD,ID_JUGADOR,cv);
        db.close();
    }

}
