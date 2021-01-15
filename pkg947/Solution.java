package pkg947;

import java.util.HashMap;
import java.util.Map;

/**
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 *
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 *
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 */
class UnionSet{
    private Map<Integer,Integer> parent;
    private int count;

    public UnionSet(){
        this.parent = new HashMap<>();
        this.count = 0;
    }

    public int getCount() {
        return count;
    }
    public int find(int x){
        if(!parent.containsKey(x)){
            parent.put(x,x);
            count++;
        }else{
            if(x != parent.get(x)){
                parent.put(x,find(parent.get(x)));
            }
        }
        return parent.get(x);
    }
    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY){
            return;
        }
        parent.put(rootX,rootY);
        count--;
    }
}
public class Solution {
    public static void main(String[] args) {
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println(removeStones(stones));
    }
    public static int removeStones(int[][] stones) {
        UnionSet unionset = new UnionSet();
        for(int[] stone : stones){
            unionset.union(stone[0]+10001,stone[1]);
        }
        return stones.length - unionset.getCount();
    }
}
