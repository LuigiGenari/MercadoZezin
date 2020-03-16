package com.senac.br.mercadozezin.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mercado.senac.db";

    private static final Integer DB_VERSION = 1;

    private final String DB_CONTATO = "CREATE TABLE PRODUTO (" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "NOME TEXT," +
            "PRECO TEXT," +
            "QUANTIDADE INTEGER);";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CONTATO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {

        }
    }
}
