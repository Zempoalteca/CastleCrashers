package com.example.zaira.castlecrashers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "castle_crashers";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLA_JUGADOR = "jugador";
    public static final String TABLA_ANIMAL = "animal";
    public static final String TABLA_RECORD = "record";
    private Cursor cursor;

    public DBHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    //Se obtiene
    public Animal getOponenteById(SQLiteDatabase db, int id){
        Animal oponente = null;

        db = this.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM animal WHERE _id=?",new String[]{String.valueOf(id)});

        if (cursor != null){
            cursor.moveToFirst();
            oponente = new Animal()
        }

        return oponente;
    }

        /*    //Obtener uan Nota dado un ID
    public Note getNoteById(int id){

        //Definimos un array con los nombres de las columnas que deseamos sacar
        String[] COLUMNS = {NotesDBDef.NOTES.ID_COL, NotesDBDef.NOTES.TITLE_COL, NotesDBDef.NOTES.URL_COL, NotesDBDef.NOTES.DESCRIP_COL};


        // 2. Contruimos el query
        Cursor cursor =
                db.query(NotesDBDef.NOTES.TABLE_NAME,  //Nomre de la tabla
                        COLUMNS, // b. Nombre de las Columnas
                        " id = ?", // c. Columnas de la clausula WHERE
                        new String[] { String.valueOf(id) }, // d. valores de las columnas de la clausula WHERE
                        null, // e. Clausula Group by
                        null, // f. Clausula having
                        null, // g. Clausula order by
                        null); // h. Limte de regsitros

        // 3. Si hemos obtenido algun resultado entonces sacamos el primero de ellos ya que se supone
        //que ha de existir un solo registro para un id
        if (cursor != null) {
            cursor.moveToFirst();
            // 4. Contruimos el objeto Note
            aNote = new Note();
            aNote.setId(Integer.parseInt(cursor.getString(0)));
            aNote.setTitle(cursor.getString(1));
            aNote.setUrl(cursor.getString(2));
            aNote.setDescription(cursor.getString(3));

        }

        // 5. Devolvemos le objeto Note
        return aNote;
    }*/

}
