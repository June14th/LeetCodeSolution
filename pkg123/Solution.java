package pkg123;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 */
public class Solution {
    public static void main(String[] args) {

        int[] prices = {3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(prices));
    }
    public static int maxProfit(int[] prices) {
        int firstBuy = Integer.MIN_VALUE;
        int firstSell = 0;
        int secBuy = Integer.MIN_VALUE;
        int secSell = 0;
        for(int p : prices){
            firstBuy = Math.max(firstBuy,-p);
            firstSell = Math.max(firstSell,firstBuy + p);
            secBuy = Math.max(secBuy,firstSell - p);
            secSell = Math.max(secSell,secBuy + p);
        }
        return secSell;
    }
}
