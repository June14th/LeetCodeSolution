class Solution {
    public int findLengthOfLCIS(int[] nums) {
        int n = nums.length;
        if(n < 1) return n;
        int curlen = 1;
        int maxlen = 1;
        for(int i=1;i<n;i++){
            if(nums[i] > nums[i-1]){
                curlen ++;
            }else{
                curlen = 1;   
            }
            maxlen = Math.max(maxlen,curlen);
        }
        return maxlen;

    }
}
