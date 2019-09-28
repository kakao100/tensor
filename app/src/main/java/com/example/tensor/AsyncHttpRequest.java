package com.example.tensor;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class AsyncHttpRequest extends AsyncTask<Uri.Builder, Void, String> {

    private Activity mainActivity;
    private ArrayList<Mons_data> mons_list = new ArrayList<>();

    public AsyncHttpRequest(Activity activity) {
        // 呼び出し元のアクティビティ
        this.mainActivity = activity;
    }
    // このメソッドは必ずオーバーライドする必要があるよ
    // ここが非同期で処理される部分みたいたぶん。
    @Override
    protected String doInBackground(Uri.Builder... builder) {
        DBOpenHelper helper;
        helper = new DBOpenHelper(mainActivity);
        final SQLiteDatabase db = helper.getReadableDatabase();
        final String[] columns=new String[]{"_id","data"};
        Cursor c = db.query("MonsterDB",columns, null, null, null, null, null);
        c.moveToFirst();
        for(int i=0;i<c.getCount();i++){
            String line = c.getString(1);
            Mons_data tmp = new Mons_data(line);
            mons_list.add(tmp);
            c.moveToNext();
        }
        c.close();
        return "";
    }


    // このメソッドは非同期処理の終わった後に呼び出されます
    @Override
    protected void onPostExecute(String result) {
    }
    public ArrayList<Mons_data> get_Mons_data(){
        return mons_list;
    }
}
