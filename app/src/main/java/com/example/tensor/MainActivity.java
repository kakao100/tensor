package com.example.tensor;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*private DBOpenHelper helper;
        private SQLiteDatabase db;
        helper = new DBOpenHelper(getApplicationContext());
        db = helper.getWritableDatabase();
        monsterdatainput();
        */
        //最初の画面を表示するメソッド呼び出し。
        SetFirstScreen();

    }

    private void monsterdatainput() {
        //モンスターのデータを入れる。
        //db.insert();
    }

    private void SetFirstScreen() {

        setContentView(R.layout.activity_main);
        //条件ボタン
        Button termbutton = findViewById(R.id.term);
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
                //結果表示画面へ
                SetResultScreen();
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
    private void SetResultScreen() {
        //入力されたデータを元に表示する処理が必要？
        setContentView(R.layout.results);
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
