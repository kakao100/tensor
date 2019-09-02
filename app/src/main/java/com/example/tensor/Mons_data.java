package com.example.tensor;
//モンスターデータのクラス

import android.util.Log;

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
    private String[] skill_feature;
    private int converted_drop;
    private int convert_drop;
    private int leader_skill_id;
    private String leader_skill_name;
    private String leader_skill_exp;
    private String[] leader_skill_feature;
    private double leader_skill_hp;
    private double leader_skill_attack;
    private double leader_skill_cure;
    private double leader_skill_Alle;
    private String[] awa;
    private String[] sawa;


    public Mons_data(String line) {
        //図鑑ナンバー(数字),モンスター名(日本語),属性(英語),副属性(英語),レア度(数字),コスト(数字),継承可能か(真偽),タイプ１qタイプ２qタイプ３(日本語),
        // HPステータスq攻撃ステータスq回復ステータス(数字),110HPステータスq110攻撃ステータスq110回復ステータス(数字),スキルID(数字)qスキル名(日本語),
        // 最短スキルターンq最大スキルターン(数字),スキル効果名(日本語),スキル特徴ID(数字)qスキル特徴(日本語),変換元(数字),変換先の色(数字),リータースキルID(数字)qリーダースキル名(日本語),
        // リーダスキル効果(日本語),リーダスキル特徴ID(数字)qリーダスキル特徴(日本語)qリーダスキル特徴ID(数字)qリーダスキル特徴(日本語)q並列,
        // リーダースキル倍率HP,リーダースキル倍率攻撃,リーダースキル倍率回復,軽減の倍率(数字),覚醒(日本語)q覚醒(日本語)q覚醒(日本語),,,,
        //0~9    1,ティラ,fire,no,2,2,×,ドラゴンタイプ,no,no,
        //10~17 144,71,13,no,no,no,1,ヒートブレス,
        //18~26 8,3,自分の攻撃力×10倍の火属性攻撃,1q単体ダメージq3q属性ダメージq,no,no,1,火の力,
        // 火属性の攻撃力が1.5倍。ドロップ操作時間を2秒延長。,1q属性倍率q1q攻撃倍率q1q操作時間延長q,0,1.5,0,0,noq,noq,
        String[] data = line.split(",");
        String[] temp;
        if(!data[0].equals("no")) {
            id = Integer.parseInt(data[0]);
            name = data[1];
            attribute = data[2];
            subattribute = data[3];
            rare = Integer.parseInt(data[4]);
            cost = Integer.parseInt(data[5]);
            if (data[6].equals("×"))
                inheritance = false;
            else
                inheritance = true;
            type1 = data[7];
            type2 = data[8];
            type3 = data[9];
            if (data[10].contains("no")) {
                hp = 0;
                attack = 0;
                cure = 0;
            } else {
                hp = Integer.parseInt(data[10]);
                attack = Integer.parseInt(data[11]);
                cure = Integer.parseInt(data[12]);
            }
            if (data[13].contains("no")) {
                hp_110 = 0;
                attack_110 = 0;
                cure_110 = 0;
            } else {
                hp_110 = Integer.parseInt(data[13]);
                attack_110 = Integer.parseInt(data[14]);
                cure_110 = Integer.parseInt(data[15]);
            }
            //18~25 8,3,自分の攻撃力×10倍の火属性攻撃,1q単体ダメージq3q属性ダメージq,no,no,1,火の力,
            // 火属性の攻撃力が1.5倍。ドロップ操作時間を2秒延長。,1q属性倍率q1q攻撃倍率q1q操作時間延長q,0,1.5,0,0,noq,noq,
            if (!data[16].equals("no")) {
                skill_id = Integer.parseInt(data[16]);
                skill_name = data[17];
                longest_tern = Integer.parseInt(data[18]);
                shortest_tern = Integer.parseInt(data[19]);
                skill_exp = data[20];
                skill_feature = split_q(data[21]);
            }
            convert_drop = 0;
            converted_drop = 0;
            if (!data[24].equals("no")) {
                leader_skill_id = Integer.parseInt(data[24]);
                leader_skill_name = data[25];
                leader_skill_exp = data[26];
                leader_skill_feature = split_q(data[27]);
            }
            //0をparseDouble出来ないのかな、、、
            //エラーが出ます
            //leader_skill_hp = Double.parseDouble(data[28]);
            //leader_skill_attack = Double.parseDouble(data[29]);
            //leader_skill_cure = Double.parseDouble(data[30]);
            //leader_skill_Alle = Double.parseDouble(data[31]);
            awa = split_q(data[32]);
            sawa = split_q(data[33]);
        }
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
    public String[] getskill_feature(){ return skill_feature; }
    public int getconverted_drop(){ return converted_drop; }
    public int getconvert_drop(){ return convert_drop; }
    public int getleader_skill_id(){ return leader_skill_id; }
    public String getleader_skill_name(){ return leader_skill_name; }
    public String getleader_skill_exp(){ return leader_skill_exp; }
    public String[] getleader_skill_feature(){ return leader_skill_feature; }
    public double getleader_skill_hp(){ return leader_skill_hp; }
    public double getleader_skill_attack(){ return leader_skill_attack; }
    public double getleader_skill_cure(){ return leader_skill_cure; }
    public double getleader_skill_Alle(){ return leader_skill_Alle; }
    public String[] getawa(){ return awa; }
    public String[] getsawa(){ return sawa; }
    public String[] split_q(String line){return line.split("q");}



}
