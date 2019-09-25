package com.example.tensor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DBOpenHelper extends SQLiteOpenHelper {
    // データーベースのバージョン
    private static final int DATABASE_VERSION = 1;
    DBOpenHelper(Context context) {
        super(context, null, null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        ContentValues value = new ContentValues();
        db.beginTransaction();
        try {
            db.execSQL("create table MonsterDB (_id integer primary key, data text);");
            URL url = new URL("https://www.");
            try {
                String line;
                int i = 0;
                BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                // Get通信してStringに変換
                while ((line = br.readLine())!=null){
                    value.put("_id",i++);
                    value.put("data",line);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            db.insert("MonsterDB", null, value);
            db.setTransactionSuccessful();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }  finally {
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