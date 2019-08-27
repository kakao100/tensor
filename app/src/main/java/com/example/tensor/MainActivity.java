package com.example.tensor;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Mons_data> mons_list = new ArrayList<>();
    EditText mini;
    EditText max;
    Switch inh_swich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_menu);
        //全てのデータを代入するのはここの方が良さそう。
        data_reader();
        //最初の画面を表示するメソッド呼び出し。
        SetFirstScreen();
    }


    private void SetFirstScreen() {

        setContentView(R.layout.search_menu);
        mini = (EditText) findViewById(R.id.mini_tern);
        max = (EditText) findViewById(R.id.max_tern);
        inh_swich = (Switch) findViewById(R.id.inh_Swich);
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
        setContentView(R.layout.results);
        int mini_tern,max_tern;
        if(!mini.getText().toString().equals("")) {
            mini_tern = Integer.parseInt(mini.getText().toString());
        }
        else {
            mini_tern = 0;
        }
        if(!max.getText().toString().equals("")) {
            max_tern = Integer.parseInt(max.getText().toString());
        }
        else {
            max_tern=100;
        }
        // ListViewにArrayAdapterを設定する
        ListView listView = (ListView)findViewById(R.id.listView);
        //ここでデータを挿入する。
        ArrayList<Mons_data> selected_data = data_selector(mini_tern,max_tern);
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
                    Mons_data tem=new Mons_data(line);
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
    public ArrayList<Mons_data> data_selector(int mini_tern,int max_tern/*boolean inheritance,,int skill_feature,int leader_skill_id*/){
        ArrayList<Mons_data> mons_filtered = new ArrayList<>();
        for(Mons_data monster : mons_list){
            //Log.d("", monster.getname()+monster.getid()+"\n");
            if(monster.getshortest_tern()>=mini_tern){
                if(monster.getshortest_tern()<=max_tern) {
                    //if(monster.getinheritance()==inheritance) {
                        //if(monster.getskill_feature()==skill_feature) {
                        //    if(monster.getleader_skill_id()==leader_skill_id) {
                                mons_filtered.add(monster);
                        //    }
                        //}
                    //}
                }
            }
        }
        return mons_filtered;
    }


}
