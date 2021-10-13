package com.ly.horse;

import com.sun.scenario.effect.impl.prism.ps.PPSZeroSamplerPeer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

//马踏棋盘的算法
public class HorseChessBoard {
    private  static  int X;//表示棋盘的列数
    private  static int Y;//棋盘的行数
    //创建一个数组，标记棋盘的各个位置是否被访问过
    private  static boolean visited[];
    //使用一个属性，标记是否棋盘的所有位置都被访问过
    private static  boolean finished;//如果为true，表示成功
    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运算~~");
     //测试一下棋盘算法
        X=8;
        Y=8;
        int row=1;//马儿初始位置的行，从1开始编号
        int column=1;//马儿初始位置的列，从1开始
        //创建棋盘
        int[][] chessboard=new int[X][Y];
        visited=new boolean[X*Y];
        //测试一下耗时
        long start =System.currentTimeMillis();
        traversalChessBoard(chessboard,row-1,column-1,1);
        long end=System.currentTimeMillis();
        System.out.println("共耗时:"+(end-start)+"毫秒");
        //输出棋盘的最后情况
        for (int[] rows: chessboard){
            for(int step:rows){
                System.out.print(step+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 功能：完成骑士周游的算法
     * @param chessboard  棋盘
     * @param row 马儿的当前的位置的行，从0开始记录
     * @param column  马儿当前位置的列，从0开始
     * @param step 是第几步，初始位置就是第一步
     */
    public static  void traversalChessBoard(int[][] chessboard,int row,int column,int step){
        //标记马儿的位置为第一步
        chessboard[row][column]=step;
        //标记马儿位置为已经访问过了
        visited[row*X+column]=true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps进行排序，排序的规则就是对ps的所有的Point对象的下一步的位置的所有元素，进行排序
        sort(ps);
        //遍历ps
        while (!ps.isEmpty()){
            Point p = ps.remove(0);//取出下一个可以走的位置
                //判断该点是否被访问过
            if(!visited[p.y*X+p.x]){//如果没有被访问过
                traversalChessBoard(chessboard,p.y,p.x,step+1);
            }
        }
        //判断马儿是否完成了任务，如果没有完成任务，将棋盘置0
        //说明：step<X*Y,成立的情况有两种
        //1.棋盘到目前位置，仍然没有走完
        //2.棋盘处于一个回溯的过程
        if (step<X*Y && !finished){
            chessboard[row][column]=0;
            visited[row*X+column]=false;
        }else {
            finished=true;
        }

    }


    /**
     * 功能：根据当前位置（Point对象），计算马儿还能走那些位置（Point），并放入到一个集合（ArrayList）中，最多有8个位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
         //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        if ((p1.x=curPoint.x-2)>=0 && (p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        if ((p1.x=curPoint.x-1)>=0 && (p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        if ((p1.x= curPoint.x+1)< X && (p1.y=curPoint.y-2)>=0){
            ps.add(new Point(p1));
        }
        if ((p1.x=curPoint.x+2)<X &&(p1.y=curPoint.y-1)>=0){
            ps.add(new Point(p1));
        }
        if ((p1.x=curPoint.x+2)<X &&(p1.y= curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        if ((p1.x=curPoint.x+1)<X && (p1.y= curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        if((p1.x=curPoint.x-2)>=0 && (p1.y= curPoint.y+1)<Y){
            ps.add(new Point(p1));
        }
        if ((p1.x=curPoint.x-1)>=0&&(p1.y=curPoint.y+2)<Y){
            ps.add(new Point(p1));
        }
        return ps;
    }
   public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1=next(o1).size();
                int count2=next(o2).size();
                if (count1<count2){
                    return -1;
                }else if(count1==count2){
                    return 0;
                }else {
                    return 1;
                }
            }
        });
   }

}
