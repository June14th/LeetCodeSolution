class Solution {
    int[] f;
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        f = new int[n];
        for(int i=0;i<n;i++){
            f[i] = i;
        }
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int rootI = find(i);
                int rootJ = find(j);
                if(rootI == rootJ){
                    continue;
                }
                if(check(strs[i],strs[j],m)){
                    f[rootI] = rootJ;
                }
            }
        }
        int res = 0;
        for(int i=0;i<n;i++){
            if(f[i] == i){
                res++;
            }
        }
        return res;
    }
    public int find(int x){
        return f[x] == x ? f[x] : find(f[x]);
    }
    public boolean check(String s1,String s2,int len){
        int count = 0;
        for(int i=0;i<len;i++){
            if(s1.charAt(i) != s2.charAt(i)){
                count++;
                if(count > 2) return false;
            }
        }
        return true;
    }
}
