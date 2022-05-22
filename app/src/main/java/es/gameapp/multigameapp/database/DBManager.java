package es.gameapp.multigameapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Wraps the logic for a SQLite database
 */

public class DBManager extends SQLiteOpenHelper {


    // Database Information
    private static final String DB_NAME = "multiGameDB";

    // Database version
    private static final int DB_VERSION = 1;
    // Table Names
    public static final String TABLE_NAME_USER = "score";

    // Table columns for score
    private static final String userName = "user";
    private static final String gameName = "game";
    private static final String gamescore = "score";

    // Creating table queries
    private static final String CREATE_TABLE_USER = "create table " + TABLE_NAME_USER + "(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            userName + " TEXT NOT NULL, " +
            gameName + " TEXT NOT NULL, " +
            gamescore + " TEXT NOT NULL" +
            ");";


    public DBManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_USER);
        onCreate(sqLiteDatabase);
    }
//------------------------------ ifExist / ifEmpty ------------------------------//

    public boolean ifTableUserExists(){
        return isEmpty(TABLE_NAME_USER);
    }

    private boolean isEmpty(String tableName){
        boolean ret = true;
        Cursor cursor = null;
        try {
            SQLiteDatabase sQLiteDatabase = this.getReadableDatabase();
            cursor = sQLiteDatabase.rawQuery( "SELECT count(*) FROM (select 0 from " + tableName + " limit 1)", null );
            cursor.moveToFirst();
            int count = cursor.getInt( 0 );
            if (count > 0) {
                ret = false;
            }
        } catch (Exception e) {
            // Nothing to do here...
        }
        finally {
            try {
                assert cursor != null;
                cursor.close();
            } catch (Exception e) {
                // Nothing to do here...
            }
        }
        return ret;
    }

    public ArrayList<Score> selectScoreByGame (String game) {
        ArrayList<Score> ret = new ArrayList<Score>();
        String query = "SELECT * FROM " + TABLE_NAME_USER + " WHERE " + gameName + "= '" + game +"'";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);
        Score score = null;
        while (cursor.moveToNext()) {
            score = new Score();
            score.setName(cursor.getString(1));
            score.setGame(cursor.getString(2));
            score.setScore(cursor.getString(3));
            ret.add(score);
        }
        cursor.close();
        sQLiteDatabase.close();
        return ret;
    }

    public void insert (Score score) {
        ContentValues values = new ContentValues();
        values.put(userName, score.getName());
        values.put(gameName, score.getGame());
        values.put(gamescore, score.getScore());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_NAME_USER, null, values);
        sQLiteDatabase.close();
    }

}
