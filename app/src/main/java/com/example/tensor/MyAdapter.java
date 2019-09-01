package com.example.tensor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<Mons_data> mons_data_list;

    public MyAdapter(Context context){
        this.context=context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setList(ArrayList<Mons_data> mons_data_list){
        this.mons_data_list=mons_data_list;
    }

    @Override
    public int getCount() {
        return mons_data_list.size();
    }


    @Override
    public Object getItem(int i) {
        return mons_data_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mons_data_list.get(i).getid();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.list_items,viewGroup,false);
        Mons_data monster=mons_data_list.get(i);

        ((TextView)view.findViewById(R.id.nameView)).setText(monster.getname());
        ((TextView)view.findViewById(R.id.hpView)).setText("HP "+String.valueOf(monster.gethp()));
        ((TextView)view.findViewById(R.id.atkView)).setText(" 攻撃 "+String.valueOf(monster.getattack()));
        ((TextView)view.findViewById(R.id.cureView)).setText(" 回復 "+String.valueOf(monster.getcure()));
        if(monster.getshortest_tern()!=0) {
            ((TextView) view.findViewById(R.id.shortest_ternView)).setText(" スキルターン " + String.valueOf(monster.getshortest_tern()));
            ((TextView) view.findViewById(R.id.longest_ternView)).setText("(" + String.valueOf(monster.getlongest_tern()) + ")");
        }

        return view;
    }


}
