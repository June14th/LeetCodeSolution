package pkg803;

/**
 * 有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
 *
 * 一块砖直接连接到网格的顶部，或者
 * 至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
 * 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。
 *
 * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
 *
 * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
 */
class UnionFind{
    private int[] parents;
    private int[] size;

    public UnionFind(int n){
        this.parents = new int[n];
        this.size = new int[n];
        for(int i=0;i<n;i++){
            parents[i] = i;
            size[i] = 1;
        }
    }
    public int find(int x){
        if(x != parents[x]){
            return find(parents[x]);
        }
        return parents[x];
    }
    public void union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY){
            return;
        }
        parents[rootX] = rootY;
        size[rootY] += size[rootX];
    }
    public int getSize(int x){
        return size[find(x)];
    }
}
public class Solution {
    private int rows;
    private int cols;

    public static final int[][] DIRECTIONS = {{0,1},{1,0},{-1,0},{0,-1}};

    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0},{1,1,1,0}};
        int[][] hits = {{1,0}};
        for(int i : new Solution().hitBricks(grid,hits)){
            System.out.print(i +", ");
        }
    }
    public int[] hitBricks(int[][] grid, int[][] hits) {
        this.rows = grid.length;
        this.cols = grid[0].length;
        //第一步：将hits中的砖头全部敲碎
        int[][] copy = new int[rows][cols];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                copy[i][j] = grid[i][j];
            }
        }
        for(int[] hit : hits){
            copy[hit[0]][hit[1]] = 0;
        }
        //第二步：建图，把砖块和砖块的连接关系输入并查集，size表示二维网络的大小，也表示虚拟的「屋顶」在并查集中的编号
        int size = rows * cols;
        UnionFind unionFind = new UnionFind(size + 1);
        //将下标为0的砖块与屋顶（size）相连
        for(int j=0;j<cols;j++){
            if(copy[0][j] == 1){
                unionFind.union(j,size);
            }
        }
        //其余的网格，如果砖块向上或向左也是砖块，则进行合并
        for(int i=1;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(copy[i][j] == 1){
                    if(copy[i-1][j] == 1){
                        unionFind.union(getIndex(i-1,j),getIndex(i,j));
                    }
                    if(copy[i][j-1] == 1){
                        unionFind.union(getIndex(i,j-1),getIndex(i,j));
                    }
                }
            }
        }
        //第三步：按照hits的逆序，在copy中补全砖块，把每一次因补回砖块而与屋顶相连的砖块增量记录到结果中
        int hitLen = hits.length;
        int[] res = new int[hitLen];
        for(int i=hitLen-1;i>=0;i--){
            int x = hits[i][0];
            int y = hits[i][1];
            //如果要去掉的砖块本来就没有，则去掉这一块不会产生影响
            if(grid[x][y] == 0){
                continue;
            }
            //原本与屋顶相连的砖块数量
            int origin = unionFind.getSize(size);
            //如果要补回的砖块在第一行，则直接与屋顶相连
            if(x == 0){
                unionFind.union(y,size);
            }
            //从四个方向上看，如果四个方向上有砖块则合并它们
            for( int[] direction : DIRECTIONS){
                int newX = x + direction[0];
                int newY = y + direction[1];
                if(inArea(newX,newY) && copy[newX][newY] == 1){
                    unionFind.union(getIndex(newX,newY),getIndex(x,y));
                }

            }
            int current = unionFind.getSize(size);
            res[i] = Math.max(0,current - origin - 1);

            copy[x][y] = 1;
        }
        return res;
    }
    public boolean inArea(int x,int y){
        return x>=0 && x<rows && y>=0 && y<cols;
    }
    public int getIndex(int x, int y){
        return x * cols + y;
    }
}
