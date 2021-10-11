package com.ly.prim;

import java.util.Arrays;

//普利姆算法
public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试看看图是否创建成功
        char[] data=new char[]{'A','B','C','D','E','F','G'};
        int verxs= data.length;
        //把邻接矩阵的关系用二维数组表示,10000表示较大的数表示两个点不连通
        int[][] weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000}};
        //创建MGraph的对象
        MGraph mGraph = new MGraph(verxs);
        //创建一个minTree对象
        Mintree mintree = new Mintree();
        mintree.createGraph(mGraph,verxs,data,weight);
        //输出
        mintree.showGraph(mGraph);
        //测试普利姆算法
        mintree.prim(mGraph,0);
    }
    }
//创建最小生成树->村庄的图
class Mintree{
    //创建图的邻接矩阵

    /**
     *
     * @param graph 图对象
     * @param verxs 图对应的顶点个数
     * @param data   图的各个顶点的值
      * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int verxs,char data[],int weight[][]){
        int i,j;
        for (i=0;i<verxs;i++){//顶点
            graph.data[i]=data[i];
            for (j=0;j<verxs;j++){
                    graph.weight[i][j]=weight[i][j];
            }
        }
    }
    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for (int[] link: graph.weight){
            System.out.println(Arrays.toString(link));
        }
    }
  //编写一个prim算法，得到最小生成树

    /**
     *
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成
     */
    public void prim(MGraph graph,int v){
        //visited[] 标记结点（顶点）是否被访问过
        int[] visited = new int[graph.verxs];
        //visited[] 默认元素的值都是0，表示没有访问过
        for (int i = 0; i < graph.verxs; i++) {
            visited[i]=0;
        }
        //把当前节点标记为已访问
        visited[v]=1;
        //h1和h2记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        int minWeight=10000;//将minWeight初始成一个大数，后面遍历过程中，会被替换
        for (int k=1;k<graph.verxs;k++){//因为有graph.verxs顶点，普利姆算法结束后，有graph.verxs-1个边
            for (int i = 0; i < graph.verxs; i++) {//这个是确定每一次生成的子图，和那个结点的距离最近
                for (int j=0;j<graph.verxs;j++){//i表示被访问过的节点，j表示没有被访问过的节点
                    if (visited[i]==1 && visited[j]==0 &&graph.weight[i][j]<minWeight){
                        //寻找（已经访问过的节点和未被访问的节点间的权值最小的边）
                        minWeight=graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            //找到一条边是最小的
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+">权值"+minWeight);
            //将当前这个结点标记为已经访问过了
            visited[h2]=1;
            //minWeight重新设置成一个最大值10000
            minWeight=10000;
        }
    }
}
class MGraph{
    int verxs;//表示图的节点个数
    char[] data;//存放节点数据
    int[][] weight;//存放边，就是我们的邻接矩阵
    public MGraph(int verxs){
         this.verxs=verxs;
         data=new char[verxs];
         weight=new int[verxs][verxs];

    }
}
