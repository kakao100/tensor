package com.example.tensor;

import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    //グローバル宣言　データの数に関しては後からcsvから読み込むのでここでは宣言しない
    public static int data_num_grobal;
    public static Mons_data[] mons_data_grobal =new Mons_data[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        //データを読み込む(ka完成)
        data_reader();

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
    public void data_reader() {
        InputStream is = null;
        BufferedReader br = null;
        String text = "";
        try {
            try {
                // assetsフォルダ内の data_mons.csv をオープンする
                is = this.getAssets().open("data_mons_test.csv");
                br = new BufferedReader(new InputStreamReader(is));
                //csvファイルの最初の一行(データの数)を読み取る
                //data_num_grobal=Integer.parseInt(br.readLine());

                //データの数だけテータクラスの配列を作る
                // １行ずつ読み込み
                String line;
                //データクラスの配列のインデックス
                int i = 0;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    mons_data_grobal[i]=new Mons_data(data[0],data[1],data[2],data[3],data[4]);
                    i++;
                }
            } finally {
                if (is != null) is.close();
                if (br != null) br.close();
            }
        } catch (Exception e){
            // エラー発生時の処理
            e.printStackTrace();
        }

        // 読み込んだ文字列を EditText に設定し、画面に表示する
        TextView textView = findViewById(R.id.textView);
        //今は　このmons_data_grobalの１には　コマさん
        String mons_data=mons_data_grobal[1].name;
        // テキストを設定して表示
        textView.setText(mons_data);
    }

}
