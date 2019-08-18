package com.example.tensor;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {


    private static final int MONSTER_DATA_NUM = 12;
    private static final int ELEMENT_NUM = 2;
    String[][] monster_data = new String[MONSTER_DATA_NUM][ELEMENT_NUM];
    private Context context;

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
                EditText minitern = findViewById(R.id.minitern);
                EditText maxtern = findViewById(R.id.maxtern);
                //結果表示画面へ
                SetResultScreen(minitern,maxtern);
            }
        });
    }
    private void SetResultScreen(TextView minitern, TextView maxtern) {
        //入力されたデータを元に表示する処理が必要？
        setContentView(R.layout.results);
        //データを読み込む(まだできてません)
        data_reader();
        TextView result_mons = findViewById(R.id.result_mons);
        result_mons.setText("");
        for(int i=0;i<MONSTER_DATA_NUM;i++){
            result_mons.append("名前"+monster_data[i][0]+"  スキルターン"+monster_data[i][1]+"\n");
        }

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
        try {
            // txtファイルの読み込み
            FileInputStream fileInputStream = openFileInput("data.txt");
            BufferedReader bufferReader= new BufferedReader(new InputStreamReader(fileInputStream, StandardCharsets.UTF_8));
            String line;
            int i = 0;
            while ((line = bufferReader.readLine()) != null) {

                //スペース区切りで１つづつ配列に入れる
                String[] RowData = line.split(" ");

                //txtの左([0]番目)から順番にセット
                monster_data[i][0] = RowData[0];
                monster_data[i][1] = RowData[1];
                i++;
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
