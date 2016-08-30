package za.co.gundula.app.bioscope.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by kgundula on 16/07/28.
 */
public class BioScopeMovieProvider extends ContentProvider {

    private static final UriMatcher bUriMatcher = buildUriMatcher();
    private BioScopeDbHelper mOpenHelper;


    private static final int MOVIE = 100;
    private static final int MOVIE_ID = 101;

    private static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = BioScopeContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, BioScopeContract.PATH_MOVIES, MOVIE);
        matcher.addURI(authority, BioScopeContract.PATH_MOVIES + "/#", MOVIE_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new BioScopeDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor bioscopeCursor = null;

        switch (bUriMatcher.match(uri)) {
            case MOVIE: {
                bioscopeCursor = mOpenHelper.getReadableDatabase().query(
                        BioScopeContract.MoviesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case MOVIE_ID: {
                bioscopeCursor = mOpenHelper.getReadableDatabase().query(
                        BioScopeContract.MoviesEntry.TABLE_NAME,
                        projection,
                        BioScopeContract.MoviesEntry._ID + " = '" + ContentUris.parseId(uri) + "'",
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        assert bioscopeCursor != null;
        bioscopeCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return bioscopeCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = bUriMatcher.match(uri);
        switch (match) {
            case MOVIE:
                return BioScopeContract.MoviesEntry.CONTENT_TYPE;
            case MOVIE_ID:
                return BioScopeContract.MoviesEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = bUriMatcher.match(uri);
        Uri movieUri;

        switch (match) {
            case MOVIE: {
                long _id = db.insert(BioScopeContract.MoviesEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    movieUri = BioScopeContract.MoviesEntry.buildMovieUri(_id);
                } else
                    throw new SQLException("Failed to insert new row into :" + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown Uri" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return movieUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = bUriMatcher.match(uri);
        int deleted = 0;

        switch (match) {
            case MOVIE:
                deleted = db.delete(BioScopeContract.MoviesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri:" + uri);
        }

        if (selection == null || deleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return deleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = bUriMatcher.match(uri);
        int updated = 0;

        switch (match) {
            case MOVIE_ID: {
                updated = db.update(BioScopeContract.MoviesEntry.TABLE_NAME, contentValues, selection, selectionArgs);
                break;
            }
        }

        if (updated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return updated;
    }
}
