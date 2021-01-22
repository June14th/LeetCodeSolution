package pkg989;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 *
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 */
public class Solution {
    public static void main(String[] args) {
        int[] A = {9,9,9};
        int K = 1001;
        System.out.println(addToArrayForm(A,K));
    }
    public static List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> res = new ArrayList<>();
        int n = A.length;

        for(int i= n-1; i>=0 || K > 0; i--,K/=10){
            if(i>=0){
                K += A[i];
            }
            res.add(K%10);
        }

        Collections.reverse(res);
        return res;
    }
}
