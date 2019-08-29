package com.example.tensor;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements TextWatcher {

    ArrayList<Mons_data> mons_list = new ArrayList<>();
    ArrayList<Mons_data> selected_data = new ArrayList<>();
    EditText mini,max;
    Switch inh_switch;

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
        inh_switch = (Switch) findViewById(R.id.inh_Swich);
        //検索ボタン
        Button search_button = findViewById(R.id.search);
        //イベント追加
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //結果表示画面へ
                SetResultScreen();
            }
        });
    }
    private void SetResultScreen() {
        setContentView(R.layout.results);
        EditText name_search = findViewById(R.id.name_search);
        name_search.addTextChangedListener(this);
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
        first_data_selector(mini_tern,max_tern);
        MyAdapter adapter = new MyAdapter(MainActivity.this);
        adapter.setList(selected_data);
        listView.setAdapter(adapter);

        //ソート画面へ行くボタン
        Button sort_button = findViewById(R.id.sort_button);
        sort_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ソートの画面へ
                SetSortScreen();
            }
        });

        //初期画面へ戻るボタン
        Button back_to_main_button = findViewById(R.id.back_to_main);
        back_to_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //初期画面へ
                SetFirstScreen();
            }
        });
    }

    private void SetSortScreen() {

        setContentView(R.layout.sort);
        //検索画面へ戻るボタン
        Button search_button = findViewById(R.id.back_to_main);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //結果表示画面へ
                SetResultScreen();
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

    public void first_data_selector(int mini_tern, int max_tern){
        selected_data = new ArrayList<>();
        for(Mons_data monster : mons_list){
            if(monster.getshortest_tern()>=mini_tern){
                if(monster.getshortest_tern()<=max_tern) {
                    selected_data.add(monster);
                }
            }
        }
    }

    private void name_searcher(String name_search_data) {
//ここでどうにかしてアダプターを更新する。
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //リアルタイムチェッカー
        String name_search_data = s.toString();
        if(!name_search_data.equals("")) {
            name_searcher(name_search_data);
        }
    }


}
