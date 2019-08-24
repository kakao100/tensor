package com.example.tensor;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    //グローバル宣言　データの数に関しては後からcsvから読み込むのでここでは宣言しない

    ArrayList<Mons_data> mons_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //全てのデータを代入するのはここの方が良さそう。
        data_reader();
        //最初の画面を表示するメソッド呼び出し。
        SetFirstScreen();
    }


    private void SetFirstScreen() {

        setContentView(R.layout.activity_main);
        //検索ボタン
        Button search_button = findViewById(R.id.search);
        //イベント追加
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //入力されたdataを渡す処理が必要？
                //↑　k(クラスを作り入力にしたい)
                //結果表示画面へ
                SetResultScreen();
            }
        });
    }
    private void SetResultScreen() {
        //入力されたデータを元に表示する処理が必要？
        setContentView(R.layout.results);
        //list View　の設定
        // ListViewにArrayAdapterを設定する
        ListView listView = (ListView)findViewById(R.id.listView);
        //ここでデータを挿入する。
        ArrayList<Mons_data> selected_data = data_selecter();
        MyAdapter adapter = new MyAdapter(MainActivity.this);

        adapter.setList(selected_data);
        listView.setAdapter(adapter);



        //初期画面へ戻るボタン
        Button back_to_main_button = findViewById(R.id.back_to_main);
        back_to_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //最初の画面へ
                SetFirstScreen();
            }
        });
    }
    public void data_reader() {
        InputStream is = null;
        BufferedReader br = null;
        try {
            try {
                // assetsフォルダ内の data_mons.csv をオープンする
                is = this.getAssets().open("data_mons_test.csv");
                br = new BufferedReader(new InputStreamReader(is));

                // １行ずつ読み込み
                String line;

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    Mons_data tem=new Mons_data(data[0],data[1],data[2],data[3],data[4]);
//                    Mons_data tem = new Mons_data();
//                    tem.setid(Integer.parseInt(data[0]));
//                    tem.setname(data[1]);
//                    tem.sethp(Integer.parseInt(data[2]));
//                    tem.setatk(Integer.parseInt(data[3]));
//                    tem.setcur(Integer.parseInt(data[4]));

                    mons_list.add(tem);
                }
            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e){
            // エラー発生時の処理
            e.printStackTrace();
        }
    }
    public ArrayList<Mons_data> data_selecter(){
        return mons_list;
    }


}
