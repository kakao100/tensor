package com.example.tensor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper {
    /*// データーベースのバージョン
    public static final int DATABASE_VERSION = 1;
    // データーベース名,変数たち
    public static final String DATABASE_NAME = "MonsterDB.db";
    private static final String TABLE_NAME = "monsterdb";
    private static final String _INDEX = "_index";
    private static final String SKILL_TERN = "skilltern";
    private static final String BEFORE_ATTRIBUTE = "battribute";
    private static final String AFTER_ATTRIBUTE = "aatribute";
    private static final String BLESS = "bless";*/
    private static final int MONSTER_DATA_NUM=10000;
    private static final int ELEMENT_NUM=10000;
    private static final String[][] monsterdata=new String[MONSTER_DATA_NUM][ELEMENT_NUM];

    DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        ContentValues value = new ContentValues();
        db.beginTransaction();
        try {
            db.execSQL("create table capitals (prefecture text primary key, capital text not null);");
            for(int i=0;i<MONSTER_DATA_NUM;i++) {
            value.put("number", monsterdata[i][0]);
            value.put("hname", monsterdata[i][1]);
            value.put("kname", monsterdata[i][2]);
            value.put("skilltern", monsterdata[i][3]);
            value.put("hp", monsterdata[i][4]);
            value.put("atk", monsterdata[i][5]);
            db.insert("MonsterDB",null,value);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

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
}
