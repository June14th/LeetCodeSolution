package pkg721;

import java.util.*;

/**
 * 给定一个列表 accounts，每个元素 accounts[i] 是一个字符串列表，其中第一个元素 accounts[i][0] 是 名称 (name)，其余元素是 emails 表示该账户的邮箱地址。
 *
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 *
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 */
class UnionFind{
    int[] parents;

    public UnionFind(int n){
        parents = new int[n];
        for(int i=0;i<n;i++){
            parents[i] = i;
        }
    }
    public int find(int x){
        if(x != parents[x]){
            return find(parents[x]);
        }
        return parents[x];
    }
    public void union(int x,int y){
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY){
            return;
        }
        parents[rootY] = rootX;
    }
}
public class Solution {
    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(Arrays.asList("John","johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary","mary@mail.com"));
        List<List<String>> res = accountsMerge(accounts);
        for(List<String> result : res){
            System.out.println(result);
        }

    }
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        //邮箱到索引的map
        Map<String,Integer> emailToIndex = new HashMap<>();
        //邮箱到姓名的map
        Map<String,String> emailToName = new HashMap<>();
        int emailCount = 0;
        for(List<String> account : accounts){
            String name = account.get(0);
            for(int i=1;i<account.size();i++){
                String email = account.get(i);
                if(!emailToIndex.containsKey(email)){
                    emailToIndex.put(email,emailCount++);
                    emailToName.put(email,name);
                }
            }
        }
        //建立并查集，邮箱之间的连通
        UnionFind unionFind = new UnionFind(emailCount);
        for(List<String> account : accounts){
            int firstIndex = emailToIndex.get(account.get(1));
            for(int i=2;i<account.size();i++){
                int nextIndex = emailToIndex.get(account.get(i));
                unionFind.union(firstIndex,nextIndex);
            }
        }

        Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
        //建立索引和连通邮箱之间的map
        for(String email : emailToIndex.keySet()) {
            int root = unionFind.find(emailToIndex.get(email));
            if (!map.containsKey(root)) {
                List<String> list = new ArrayList<>();
                list.add(email);
                map.put(root, list);
            } else {
                map.get(root).add(email);
            }
        }
        //组织结果
        List<List<String>> res = new ArrayList<>();
        for(int index : map.keySet()){
            List<String> emails = map.get(index);
            //将邮箱进行排序
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> list = new ArrayList<>();
            list.add(name);
            list.addAll(emails);
            res.add(list);
        }
        return res;
    }
}
