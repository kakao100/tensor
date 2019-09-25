package com.example.tensor;

import android.app.Activity;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Switch;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends Activity implements SearchView.OnQueryTextListener {

    //定数
    final int result = 1;
    final int search = 2;
    final int sort = 3;

    //変数宣言
    ArrayList<Mons_data> mons_list = new ArrayList<>();
    ArrayList<Mons_data> selected_data = new ArrayList<>();
    EditText mini,max;
    MyAdapter adapter;
    RadioGroup radiogroup;
    int display_name=0;
    int checkedId = -1;
    String search_text_checker ="";
    String sort_type="";
    boolean reverse_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全データをmons_listという変数に代入する。
        data_reader();
        //最初の画面を表示するメソッド呼び出し。
        SetResultScreen();
    }

    private void SetResultScreen() {
        setContentView(R.layout.results);
        display_name = result;
        //入力データを受け取る。
        int mini_tern=1,max_tern=100;
        if(mini!=null&&!mini.getText().toString().equals("")) {
            mini_tern = Integer.parseInt(mini.getText().toString());
        }
        if(max!=null&&!max.getText().toString().equals("")) {
            max_tern = Integer.parseInt(max.getText().toString());
        }
        //ここでselected_dataにデータを挿入する。
        data_selector(mini_tern,max_tern);
        //selected_dataの中身をラジオボタンに合わせてソートする。
        selected_data = mons_data_sorter(sort_type,selected_data);
        // ListViewにArrayAdapterを設定する
        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new MyAdapter(MainActivity.this);
        adapter.setList(selected_data);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);

        //文字検索の部分の設定諸々
        SearchView search_text = (SearchView) findViewById(R.id.search_text);
        // SearchViewの初期表示状態を設定
        search_text.setIconifiedByDefault(false);
        // SearchViewにOnQueryChangeListenerを設定
        search_text.setOnQueryTextListener(this);
        // SearchViewのSubmitボタンを使用不可にする
        search_text.setSubmitButtonEnabled(true);
        // SearchViewに何も入力していない時のテキストを設定
        search_text.setQueryHint("モンスターが検索できるよ！！？！");
        //モンスター検索の文字列を保持する。
        search_text.setQuery(search_text_checker,false);

        //検索画面へ行くボタン
        Button search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //検索画面へ
                SetSearchScreen();
            }
        });

        //ソート画面へ行くボタン
        Button sort_button = findViewById(R.id.sort_button);
        sort_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ソートの画面へ
                SetSortScreen();
            }
        });
    }

    private void SetSortScreen() {
        setContentView(R.layout.sort);
        display_name = sort;
        radiogroup = (RadioGroup) findViewById(R.id.sort_group);
        Button search_button = findViewById(R.id.search_button);
        if (checkedId != -1) {
            RadioButton radioButton = (RadioButton) findViewById(checkedId);
            radioButton.setChecked(true);
        }
        //検索ボタンが押されたのを検知
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //昇順降順ボタンが押されているかのチェック
                Switch reverse_monster_list = (Switch) findViewById(R.id.reverse_button);
                if(reverse_monster_list.isChecked()){
                    reverse_flag = true;
                }
                else{
                    reverse_flag = false;
                }
                //どのラジオボタンが押されているかのチェック
                checkedId = radiogroup.getCheckedRadioButtonId();
                if (checkedId != -1) {
                    RadioButton radioButton = (RadioButton) findViewById(checkedId);
                    sort_type = radioButton.getText().toString();
                }
                //結果の画面へ
                SetResultScreen();
            }
        });
    }

    private void SetSearchScreen() {
        setContentView(R.layout.search_menu);
        display_name = search;
        mini = (EditText) findViewById(R.id.mini_tern);
        max = (EditText) findViewById(R.id.max_tern);
        //検索画面へ移行するボタン
        Button search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //結果表示画面へ
                SetResultScreen();
            }
        });
    }

    //起動時にデータの読み込み(毎回するのは重すぎて本番だとやってられないかも)
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
    //最小ターン最大ターンのみでフィルタをかける。
    //これはこれから追加する
    public void data_selector(int mini_tern, int max_tern){
        selected_data = new ArrayList<>();
        for(Mons_data monster : mons_list){
            if(monster.getshortest_tern()>=mini_tern){
                if(monster.getshortest_tern()<=max_tern) {
                    if(monster.getawa()[0]!=null&&monster.getrare()>=4) {
                        selected_data.add(monster);
                    }
                }
            }
        }
    }

    //文字検索の提出ボタン
    //使わないから書かない。
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    //文字検索の部分に何か打ち込まれたら呼ばれる。
    @Override
    public boolean onQueryTextChange(String query) {
        search_text_checker = query;
        if(!query.equals("")){
            String revquery = revquery_method(query);
            final ArrayList<Mons_data> filtered_mons_data = new ArrayList<>();
            for (Mons_data monster: selected_data) {
            if (monster.getname().contains(query)||monster.getname().contains(revquery)) {
                    filtered_mons_data.add(monster);
                }
            }
            /*String revquery = revquery_method(query);
            String[] name_split = query.split("");
            String[] rev_name_split = revquery.split("");
            String name_matcher = "";
            String rev_name_matcher = "";
            for(String tmp: name_split){
                name_matcher += ".*"+tmp;
            }
            name_matcher += ".*";
            for(String tmp: rev_name_split){
                rev_name_matcher += ".*"+tmp;
            }
            rev_name_matcher += ".*";
            final ArrayList<Mons_data> filtered_mons_data = new ArrayList<>();
            for (Mons_data monster: selected_data) {
                String name = monster.getname();
                if(name.matches(name_matcher)||name.matches(rev_name_matcher)) {
                    filtered_mons_data.add(monster);
                }
            }*/
            //データをセット
            adapter.setList(filtered_mons_data);
            //反映
            adapter.notifyDataSetChanged();
        }
        else{
            //何も変化を加えていないデータをセット
            adapter.setList(selected_data);
            //反映
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    //ひらがなはカタカナに
    //カタカナはひらがなに
    private String revquery_method(String query) {
        if(!(query.matches("^[\\u3040-\\u309F]+$")||query.matches("^[\\u30A0-\\u30FF]+$"))){
            return query;
        }
        int dis=0;
        StringBuilder revquery = new StringBuilder();
        if(query.matches("^[\\u3040-\\u309F]+$")){
            dis = 'あ'-'ア';
        }
        else if(query.matches("^[\\u30A0-\\u30FF]+$")) {
            dis = 'ア' - 'あ';
        }
        for (int i = 0; i < query.length(); i++) {
            char code = query.charAt(i);
            revquery.append((char)(code - dis));
        }
        return revquery.toString();
    }

    //戻るボタンの処理
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(display_name==search||display_name==sort) {
                SetResultScreen();
                return false;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    //データの順番を変える。
    public ArrayList<Mons_data> mons_data_sorter(String sorter,ArrayList<Mons_data> selected_data){
        switch (sorter) {
            case ("図鑑No"):
                Collections.sort(
                        selected_data,
                        new Comparator<Mons_data>() {
                            @Override
                            public int compare(Mons_data a, Mons_data b) {
                                return a.getid() - b.getid();
                            }
                        });
                break;
            case ("属性"):
                Collections.sort(
                        selected_data,
                        new Comparator<Mons_data>() {
                            @Override
                            public int compare(Mons_data a, Mons_data b) {
                                return a.getid() - b.getid();
                            }
                        });
                break;
            case ("レア度"):
                Collections.sort(
                        selected_data,
                        new Comparator<Mons_data>() {
                            @Override
                            public int compare(Mons_data a, Mons_data b) {
                                if(reverse_flag) {
                                    return -(a.getrare() - b.getrare());
                                }
                                return a.getrare() - b.getrare();
                            }
                        });
                break;
            case ("スキルターン"):
                Collections.sort(
                        selected_data,
                        new Comparator<Mons_data>() {
                            @Override
                            public int compare(Mons_data a, Mons_data b) {
                                if(reverse_flag) {
                                    return -(a.getshortest_tern() - b.getshortest_tern());
                                }
                                return a.getshortest_tern() - b.getshortest_tern();
                            }
                        });
                break;
            case ("HP"):
                Collections.sort(
                        selected_data,
                        new Comparator<Mons_data>() {
                            @Override
                            public int compare(Mons_data a, Mons_data b) {
                                if(reverse_flag) {
                                    return -(a.gethp() - b.gethp());
                                }
                                return a.gethp() - b.gethp();
                            }
                        });
                break;
            case ("攻撃"):
                Collections.sort(
                        selected_data,
                        new Comparator<Mons_data>() {
                            @Override
                            public int compare(Mons_data a, Mons_data b) {
                                if(reverse_flag) {
                                    return -(a.getattack() - b.getattack());
                                }
                                return a.getattack() - b.getattack();
                            }
                        });
                break;
            case ("回復"):
                Collections.sort(
                        selected_data,
                        new Comparator<Mons_data>() {
                            @Override
                            public int compare(Mons_data a, Mons_data b) {
                                if(reverse_flag) {
                                    return -(a.getcure() - b.getcure());
                                }
                                return a.getcure() - b.getcure();
                            }
                        });
                break;
            default:
        }
        return selected_data;
    }
}
