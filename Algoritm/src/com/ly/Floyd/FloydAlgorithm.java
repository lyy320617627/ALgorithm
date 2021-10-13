package com.ly.Floyd;

import com.sun.corba.se.impl.naming.cosnaming.InternalBindingKey;

import java.util.Arrays;

//弗洛伊德算法
public class FloydAlgorithm {
    public static void main(String[] args) {
        //测试一下，图是否创建成功
        char[] vertexs={'A','B','C','D','E','F','G'};
        //创建邻接矩阵
        int[][] martix=new int[vertexs.length][vertexs.length];
        final int N=65535;
        martix[0]=new int[]{0,5,7,N,N,N,2};
        martix[1]=new int[]{5,0,N,9,N,N,3};
        martix[2]=new int[]{7,N,0,N,8,N,N};
        martix[3]=new int[]{N,9,N,0,N,4,N};
        martix[4]=new int[]{N,N,8,N,0,5,4};
        martix[5]=new int[]{N,N,N,4,5,0,6};
        martix[6]=new int[]{2,3,N,N,4,6,0};
        //创建一个图对象
        Graph graph = new Graph(martix.length, martix, vertexs);
      //调用弗洛伊德算法
        graph.floyd();
        graph.show();

    }
}
//创建图
class Graph {
    private char[] vertexs;//存放顶点的数组
    private int[][] dis;//保存，从各个顶点出发到达其他顶点的距离，最后的结果，也是保留在该数组
    private int[][] pre;//保存到达目标顶点的前驱节点

    /**
     * @param length  大小
     * @param martix  邻接矩阵
     * @param vertexs 顶点
     */
    public Graph(int length, int[][] martix, char[] vertexs) {
        this.vertexs = vertexs;
        this.dis = martix;
        this.pre = new int[length][length];
        //对pre数组初始化，注意存放的是前驱节点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    //显示pre数组和dis数组
    public void show() {
        //为了显示便于阅读，优化一下输出
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //显示pre数组
        for (int k = 0; k < dis.length; k++) {
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertexs[pre[k][i]] + " ");
            }
            System.out.println();
            //输出dis数组的一行数据
            for (int i = 0; i < dis.length; i++) {
                System.out.print("(" + vertexs[k] + "到" + vertexs[i] + "的最短路径是" + dis[k][i] + ")");
            }
            System.out.println();
            System.out.println();
        }
    }

    //弗洛伊德算法
    public void floyd() {
        int len = 0;//保存距离
        //对中间节点遍历，k就是中间节点的下标{A,B,C,D,E,F,G}
        for (int k = 0; k < dis.length; k++) {
            //从i顶点出发，{A,B,C,D,E,F,G}
            for (int i = 0; i < dis.length; i++) {
                //到达顶点j{A,B,C,D,E,F,G}
                for (int j = 0; j < dis.length; j++) {
                    len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {
                        dis[i][j] = len;//更新距离
                        pre[i][j] = pre[k][j];//更新一下前驱节点
                    }
                }
            }
        }
    }
}