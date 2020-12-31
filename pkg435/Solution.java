package pkg435;

import java.util.Arrays;

/**
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 */
public class Solution {
    public static void main(String[] args) {

        int[][] intervals = {{0,2},{1,3},{2,4},{3,5},{4,6}};
        System.out.println(eraseOverlapIntervals(intervals));
    }
    public static int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length < 2) return 0;
        Arrays.sort(intervals,(i1,i2)->{
            return i1[0]-i2[0]==0?i1[1]-i2[1]:i1[0]-i2[0];
        });
        int res = 0;
        int left = intervals[0][1];
        int right = intervals[1][0];
        for(int i=1;i<intervals.length;i++){
            if(right < left){
                res++;
                if(intervals[i][1]<left){
                    left = intervals[i][1];
                }
            }else{
                left = intervals[i][1];
            }
            if(i<intervals.length-1)right = intervals[i+1][0];
        }
        return res;
    }
}
