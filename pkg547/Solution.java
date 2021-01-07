package pkg547;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 *
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 *
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 *
 * 返回矩阵中 省份 的数量。
 */
public class Solution {
    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(isConnected));
    }
    public static int findCircleNum(int[][] isConnected) {
        int num = isConnected.length;
        int[] area = new int[num];
        for(int i=0;i<num;i++){
            area[i] = i;
        }
        for(int i=0;i<num;i++){
            for(int j=i+1;j<num;j++){
                if(isConnected[i][j] == 1){
                    union(area,i,j);
                }
            }
        }
        int res = 0;
        for(int i=0;i<num;i++){
            if(area[i] == i){
                res++;
            }
        }
        return res;
    }

    private static void union(int[] area, int index1, int index2) {
        area[findarea(area,index1)] = findarea(area,index2);
    }

    private static int findarea(int[] area, int index) {
        if(area[index] != index){
            return findarea(area,area[index]);
        }
        return area[index];
    }

}
