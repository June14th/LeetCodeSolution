package pkg189;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 */
public class Solution {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        int k = 3;
        rotate(nums,k);
        for(int i : nums){
            System.out.print(i+", ");
        }
    }

    /**
     * 双重循环：依次向后挪 1 次
     *时间 O(kn)
     *空间 O(1)
     * @param nums
     * @param k
     */
    /*
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
         k %= n;
        for(int i=0;i<k;i++){
            int mid = nums[n-1];
            for(int j=n-1;j>0;j--){
                nums[j] = nums[j-1];
            }
            nums[0] = mid;
        }
    }*/
    /**
     * 存储后 k 个，然后向后挪
     * 时间 O(n)
     * 空间 O(k)
     * @param nums
     * @param k
     */
    /*
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        int[] mid = new int[k];
        for(int i=0;i<k;i++){
            mid[i] = nums[n-k+i];
        }
        for(int i=n-1;i>=k;i--){
            nums[i] = nums[i-k];
        }
        for(int i=0;i<k;i++){
            nums[i] = mid[i];
        }
    }*/

    /**
     * 翻转
     * 时间 O(n)
     * 空间 O(1)
     * @param nums
     * @param k
     */
    public static void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums,0,n-1);
        reverse(nums,0,k-1);
        reverse(nums, k,n-1);
    }
    private static void reverse(int[] nums, int start, int end) {
        while(start < end){
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}
