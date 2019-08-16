package com.example.tensor;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DBOpenHelper helper;
    private SQLiteOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //最初の画面を表示するメソッド呼び出し。
        SetFirstScreen();

    }

    private void SetFirstScreen() {

        setContentView(R.layout.activity_main);
        //条件ボタン
        Button termbutton = findViewById(R.id.term);
        //スキルターン
        TextView minitern = findViewById(R.id.minitern);
        TextView maxtern = findViewById(R.id.maxtern);
        //イベント追加
        termbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //条件決定の画面へ
                SetTermScreen();
            }
        });
        //検索ボタン
        Button searchbutton = findViewById(R.id.search);
        //イベント追加
        searchbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //入力されたdataを渡す処理が必要？
                TextView minitern = findViewById(R.id.minitern);
                TextView maxtern = findViewById(R.id.maxtern);
                //結果表示画面へ
                SetResultScreen(minitern,maxtern);
            }
        });
    }
    private void SetTermScreen() {
        setContentView(R.layout.terms);
        //初期画面へ戻るボタン
        Button backtomainbutton = findViewById(R.id.backtomain);
        backtomainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //最初の画面へ
                SetFirstScreen();
            }
        });
    }
    private void SetResultScreen(TextView minitern, TextView maxtern) {
        //入力されたデータを元に表示する処理が必要？
        setContentView(R.layout.results);
        helper = new DBOpenHelper(this);
        final SQLiteDatabase db = helper.getReadableDatabase();
        final String[] columns=new String[]{"_id","name","skilltern"};
        Cursor c = db.query("MonsterDB",columns, null, null, null, null, null);
        c.moveToFirst();
        CharSequence[] list = new CharSequence[c.getCount()];
        for (int i = 0; i < list.length; i++) {
            list[i] = c.getString(1);
            c.moveToNext();
        }
        c.close();
        TextView textView0 = findViewById(R.id.text0);
        TextView textView1 = findViewById(R.id.text1);
        TextView textView2 = findViewById(R.id.text2);
        textView0.setText(list[0]);
        textView1.setText(list[1]);
        textView2.setText(list[2]);
        //初期画面へ戻るボタン
        Button backtomainbutton = findViewById(R.id.backtomain);
        backtomainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //最初の画面へ
                SetFirstScreen();
            }
        });
    }

}
