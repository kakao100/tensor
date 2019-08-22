package com.example.tensor;
//モンスターデータのクラス

public class Mons_data {
    //図鑑番号
    private int id;
    public String name;
    private int atk;
    private int hp;
    private int cur;

    public Mons_data(String id,String name,String atk,String hp,String cur){
        this.id= Integer.parseInt(id);
        this.name= name;
        this.atk=Integer.parseInt(atk);
        this.hp=Integer.parseInt(hp);
        this.cur=Integer.parseInt(cur);
    }




}
