package com.example.tensor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    // データーベースのバージョン
    public static final int DATABASE_VERSION = 1;

    private static final int MONSTER_DATA_NUM = 3;
    private static final int ELEMENT_NUM = 2;
    private static final String[][] monsterdata = new String[MONSTER_DATA_NUM][ELEMENT_NUM];

    DBOpenHelper(Context context) {
        super(context, null, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        monsterdata[0][0] = "eiru";
        monsterdata[0][1] = "10";
        monsterdata[1][0] = "meru";
        monsterdata[1][1] = "5";
        monsterdata[2][0] = "kanan";
        monsterdata[2][1] = "7";
        ContentValues value = new ContentValues();
        db.beginTransaction();
        try {
            db.execSQL("create table MonsterDB (_id integer primary key, name text, skilltern integer);");
            for (int i = 0; i < MONSTER_DATA_NUM; i++) {
                //value.put("number", monsterdata[i][0]);
                value.put("_id", String.valueOf(i));
                value.put("name", monsterdata[i][0]);
                //value.put("kname", monsterdata[i][2]);
                value.put("skilltern", monsterdata[i][1]);
                //value.put("hp", monsterdata[i][4]);
                //value.put("atk", monsterdata[i][5]);
                //value.put("heal", monsterdata[i][6]);
                db.insert("MonsterDB", null, value);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        // アップデートの判別、古いバージョンは削除して新規作成
        /*db.execSQL(
                SQL_DELETE_ENTRIES
        );
        onCreate(db);*/
    }
}
