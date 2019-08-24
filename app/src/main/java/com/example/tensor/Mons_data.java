package com.example.tensor;
//モンスターデータのクラス

public class Mons_data {
    //図鑑番号
    private int id;
    private String name;
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
    public int getid(){
        return id;
    }
    public void setid(int id){
        this.id=id;
    }
    public String getname(){
        return name;
    }
    public void setname(String name){
        this.name=name;
    }
    public int getatk(){
        return atk;
    }
    public void setatk(int atk){
        this.atk=atk;
    }
    public int gethp(){
        return hp;
    }
    public void sethp(int hp){
        this.hp=hp;
    }
    public int getcur(){
        return cur;
    }
    public void setcur(int cur){
        this.cur=cur;
    }




}
