package com.example.tensor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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

        ((TextView)view.findViewById(R.id.zukanView)).setText(mons_data_list.get(i).getid());
        ((TextView)view.findViewById(R.id.nameView)).setText(mons_data_list.get(i).getname());
        ((TextView)view.findViewById(R.id.hpView)).setText(mons_data_list.get(i).gethp());
        ((TextView)view.findViewById(R.id.atkView)).setText(mons_data_list.get(i).getatk());
        ((TextView)view.findViewById(R.id.cureView)).setText(mons_data_list.get(i).getcur());

        return view;
    }
}
