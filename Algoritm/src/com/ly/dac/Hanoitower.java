package com.ly.dac;

public class Hanoitower {
    public static void main(String[] args) {
        hanoTower(5,'A','B','C');
    }
    //汉诺塔的移动的方法
    //使用分治算法实现
    public static void hanoTower(int num,char a,char b,char c){
        //如果只有一个盘
        if (num==1){
            System.out.println("第1个盘从"+a+"->"+c);
        }else {
            //如果我们有两个盘，我们总是看做是两个盘1.最下面的一个盘2.最上面的所有盘
            //1.先把最上面的盘A->B,移动过程会使用到c
            hanoTower(num-1,a,c,b);
            System.out.println("第"+num+"个盘从"+a+"->"+c);
            //2.把B塔上面的所有盘从B->C,移动过程中使用到A塔
            hanoTower(num-1,b,a,c);
        }
    }
}
