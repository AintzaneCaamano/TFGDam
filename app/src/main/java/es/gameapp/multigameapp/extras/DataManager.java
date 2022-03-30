package es.gameapp.multigameapp.extras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

import es.gameapp.multigameapp.modelo.Juego;
import es.gameapp.multigameapp.modelo.Palabra;
import es.gameapp.multigameapp.modelo.Pregunta;
import es.gameapp.multigameapp.modelo.Score;
import es.gameapp.multigameapp.modelo.Sopa;

/**
 * Wraps the logic for a SQLite database
 */
public class DataManager extends SQLiteOpenHelper {

    // Database Information
    private static final String DB_NAME = "multijuegos.db";

    // Database version
    private static final int DB_VERSION = 1;

    // TABLA PREGUNTAS
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

    // TABLA JUEGOS
    public static final String TABLE_NAME_JUEGOS = "Juegos";

    // Table columns
    private static final String ID_JUEGO = "id";
    private static final String NOMBRE_JUEGO = "nombre";

    // Creating table query
    private static final String CREATE_TABLE_JUEGOS = "create table " + TABLE_NAME_JUEGOS + "(" +
            ID_JUEGO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOMBRE_JUEGO + " TEXT " +
            ");";

    // TABLA SCORES
    public static final String TABLE_NAME_SCORES = "Scores";

    // Table columns
    private static final String ID_SCORE = "id";
    private static final String ID_JUEGO_FK = "id_juego";
    private static final String PLAYER = "player";
    private static final String SCORE = "score";

    //table query
    private static final String CREATE_TABLE_SCORES = "create table " + TABLE_NAME_SCORES + "(" +
            ID_SCORE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ID_JUEGO_FK + " TEXT, " +
            PLAYER + " TEXT, " +
            SCORE + " TEXT, " +
            " FOREIGN KEY ("+ID_JUEGO_FK+") REFERENCES "+TABLE_NAME_JUEGOS+"("+ID_JUEGO+"));";


    // TABLA SOPAS
    public static final String TABLE_NAME_SOPAS = "Scores";

    // Table columns
    private static final String ID_SOPA = "id";
    private static final String STRING_IMAGEN_SOPA = "imageBase64";

    // Creating table query
    private static final String CREATE_TABLE_SOPAS = "create table " + TABLE_NAME_SOPAS + "(" +
            ID_SOPA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STRING_IMAGEN_SOPA + " TEXT " +
            ");";

    // TABLA PALABRAS
    public static final String TABLE_NAME_PALABRAS_SOPA = "Palabras";

    // Table columns
    private static final String ID_PALABRA = "id";
    private static final String STRING_PALABRA = "palabra";
    private static final String ID_SOPA_FK = "id_sopa";

    private static final String CREATE_TABLE_PALABRAS = "create table " + TABLE_NAME_PALABRAS_SOPA + "(" +
            ID_PALABRA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ID_SOPA_FK + " TEXT, " +
            STRING_PALABRA + " TEXT, " +
            " FOREIGN KEY ("+ID_SOPA_FK+") REFERENCES "+TABLE_NAME_SOPAS+"("+ID_SOPA+"));";


    private final Context context;

    public DataManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_TABLE_JUEGOS);
        sQLiteDatabase.execSQL(CREATE_TABLE_SOPAS);
        sQLiteDatabase.execSQL(CREATE_TABLE_PREGUNTAS);
        sQLiteDatabase.execSQL(CREATE_TABLE_PALABRAS);
        sQLiteDatabase.execSQL(CREATE_TABLE_SCORES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_JUEGOS);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SOPAS);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PREGUNTAS_TRIVIA);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PALABRAS_SOPA);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SCORES);
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

    //------------------------------ SelectSopa by Id ------------------------------//

    public Sopa selectSopaById (int id) {
        String query = "Select * FROM " + TABLE_NAME_SOPAS + " WHERE " + ID_SOPA +
                " = " + "'" + id + "'";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        Sopa sopa;
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            sopa = new Sopa();
            sopa.setIdSopa(cursor.getInt(0));
            sopa.setStringSopaBase64(cursor.getString(1));
            cursor.close();
        } else {
            sopa = null;
        }
        sQLiteDatabase.close();
        return sopa;
    }
    //------------------------------ selectPalabrasByIdSopa ------------------------------//

    public ArrayList<Palabra> selectPalabrasByIdSopa (int idSopa) {
        ArrayList<Palabra> ret = new ArrayList<>();
        String query = "Select * FROM " + TABLE_NAME_PALABRAS_SOPA + " WHERE " + ID_SOPA_FK +
                "=" + "'" + idSopa + "'";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);
        Palabra palabra;
        while (cursor.moveToNext()) {
            palabra = new Palabra();
            palabra.setIdPalabra(cursor.getInt(0));
            palabra.setIdSopaFk(cursor.getInt(1));
            palabra.setStringPalabra(cursor.getString(2));

            ret.add(palabra);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }

    //------------------------------ selectAllScores ------------------------------//

    public ArrayList<Score> selectAllScores() {
        ArrayList<Score> ret = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_SCORES;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);
        Score score;
        while (cursor.moveToNext()) {
            score = new Score();
            score.setIdScore(cursor.getInt(0));
            score.setPlayer(cursor.getString(1));
            score.setScore(cursor.getString(2));
            ret.add(score);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }

    //------------------------------ selectBestScores ------------------------------//

    public ArrayList<Score> selectBestScoresByIdJuego(int idJuego) {
        ArrayList<Score> ret = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_SCORES + " WHERE " + ID_JUEGO_FK +
                "=" + "'" + idJuego + "'"+ " ORDER BY " + SCORE + " DESC ";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);
        Score score;
        while (cursor.moveToNext()) {
            score = new Score();
            score.setIdScore(cursor.getInt(0));
            score.setPlayer(cursor.getString(1));
            score.setScore(cursor.getString(2));
            ret.add(score);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }

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

    //------------------------------ InsertPalabra ------------------------------//

    public void insertPalabra(Palabra palabra) {
        ContentValues values = new ContentValues();
        values.put(ID_SOPA_FK, palabra.getIdSopaFk());
        values.put(STRING_PALABRA, palabra.getStringPalabra());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_NAME_PALABRAS_SOPA, null, values);
        sQLiteDatabase.close();
    }

    //------------------------------ InsertScore ------------------------------//

    public void insertScore(Score score) {
        ContentValues values = new ContentValues();
        values.put(ID_JUEGO_FK, score.getIdJuegoFk());
        values.put(PLAYER, score.getPlayer());
        values.put(SCORE, score.getScore());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_NAME_SCORES, null, values);
        sQLiteDatabase.close();
    }

    //------------------------------ InsertSopa ------------------------------//

    public void insertSopa(Sopa sopa) {
        ContentValues values = new ContentValues();
        values.put(STRING_IMAGEN_SOPA, sopa.getStringSopaBase64());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_NAME_SOPAS, null, values);
        sQLiteDatabase.close();
    }

    //------------------------------ InsertJuego ------------------------------//

    public void insertJuego(Juego juego) {
        ContentValues values = new ContentValues();
        values.put(NOMBRE_JUEGO, juego.getNombre());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_NAME_JUEGOS, null, values);
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

    //------------------------------ UpdateScore ------------------------------//

    public boolean updateScore(Score score) {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(PLAYER, score.getPlayer());
        args.put(SCORE, score.getScore());


        String whereClause = ID_SCORE + "=" + score.getIdScore();

        return sQLiteDatabase.update(TABLE_NAME_SCORES, args, whereClause, null) > 0;
    }

    //------------------------------ UpdateSopa ------------------------------//

    public boolean updateSopa(Sopa sopa) {
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(STRING_IMAGEN_SOPA, sopa.getStringSopaBase64());



        String whereClause = ID_SCORE + "=" + sopa.getIdSopa();

        return sQLiteDatabase.update(TABLE_NAME_SOPAS, args, whereClause, null) > 0;
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

    //------------------------------ DeletePalabrabyID ------------------------------//

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

    //------------------------------ DeleteScorebyID ------------------------------//

    public int deleteScoreById(int id) {
        int ret;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Score score = new Score();
        score.setIdScore(id);
        ret = sQLiteDatabase.delete(TABLE_NAME_SCORES, ID_SCORE + "=?",
                new String[]{
                        String.valueOf(score.getIdScore())
                });
        sQLiteDatabase.close();
        return ret;
    }

    //------------------------------ DeleteSopabyId ------------------------------//

    public int deleteSopaById(int id) {
        int ret;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Sopa sopa = new Sopa();
        sopa.setIdSopa(id);
        ret = sQLiteDatabase.delete(TABLE_NAME_SOPAS, ID_SOPA + "=?",
                new String[]{
                        String.valueOf(sopa.getIdSopa())
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


