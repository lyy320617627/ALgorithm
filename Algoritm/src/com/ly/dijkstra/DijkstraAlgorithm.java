package com.ly.dijkstra;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertexs=new char[]{'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] martix=new int[vertexs.length][vertexs.length];
        final  int N=65535;;//表示不可连接
        martix[0]=new int[]{N,5,7,N,N,N,2};
        martix[1]=new int[]{5,N,N,9,N,N,3};
        martix[2]=new int[]{7,N,N,N,8,N,N};
        martix[3]=new int[]{N,9,N,N,N,4,N};
        martix[4]=new int[]{N,N,8,N,N,5,4};
        martix[5]=new int[]{N,N,N,4,5,N,6};
        martix[6]=new int[]{2,3,N,N,4,6,N};
        //创建Graph对象
        Graph graph = new Graph(vertexs, martix);
        //测试图的邻接矩阵是否正确
        graph.showGraph();
        //测试迪杰斯特拉算法
        graph.dsj(6);
        graph.showDijkstra();
    }
}
class Graph {
    private char[] vertexs;//顶点数组
    private int[][] martix;//邻接矩阵
    private VisitedVertex vv;//已经访问过的顶点的集合

    public Graph(char[] vertexs, int[][] martix) {
        this.vertexs = vertexs;
        this.martix = martix;
    }
   //显示结果
    public void showDijkstra(){
        vv.show();
    }
    //显示图
    public void showGraph() {
        for (int[] link : martix) {
            System.out.println(Arrays.toString(link));
        }
    }
    //迪杰斯特拉算法实现

    /**
     * @param index 表示出发顶点对应的下标
     */
    public void dsj(int index) {
        vv = new VisitedVertex(vertexs.length, index);
        update(index);//更新index顶点到周围顶点的距离和前驱结点
        for(int j=0;j< vertexs.length;j++){
            index= vv.updateArr();//选择并返回新的节点
            update(index);//更新index顶点到周围顶点的距离和前驱节点
        }
    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱结点
    private void update(int index) {
     int len=0;
     //根据遍历我们的邻接矩阵的martix[index]行
     for (int j=0;j<martix[index].length;j++){
         //len含义是：出发顶点到index顶点的距离+从index顶点到j顶点的距离的和
     len= vv.getDis(j)+martix[index][j];
     //如果j顶点没有被访问过，并且len小于出发顶点到j顶点的距离，就需要更新
         if (!vv.in(j) && len<vv.getDis(j)){
             vv.update(j,index);//更新j顶点的前驱结点为index顶点
             vv.updateDis(j,len);//更新出发顶点到j顶点的距离
         }
     }
    }

}
class VisitedVertex{//已经访问顶点集合
    public int[] already_arr;//记录各个顶点是否已经访问过 1表示访问过，0表示未访问，会自动更新
    public int[] pre_visited;//每个下标对应的值为前一个顶点下标，会自动更新
    public int[] dis;//记录出发顶点到其他所有顶点的距离，比如G为出发点，就会记录到G到其他顶点的距离，会自动更新，求的最短距离就会存放到dis
  //构造器

    /**
     *
     * @param length: 表示顶点的个数
     * @param index ：表示出发顶点对应的下标，比如G，下标就是6
     */
    public VisitedVertex(int length,int index){
        this.already_arr=new int[length];
        this.dis=new int[length];
        this.pre_visited=new int[length];
        Arrays.fill(dis,65535);
        this.already_arr[index]=1;//设置出发顶点的访问状态为1
        this.dis[index]=0;
    }

    /**
     * 功能：判断index顶点是否被访问过
     * @param index
     * @return  如果访问过，就返回true，否则返回false
     */
    public boolean in(int index){
        return pre_visited[index]==1;
    }

    /**
     * 功能：更新出发顶点到index顶点的距离
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index]=len;
    }

    /**
     * 功能：更新pre这个顶点的前驱顶点为index顶点
     * @param pre
     * @param index
     */
    public void update(int pre,int index){
        pre_visited[pre]=index;
    }

    /**
     * 功能：返回出发顶点到index顶点的距离
     * @param index
     * @return
     */
    public int getDis(int index){
        return dis[index];
    }
    //继续选择并且返回新的访问顶点，比如这里的G完后，就是A作为新的访问顶点(注意不是出发顶点)

    /**
     * 功能：继续选择并且返回新的访问结点
     * @return
     */
    public int updateArr(){
        int min=65535,index=0;
        for (int i=0;i<already_arr.length;i++){
        if (already_arr[i]==0 && dis[i]<min){
            min=dis[i];
            index=i;
        }
        }
        //更新顶点被访问过
        already_arr[index]=1;
        return index;
    }
    //显示最后的结果
    //即将三个数组的结展示出来就可以了
    public void show(){
        System.out.println("===========================");
        //输出already_arr
        for (int i:already_arr){
            System.out.printf(i +" ");
        }
        //输出pre_visited
        for (int i:pre_visited){
            System.out.printf(i+" ");
        }
        //输出dis
        for(int i:dis){
            System.out.println(i +" ");
        }
        //为了好看
        char[] vertexs={'A','B','C','D','E','F','G'};
        int count=0;
        for (int i:dis){
            if (i!=65535){
                System.out.print(vertexs[count]+"("+i+")");
            }else {
                System.out.println("N");
            }
            count++;
        }
        System.out.println();
    }
}