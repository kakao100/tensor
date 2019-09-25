package com.example.tensor;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper {
    // データーベースのバージョン
    private final int DATABASE_VERSION = 1;
    DBOpenHelper(Context context) {
        super(context, null, null,1/*DATABASE_VERSION*/);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // テーブル作成
        ContentValues value = new ContentValues();
        db.beginTransaction();
        try {
            db.execSQL("create table MonsterDB (_id integer primary key, name text, skilltern integer);");
            URL url = new URL("https://www.petitmonte.com/java/java_url.html");
            ArrayList<Mons_data> mons_data_list = http_data_reader(url);
                //value.put("monsterlist", mons_data_list);
                db.insert("MonsterDB", null, value);
            db.setTransactionSuccessful();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    private ArrayList<Mons_data> http_data_reader(URL url) throws IOException {
        ArrayList<Mons_data> mons_data_list = new ArrayList<>();
        // InputStream(バイトストリーム)のままでもHTMLは取得できるが文字化けする
        InputStream is = url.openStream();
        // InputStreamをUTF8のInputStreamReader(文字ストリーム)に変換する
        InputStreamReader isr = new InputStreamReader(is,"UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String inputLine;
        // 一行毎に読み込む
        while ((inputLine = br.readLine()) != null) {
            Mons_data tmp = new Mons_data(inputLine);
            mons_data_list.add(tmp);
        }
        br.close();
        return mons_data_list;
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