package pkg228;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个无重复元素的有序整数数组 nums 。
 *
 * 返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，nums 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 nums 的数字 x 。
 *
 * 列表中的每个区间范围 [a,b] 应该按如下格式输出：
 *
 * "a->b" ，如果 a != b
 * "a" ，如果 a == b
 */
public class Solution {
    public static void main(String[] args) {
        int[] nums = {0,2,3,4,6,8,9};
        List<String> list = summaryRanges(nums);
        for(String s : list){
            System.out.print(s+", ");
        }
    }
    public static List<String> summaryRanges(int[] nums) {
        List<String> list = new ArrayList<>();
        int n = nums.length;
        if(n == 0) return list;
        for(int i=0;i<n;i++){
            StringBuilder s = new StringBuilder();
            if(i==n-1 || (i<n-1 && nums[i+1]!=nums[i]+1)){
                s.append(nums[i]);
            }else{
                s.append(nums[i]);
                s.append("->");
                while(i<n-1 && nums[i]+1==nums[i+1]){
                    i++;
                }
                s.append(nums[i]);
            }
            list.add(s.toString());
        }
        return list;
    }
}
