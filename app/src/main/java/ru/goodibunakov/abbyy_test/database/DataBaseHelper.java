package ru.goodibunakov.abbyy_test.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ru.goodibunakov.abbyy_test.model.DatabaseModel;

import static ru.goodibunakov.abbyy_test.database.DbSchema.DbTable.TABLE_NAME;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "cards.db";
    private static final int DB_VERSION = 1;
    private final Context context;

    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public boolean tableIsExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db == null || !db.isOpen()) {
            return false;
        }
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type = ? AND name = ?", new String[]{"table", TABLE_NAME});
        if (!cursor.moveToFirst()) {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count > 0;
    }

    public List<DatabaseModel> getCardsFromDb() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<DatabaseModel> items = new ArrayList<>();

        String[] sqRequest = {
                DbSchema.DbTable.TEXT,
                DbSchema.DbTable.INTERNATIONAL,
                DbSchema.DbTable.INTERNATIONAL_VALUES,
                DbSchema.DbTable.TRANSLATIONS,
                DbSchema.DbTable.P2_0,
                DbSchema.DbTable.P2_1,
                DbSchema.DbTable.P2_2,
                DbSchema.DbTable.P2_3
        };

        Cursor cursor = db.query(
                TABLE_NAME,
                sqRequest,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            DatabaseModel item = new DatabaseModel(
                    cursor.getString(cursor.getColumnIndex(DbSchema.DbTable.TEXT)),
                    cursor.getString(cursor.getColumnIndex(DbSchema.DbTable.INTERNATIONAL)),
                    cursor.getString(cursor.getColumnIndex(DbSchema.DbTable.INTERNATIONAL_VALUES)),
                    cursor.getString(cursor.getColumnIndex(DbSchema.DbTable.TRANSLATIONS)),
                    cursor.getString(cursor.getColumnIndex(DbSchema.DbTable.P2_0)),
                    cursor.getString(cursor.getColumnIndex(DbSchema.DbTable.P2_1)),
                    cursor.getString(cursor.getColumnIndex(DbSchema.DbTable.P2_2)),
                    cursor.getString(cursor.getColumnIndex(DbSchema.DbTable.P2_3))
            );
            items.add(item);
        }
        cursor.close();
        db.close();
        return items;
    }

    public void createTableCards() {
        SQLiteDatabase db = this.getWritableDatabase();
        String SQL_CREATE_TABLE = "CREATE TABLE " + DbSchema.DbTable.TABLE_NAME + " ("
                + DbSchema.DbTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbSchema.DbTable.TEXT + " TEXT, "
                + DbSchema.DbTable.INTERNATIONAL + " TEXT, "
                + DbSchema.DbTable.INTERNATIONAL_VALUES + " TEXT, "
                + DbSchema.DbTable.TRANSLATIONS + " TEXT, "
                + DbSchema.DbTable.P2_0 + " TEXT, "
                + DbSchema.DbTable.P2_1 + " TEXT, "
                + DbSchema.DbTable.P2_2 + " TEXT, "
                + DbSchema.DbTable.P2_3 + " TEXT )";
        db.execSQL(SQL_CREATE_TABLE);
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
