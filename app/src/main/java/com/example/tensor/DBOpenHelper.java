package com.example.tensor;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    // データーベースのバージョン
    public static final int DATABASE_VERSION = 1;
    // データーベース名,変数たち
    public static final String DATABASE_NAME = "MonsterDB.db";
    private static final String TABLE_NAME = "monsterdb";
    private static final String _INDEX = "_index";
    private static final String SKILL_TERN = "skilltern";
    private static final String BEFORE_ATTRIBUTE = "battribute";
    private static final String AFTER_ATTRIBUTE = "aatribute";
    private static final String BLESS = "bless";


    DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        /*db.execSQL(
                SQL_CREATE_ENTRIES
        );*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // アップデートの判別、古いバージョンは削除して新規作成
        /*db.execSQL(
                SQL_DELETE_ENTRIES
        );*/
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db,
                            int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
