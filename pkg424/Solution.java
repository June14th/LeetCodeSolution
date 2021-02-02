class Solution {
    public int characterReplacement(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxlen = 0;
        int left = 0;
        int right = 0;
        while(right < n){
            num[s.charAt(right) - 'A']++;
            maxlen = Math.max(maxlen,num[s.charAt(right)-'A']);
            if(right-left+1-maxlen>k){
                num[s.charAt(left)-'A']--;
                left++;
            }
            right++;
        }
        return right-left;
    }
}
