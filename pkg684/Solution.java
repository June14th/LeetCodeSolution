package pkg684;

/**
 * 在本问题中, 树指的是一个连通且无环的无向图。
 *
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 *
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 *
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 */
public class Solution {
    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{2,3}};
        for(int i : findRedundantConnection(edges)){
            System.out.print(i+",");
        }

    }
    public static int[] findRedundantConnection(int[][] edges) {
        int length = edges.length;
        int[] parents= new int[length+1];
        for(int i=0;i<=length;i++){
            parents[i] = i;
        }
        for(int[] num : edges){
            int index1 = num[0];
            int index2 = num[1];
            if(find(parents,index1) != find(parents,index2)){
                union(parents,index1,index2);
            }else{
                return num;
            }
        }
        return new int[0];
    }

    public static void union(int[] parents, int index1, int index2) {
        parents[find(parents,index2)] = find(parents,index1);
    }

    public static int find(int[] parents, int index) {
        if(parents[index] != index){
            return find(parents,parents[index]);
        }
        return index;
    }
}
