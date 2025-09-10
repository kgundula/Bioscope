package za.co.gundula.app.bioscope.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Manages the application's database.
 *
 * This class creates and upgrades the database tables.
 */
public class BioScopeDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "bioscope.db";

    /**
     * Constructs a new BioScopeDbHelper.
     *
     * @param context The context to use.
     */
    public BioScopeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     *
     * @param sqLiteDatabase The database.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + BioScopeContract.MoviesEntry.TABLE_NAME + " ( " +
                BioScopeContract.MoviesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                BioScopeContract.MoviesEntry.COLUMN_NAME_ID + " INTEGER UNIQUE NOT NULL, " +
                BioScopeContract.MoviesEntry.COLUMN_NAME_TITLE + " TEXT NOT NULL, " +
                BioScopeContract.MoviesEntry.COLUMN_NAME_OVERVIEW + " TEXT NOT NULL, " +
                BioScopeContract.MoviesEntry.COLUMN_NAME_POSTER_PATH + " TEXT NOT NULL, " +
                BioScopeContract.MoviesEntry.COLUMN_NAME_RELEASE_DATE + " TEXT NOT NULL, " +
                BioScopeContract.MoviesEntry.COLUMN_NAME_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                BioScopeContract.MoviesEntry.COLUMN_NAME_VOTE_AVERAGE + " TEXT NOT NULL, " +
                BioScopeContract.MoviesEntry.COLUMN_NAME_POPULARITY + " TEXT NOT NULL, " +
                BioScopeContract.MoviesEntry.COLUMN_NAME_ORIGINAL_LANGUAGE + " TEXT NOT NULL, " +
                " UNIQUE (  " + BioScopeContract.MoviesEntry.COLUMN_NAME_ID + " ) ON CONFLICT IGNORE" +
                " ); ";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);

    }

    /**
     * Called when the database needs to be upgraded.
     *
     * @param sqLiteDatabase The database.
     * @param i              The old database version.
     * @param i1             The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        if (i1 > i) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BioScopeContract.MoviesEntry.TABLE_NAME);
            onCreate(sqLiteDatabase);
        }

    }
}
