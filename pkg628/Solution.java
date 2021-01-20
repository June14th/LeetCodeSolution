package pkg628;

import java.util.Arrays;

/**
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 */
public class Solution {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        System.out.println(maximumProduct(nums));
    }
    public static int maximumProduct(int[] nums) {
        int len = nums.length;
        if(len == 3) return nums[0]*nums[1]*nums[2];

        Arrays.sort(nums);

        int num1 = nums[0];
        int num2 = nums[1];
        int num3 = nums[len-1];

        return Math.max(num1*num2*num3,nums[len-1]*nums[len-2]*nums[len-3]);
    }

}
