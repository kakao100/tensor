package com.example.tensor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper {
    // データーベースのバージョン
    private final int DATABASE_VERSION = 1;
    DBOpenHelper(Context context) {
        super(context, null, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        ContentValues value = new ContentValues();
        db.beginTransaction();
        try {
            db.execSQL("create table MonsterDB (_id integer primary key, name text, skilltern integer);");
            URL url = new URL("https://www.petitmonte.com/java/java_url.html");
            private ArrayList<Mons_data> mons_data_list = http_data_reader(url);
            /*for () {
                value.put("number", monsterdata[i][0]);
                value.put("_id", String.valueOf(i));
                value.put("name", monsterdata[i][0]);
                value.put("kname", monsterdata[i][2]);
                value.put("skilltern", monsterdata[i][1]);
                value.put("hp", monsterdata[i][4]);
                value.put("atk", monsterdata[i][5]);
                value.put("heal", monsterdata[i][6]);
                db.insert("MonsterDB", null, value);z
            }*/
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    private ArrayList<Mons_data> http_data_reader(URL url)  {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStreamReader isr = null;

        try {

            // InputStream(バイトストリーム)のままでもHTMLは取得できるが文字化けする
            InputStream is = url.openStream();

            // InputStreamをUTF8のInputStreamReader(文字ストリーム)に変換する
            isr = new InputStreamReader(is,"UTF-8");

            // 一文字毎に読み込む
            while(true) {
                int i = isr.read();
                if (i == -1) {
                    break;
                }
                System.out.print((char)i);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                isr.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return null;
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