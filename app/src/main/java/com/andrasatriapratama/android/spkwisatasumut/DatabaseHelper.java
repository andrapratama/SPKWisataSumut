package com.andrasatriapratama.android.spkwisatasumut;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andra Satria Pratama on 9/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "spk.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABEL_WISATA = "tbl_wisata";
    public static final String TABEL_KRITERIA = "tbl_krteria";

    public static final String SQL_TABEL_WISATA = "CREATE TABLE " + TABEL_WISATA + " ("
            + "id_wisata INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nama_wisata TEXT)";

    public static final String SQL_TABEL_KRITERIA = "CREATE TABLE " + TABEL_KRITERIA + " ("
            + "id_kriteria INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "id_wisata INTEGER, "
            + "c1 INTEGER, "
            + "c2 INTEGER, "
            + "c3 INTEGER, "
            + "c4 INTEGER, "
            + "c5 INTEGER, "
            + "c6 INTEGER)";

    public static final String SQL_DELETE_TABEL_WISATA = "DROP TABLE IF EXIST " + TABEL_WISATA;
    public static final String SQL_DELETE_TABEL_KRITERIA = "DROP TABLE IF EXIST " + TABEL_KRITERIA;
    public static final String SQL_SELECT_WISATA = "SELECT * FROM " + TABEL_WISATA;
    public static final String SQL_SELECT_KRITERIA = "SELECT * FROM " + TABEL_KRITERIA;
    public static final String SQL_SELECT_ONE_WISATA = "SELECT * FROM " + TABEL_WISATA + " WHERE ";
    public static final String SQL_SELECT_ONE_KRITERIA = "SELECT * FROM " + TABEL_KRITERIA + " WHERE ";

    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_TABEL_WISATA);
        db.execSQL(SQL_TABEL_KRITERIA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABEL_WISATA);
        db.execSQL(SQL_DELETE_TABEL_KRITERIA);
    }

    // mengambil semua data wisata
    public Cursor getDataWisata() {
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT_WISATA, null);
        return cursor;
    }

    // mengambil semua data kriteria
    public Cursor getDataKriteria() {
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT_KRITERIA, null);
        return cursor;
    }

    // mengambil satu baris data berdasarkan id wisata
    public Cursor getOneIdWisata(String id) {
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT_ONE_WISATA + " id_wisata = '" + id + "'", null);
        return cursor;
    }

    // mengambil satu baris data berdasarkan nama wisata
    public Cursor getOneNameWisata(String nama) {
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT_ONE_WISATA + " nama_wisata = '" + nama + "'", null);
        return cursor;
    }

    // mengambil satu baris data kriteria
    public Cursor getOneDataKriteria(String id) {
        Cursor cursor = sqLiteDatabase.rawQuery(SQL_SELECT_ONE_KRITERIA + " id_kriteria = '" + id + "'", null);
        return cursor;
    }

    // method untuk memasukkan data wisata
    public boolean insertDataWisata(String nama) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama_wisata", nama);
        long result = sqLiteDatabase.insert(TABEL_WISATA, null, contentValues);

        // mengecek apakah data telah ditambahakan
        if (result < 0) {
            return false;
        } else {
            return true;
        }
    }

    // method untuk memasukkan data kriteria
    public boolean insertDataKriteria(String idWisata, String c1, String c2, String c3, String c4, String c5, String c6) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_wisata", idWisata);
        contentValues.put("c1", c1);
        contentValues.put("c2", c2);
        contentValues.put("c3", c3);
        contentValues.put("c4", c4);
        contentValues.put("c5", c5);
        contentValues.put("c6", c6);
        long result = sqLiteDatabase.insert(TABEL_KRITERIA, null, contentValues);

        // mengecek apakah data telah ditambahakan
        if (result < 0) {
            return false;
        } else {
            return true;
        }
    }

    // method untuk mengubah data kriteria
    public boolean updateDataKriteria(String idWisata, String c1, String c2, String c3, String c4, String c5, String c6) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("c1", c1);
        contentValues.put("c2", c2);
        contentValues.put("c3", c3);
        contentValues.put("c4", c4);
        contentValues.put("c5", c5);
        contentValues.put("c6", c6);
        int result = db.update(TABEL_KRITERIA, contentValues, "id_wisata=?", new String[]{idWisata});

        // mengecek apakah data telah ditambahakan
        if (result < 0) {
            return false;
        } else {
            return true;
        }
    }


    // method untuk menghapus data wisata
    public Integer deleteDataWisata(String nama) {
        int i = sqLiteDatabase.delete(TABEL_WISATA, "nama_wisata=?", new String[]{nama});
        return i;
    }

    // method untuk menghapus data kriteria
    public Integer deleteDataKriteria(String id) {
        int i = sqLiteDatabase.delete(TABEL_KRITERIA, "id_wisata=?", new String[]{id});
        return i;
    }


}
