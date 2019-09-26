package com.example.tensor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
        try{
            db.execSQL("create table MonsterDB (_id integer primary key, data text);");
            URL url = new URL("https://kou0.github.io/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            int responseCode = connection.getResponseCode();
            InputStream inputStream;
            if (200 <= responseCode && responseCode <= 299) {
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }
            String line;
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            inputStream));
            // Get通信してStringに変換
            int i=0;
            while ((line = in.readLine())!=null){
                //value.put("_id",i++);
                //value.put("data",line);
                Log.d("", "onCreate: "+line);
            }
            in.close();
            db.insert("MonsterDB", null, value);
            db.setTransactionSuccessful();
        }catch(IOException e) {
        System.err.println(e.getMessage());
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