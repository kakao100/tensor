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
            URL url = new URL("https://www.");
            HashMap<Integer,Mons_data> mons_data_map = http_data_reader(url);
            for(Mons_data monster:mons_data_map) {
                value.put("_id",monster.getid());
                value.put("name",monster.getname());
                value.put("_id",monster.getid());
                value.put("_id",monster.getid());
                value.put("_id",monster.getid());
                value.put("_id",monster.getid());
                value.put("_id",monster.getid());
                value.put("_id",monster.getid());
                value.put("_id",monster.getid());
                value.put("_id",monster.getid());
                value.put("_id",monster.getid());
                value.put("_id",monster.getid());

            }
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

    private HashMap<Integer,Mons_data> http_data_reader(URL url) throws IOException {
        HashMap<Integer,Mons_data> mons_data_list = new HashMap<Integer, Mons_data>();
        try {
            String line;
            int i = 0;
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            // Get通信してStringに変換
            while ((line = br.readLine())!=null){
                Mons_data tmp = new Mons_data(line);
                mons_data_list.put(i++, tmp);
            }
        }
        catch (Exception e) {
                e.printStackTrace();
        }
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