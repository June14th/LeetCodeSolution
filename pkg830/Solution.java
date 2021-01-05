package pkg830;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在一个由小写字母构成的字符串 s 中，包含由一些连续的相同字符所构成的分组。
 *
 * 例如，在字符串 s = "abbxxxxzyy" 中，就含有 "a", "bb", "xxxx", "z" 和 "yy" 这样的一些分组。
 *
 * 分组可以用区间 [start, end] 表示，其中 start 和 end 分别表示该分组的起始和终止位置的下标。上例中的 "xxxx" 分组用区间表示为 [3,6] 。
 *
 * 我们称所有包含大于或等于三个连续字符的分组为 较大分组 。
 *
 * 找到每一个 较大分组 的区间，按起始位置下标递增顺序排序后，返回结果。
 */
public class Solution {
    public static void main(String[] args) {
        String s = "abcdddeeeeaabbbcd";
        List<List<Integer>> lists = largeGroupPositions(s);
        for(List<Integer> list : lists){
            System.out.println(list);
        }
    }
    public static List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> lists = new ArrayList<>();
        if(s.length()<3) return lists;
        for(int i=0;i<s.length()-2;i++){
            if(s.charAt(i) == s.charAt(i+1) && s.charAt(i) == s.charAt(i+2)){
                int start = i;
                i = i+2;
                int end = i;
                while (i<s.length()-1 && s.charAt(i) == s.charAt(i+1)){
                    i++;
                    end = i;
                }
                lists.add(Arrays.asList(start,end));
            }
        }
        return lists;
    }
}
