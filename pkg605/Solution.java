package pkg605;

/**
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 */
public class Solution {
    public static void main(String[] args) {

        int[] flowerbed = {1, 0, 0, 0, 0, 1};
        int n = 2;
        System.out.println(canPlaceFlowers(flowerbed, n));
    }

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if(n == 0) return true;
        if(flowerbed.length == 1 && n == 1){
            return flowerbed[0] == 0;
        }
        if (n > (flowerbed.length + 1)/ 2) return false;
        for (int i = 0; i < flowerbed.length; i++) {
            if(flowerbed[i] == 1){
                continue;
            }
            if (i == 0) {
                if(flowerbed[i+1] == 0){
                    n--;
                    flowerbed[i] = 1;
                }
            } else if (i == flowerbed.length - 1) {
                if(flowerbed[i - 1] == 0 ){
                    n--;
                    flowerbed[i] = 1;
                }
            } else {
                if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    n--;
                    flowerbed[i] = 1;
                }
            }
        }
        return n <= 0;
    }
}
