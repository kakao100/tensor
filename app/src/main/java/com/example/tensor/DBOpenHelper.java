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
    private static final String[][] monster_data = new String[MONSTER_DATA_NUM][ELEMENT_NUM];

    DBOpenHelper(Context context) {
        super(context, null, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        monster_data[0][0] = "eiru";
        monster_data[0][1] = "10";
        monster_data[1][0] = "meru";
        monster_data[1][1] = "5";
        monster_data[2][0] = "kanan";
        monster_data[2][1] = "7";
        ContentValues value = new ContentValues();
        db.beginTransaction();
        try {
            db.execSQL("create table MonsterDB (_id integer primary key, name text, skill_tern integer);");
            for (int i = 0; i < MONSTER_DATA_NUM; i++) {
                value.put("_id", String.valueOf(i+1));
                value.put("name", monster_data[i][0]);
                value.put("skill_tern", monster_data[i][1]);
                //value.put("hp", monster_data[i][4]);
                //value.put("atk", monster_data[i][5]);
                //value.put("heal", monster_data[i][6]);
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
