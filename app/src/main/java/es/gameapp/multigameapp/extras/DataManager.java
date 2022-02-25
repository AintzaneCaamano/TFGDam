package es.gameapp.multigameapp.extras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import es.gameapp.multigameapp.modelo.Palabra;
import es.gameapp.multigameapp.modelo.Pregunta;

/**
 * Wraps the logic for a SQLite database
 */
public class DataManager extends SQLiteOpenHelper {

    // Database Information
    private static final String DB_NAME = "multijuegos.db";

    // Database version
    private static final int DB_VERSION = 1;

    // Table Name
    public static final String TABLE_NAME_PREGUNTAS_TRIVIA = "Preguntas";

    // Table columns
    private static final String ID_PREGUNTA = "id";
    private static final String TEXTO = "texto";
    private static final String OPCION1 = "opcion1";
    private static final String OPCION2 = "opcion2";
    private static final String OPCION3 = "opcion3";
    private static final String OPCION4 = "opcion4";
    private static final String RESPUESTA = "respuesta";


    // Creating table query
    private static final String CREATE_TABLE_PREGUNTAS = "create table " + TABLE_NAME_PREGUNTAS_TRIVIA + "(" +
            ID_PREGUNTA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TEXTO + " TEXT, " +
            OPCION1 + " TEXT, " +
            OPCION2 + " TEXT, " +
            OPCION3 + " TEXT, " +
            OPCION4 + " TEXT, " +
            RESPUESTA + " TEXT " +
            ");";

    // Table Name
    public static final String TABLE_NAME_PALABRAS_SOPA = "Palabras";

    // Table columns
    private static final String ID_PALABRA = "id";
    private static final String STRING_PALABRA = "palabra";

    private static final String CREATE_TABLE_PALABRAS = "create table " + TABLE_NAME_PALABRAS_SOPA + "(" +
            ID_PALABRA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STRING_PALABRA + " TEXT " +
            ");";

    private final Context context;

    public DataManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_TABLE_PREGUNTAS);
        sQLiteDatabase.execSQL(CREATE_TABLE_PALABRAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PALABRAS_SOPA);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PREGUNTAS_TRIVIA);
        onCreate(sQLiteDatabase);
    }

    //------------------------------ selectAllPreguntas ------------------------------//

    public ArrayList<Pregunta> selectAllPreguntas() {
        ArrayList<Pregunta> ret = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_PREGUNTAS_TRIVIA;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);
        Pregunta pregunta;
        while (cursor.moveToNext()) {
            pregunta = new Pregunta();
            pregunta.setIdPregunta(cursor.getInt(0));
            pregunta.setTexto(cursor.getString(1));
            pregunta.setOpcion1(cursor.getString(2));
            pregunta.setOpcion2(cursor.getString(3));
            pregunta.setOpcion3(cursor.getString(4));
            pregunta.setOpcion4(cursor.getString(5));
            pregunta.setRespuesta(cursor.getString(6));
            ret.add(pregunta);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }

    //------------------------------ selectAllPalabras ------------------------------//

    public ArrayList<Palabra> selectAllPalabras() {
        ArrayList<Palabra> ret = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_PALABRAS_SOPA;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);
        Palabra palabra;
        while (cursor.moveToNext()) {
            palabra = new Palabra();
            palabra.setIdPalabra(cursor.getInt(0));
            palabra.setStringPalabra(cursor.getString(1));
            ret.add(palabra);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }

    //------------------------------ SelectUsuario by Id ------------------------------//

    /*public Usuario selectUsuarioById (int id) {
        String query = "Select * FROM " + TABLE_NAME_PREGUNTAS_TRIVIA + " WHERE " + ID_PREGUNTA +
                " = " + "'" + id + "'";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        Usuario usuario;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombreUsuario(cursor.getString(1));
            usuario.setPassword(cursor.getString(2));
            cursor.close();
        } else {
            usuario = null;
        }
        sQLiteDatabase.close();
        return usuario;
    }*/

    //------------------------------ InsertPregunta ------------------------------//

    public void insertPregunta(Pregunta pregunta) {
        ContentValues values = new ContentValues();
        values.put(TEXTO, pregunta.getTexto());
        values.put(OPCION1, pregunta.getOpcion1());
        values.put(OPCION2, pregunta.getOpcion2());
        values.put(OPCION3, pregunta.getOpcion3());
        values.put(OPCION4, pregunta.getOpcion4());
        values.put(RESPUESTA, pregunta.getRespuesta());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_NAME_PREGUNTAS_TRIVIA, null, values);
        sQLiteDatabase.close();
    }

    //------------------------------ InsertTarea ------------------------------//

    public void insertPalabra(Palabra palabra) {
        ContentValues values = new ContentValues();
        values.put(ID_PALABRA, palabra.getIdPalabra());
        values.put(STRING_PALABRA, palabra.getStringPalabra());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_NAME_PALABRAS_SOPA, null, values);
        sQLiteDatabase.close();
    }

    //------------------------------ UpdatePregunta ------------------------------//

    public boolean updatePregunta(Pregunta pregunta) {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(TEXTO, pregunta.getTexto());
        args.put(OPCION1, pregunta.getOpcion1());
        args.put(OPCION2, pregunta.getOpcion2());
        args.put(OPCION3, pregunta.getOpcion3());
        args.put(OPCION4, pregunta.getOpcion4());
        args.put(RESPUESTA, pregunta.getRespuesta());
        String whereClause = ID_PREGUNTA + "=" + pregunta.getIdPregunta();

        return sQLiteDatabase.update(TABLE_NAME_PREGUNTAS_TRIVIA, args, whereClause, null) > 0;
    }

    //------------------------------ UpdatePalabra ------------------------------//

    public boolean updatePalabra(Palabra palabra) {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(STRING_PALABRA, palabra.getStringPalabra());

        String whereClause = ID_PALABRA + "=" + palabra.getIdPalabra();

        return sQLiteDatabase.update(TABLE_NAME_PALABRAS_SOPA, args, whereClause, null) > 0;
    }

    //------------------------------ DeletePreguntabyId ------------------------------//

    public int deletePreguntaById(int id) {
        int ret;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Pregunta pregunta = new Pregunta();
        pregunta.setIdPregunta(id);
        ret = sQLiteDatabase.delete(TABLE_NAME_PREGUNTAS_TRIVIA, ID_PREGUNTA + "=?",
                new String[]{
                        String.valueOf(pregunta.getIdPregunta())
                });
        sQLiteDatabase.close();
        return ret;
    }

    //------------------------------ DeletePalabra ------------------------------//

    public int deletePalabraById(int id) {
        int ret;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Palabra palabra = new Palabra();
        palabra.setIdPalabra(id);
        ret = sQLiteDatabase.delete(TABLE_NAME_PALABRAS_SOPA, ID_PALABRA + "=?",
                new String[]{
                        String.valueOf(palabra.getIdPalabra())
                });
        sQLiteDatabase.close();
        return ret;
    }

    //------------------------------ ifExist / ifEmpty ------------------------------//

    public boolean ifTableExists() {
        boolean ret = false;
        Cursor cursor = null;
        try {
            SQLiteDatabase sQLiteDatabase = this.getReadableDatabase();
            String query = "select DISTINCT tbl_name from sqlite_master where tbl_name = '" +
                    TABLE_NAME_PREGUNTAS_TRIVIA + "'";
            cursor = sQLiteDatabase.rawQuery(query, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    ret = true;
                }
            }
        } catch (Exception e) {
            // Nothing to do here...
        } finally {
            try {
                assert cursor != null;
                cursor.close();
            } catch (NullPointerException e) {
                // Nothing to do here...
            }
        }
        return ret;
    }

    public boolean isEmpty() {
        boolean ret = true;
        Cursor cursor = null;
        try {
            SQLiteDatabase sQLiteDatabase = this.getReadableDatabase();
            cursor = sQLiteDatabase.rawQuery("SELECT count(*) FROM (select 0 from " +
                    TABLE_NAME_PREGUNTAS_TRIVIA + " limit 1)", null);
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            if (count > 0) {
                ret = false;
            }
        } catch (Exception e) {
            // Nothing to do here...
        } finally {
            try {
                assert cursor != null;
                cursor.close();
            } catch (Exception e) {
                // Nothing to do here...
            }
        }
        return ret;
    }
}

    /*String query = "Select * FROM " + TABLE_NAME_USUARIO + " WHERE " + NOMBRE +
            " like " + "'" + nombre + "'" + " and " + PASSWORD + " like " + "'" +
            apellido1 + "'" + " and " + APELLIDO2 + " like " + "'" + apellido2 + "'";
            }*/


