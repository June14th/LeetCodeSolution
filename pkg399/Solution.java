package pkg399;

import java.util.*;

/**
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 *
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 *
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 */
public class Solution {
    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a","b"));
        equations.add(Arrays.asList("b","c"));
        double[] values = {2.0,3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a","c"));
        queries.add(Arrays.asList("b","a"));
        queries.add(Arrays.asList("a","e"));
        queries.add(Arrays.asList("a","a"));
        queries.add(Arrays.asList("x","x"));
        double[] res = calcEquation(equations,values,queries);
        for(double i : res){
            System.out.print(i+", ");
        }

    }

    /**
     * Floyd 算法
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nvars = 0;
        Map<String,Integer> map = new HashMap<>();
        int eqnum = equations.size();
        for(int i=0;i<eqnum;i++){
            if(!map.containsKey(equations.get(i).get(0))){
                map.put(equations.get(i).get(0),nvars++);
            }
            if(!map.containsKey(equations.get(i).get(1))){
                map.put(equations.get(i).get(1),nvars++);
            }
        }
        double[][] matrix = new double[nvars][nvars];
        for(int i=0;i<nvars;i++){
            Arrays.fill(matrix[i],-1.0);
        }
        int valnum = values.length;
        for(int i=0;i<valnum;i++){
            int num1 = map.get(equations.get(i).get(0));
            int num2 = map.get(equations.get(i).get(1));
            matrix[num1][num2] = values[i];
            matrix[num2][num1] = 1.0/values[i];
        }
        for(int k=0;k<nvars;k++){
            for(int i=0;i<nvars;i++){
                for(int j=0;j<nvars;j++){
                    if(matrix[i][k]>0 && matrix[k][j] > 0){
                        matrix[i][j] = matrix[i][k] * matrix[k][j];
                    }
                }
            }
        }
        int quenum = queries.size();
        double[] res = new double[quenum];
        for(int i=0;i<quenum;i++){
            if(!map.containsKey(queries.get(i).get(0)) || !map.containsKey(queries.get(i).get(1))){
                res[i] = -1.0;
            }else{
                int num1 = map.get(queries.get(i).get(0));
                int num2 = map.get(queries.get(i).get(1));
                res[i] = matrix[num1][num2];
            }
        }
        return res;
    }
    /**
     * 只针对单个字符
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    /*
    public static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] sign = new double[26];
        Arrays.fill(sign,0);
        for(int i=0;i<equations.size();i++){
            char num1 = equations.get(i).get(0).charAt(0);
            char num2 = equations.get(i).get(1).charAt(0);
            if(sign[num1-'a']!=0){
                sign[num2-'a'] = sign[num1-'a']/values[i];
            }else if(sign[num2-'a']!=0){
                sign[num1-'a'] = sign[num2-'a']*values[i];
            }else{
                sign[num2-'a'] = 1;
                sign[num1-'a'] = sign[num2-'a']*values[i];
            }
        }
        double[] res = new double[queries.size()];
        for(int i=0;i<queries.size();i++) {
            char num1 = queries.get(i).get(0).charAt(0);
            char num2 = queries.get(i).get(1).charAt(0);
            if(sign[num1-'a']==0 || sign[num2-'a']==0){
                res[i] = -1;
            }else{
                res[i] = sign[num1-'a']/sign[num2-'a'];
            }
        }
        return res;
    }

     */

}
