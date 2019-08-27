package com.example.tensor;
//モンスターデータのクラス

public class Mons_data {
    //図鑑番号
    private int id;
    private String name;
    private String attribute;
    private String subattribute;
    private int rare;
    private int cost;
    private boolean inheritance;
    private String type1;
    private String type2;
    private String type3;
    private int hp;
    private int attack;
    private int cure;
    private int hp_110;
    private int attack_110;
    private int cure_110;
    private int skill_id;
    private String skill_name;
    private int shortest_tern;
    private int longest_tern;
    private String skill_exp;
    private int skill_feature;
    private int converted_drop;
    private int convert_drop;
    private int leader_skill_id;
    private String leader_skill_name;
    private int leader_skill_hp;
    private int leader_skill_attack;
    private int leader_skill_cure;
    private int leader_skill_Alle;
    private String[] awa;


    public Mons_data(String line){
        //図鑑ナンバー(数字),モンスター名(日本語),属性(英語),副属性(英語),レア度(数字),コスト(数字),継承可能か(真偽),タイプ１qタイプ２qタイプ３(日本語),
        // HPステータスq攻撃ステータスq回復ステータス(数字),110HPステータスq110攻撃ステータスq110回復ステータス(数字),スキルID(数字)qスキル名(日本語),
        // 最短スキルターンq最大スキルターン(数字),スキル効果名(日本語),スキル特徴ID(数字)qスキル特徴(日本語),変換元(数字),変換先の色(数字),リータースキルID(数字)qリーダースキル名(日本語),
        // リーダスキル効果(日本語),リーダスキル特徴ID(数字)qリーダスキル特徴(日本語)qリーダスキル特徴ID(数字)qリーダスキル特徴(日本語)q並列,
        // リーダースキル倍率HP,リーダースキル倍率攻撃,リーダースキル倍率回復,軽減の倍率(数字),覚醒(日本語)q覚醒(日本語)q覚醒(日本語),,,,
        String[] data=line.split(",");
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        this.attack = Integer.parseInt(data[2]);
        this.hp = Integer.parseInt(data[3]);
        this.cure = Integer.parseInt(data[4]);
        this.longest_tern = Integer.parseInt(data[5]);
        this.shortest_tern = Integer.parseInt(data[6]);
    }
    public int getid(){ return id; }
    public String getname(){ return name; }
    public String getattribute(){ return attribute; }
    public String getsubattribute(){ return subattribute; }
    public int getrare(){ return rare; }
    public int getcost(){ return cost; }
    public boolean getinheritance(){ return inheritance; }
    public String gettype1(){ return type1; }
    public String gettype2(){ return type2; }
    public String gettype3(){ return type3; }
    public int gethp(){ return hp; }
    public int getattack(){ return attack; }
    public int getcure(){ return cure; }
    public int gethp_110(){ return hp_110; }
    public int getattack_110(){ return attack_110; }
    public int getcure_110(){ return cure_110; }
    public int getskill_id(){ return skill_id; }
    public String getskill_name(){ return skill_name; }
    public int getshortest_tern(){ return shortest_tern; }
    public int getlongest_tern(){ return longest_tern; }
    public String getskill_exp(){ return skill_exp; }
    public int getskill_feature(){ return skill_feature; }
    public int getconverted_drop(){ return converted_drop; }
    public int getconvert_drop(){ return convert_drop; }
    public int getleader_skill_id(){ return leader_skill_id; }
    public String getleader_skill_name(){ return leader_skill_name; }
    public int getleader_skill_hp(){ return leader_skill_hp; }
    public int getleader_skill_attack(){ return leader_skill_attack; }
    public int getleader_skill_cure(){ return leader_skill_cure; }
    public int getleader_skill_Alle(){ return leader_skill_Alle; }
    public String[] getawa(){ return awa; }




}
