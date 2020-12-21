/**
 * Copyright (c) 2018-2020 ixiancheng.com All Rights Reserved.
 */
package com.algorithm.sha.stock;

/**
 * 尽可能多的完成交易，最大收益
 *
 * @author 沙志鸿
 * @version Stock1.java, v0.1 2020/12/19 沙志鸿 Exp $$
 */
public class Stock2 {
    public static int[] mem;

    /**
     * 贪心算法
     *
     * @param prices 价格
     * @return int
     */
    public int solution(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    /**
     * 备忘录
     *
     * @param prices 价格
     * @return int
     */
    public int dpSolution(int[] prices) {
        mem = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            mem[i] = -1;
        }
        return dp(0, prices);
    }

    /**
     * 状态机
     * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的。可以这样改写框架：
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *             = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
     *
     * 我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
     * @param prices 价格
     * @return int
     */
    public int dpSolution2(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, tmp - prices[i]);
        }
        return dp_i_0;
    }

    public int dp(int start, int[] prices) {
        if (start >= prices.length) {
            return 0;
        }
        if (mem[start] != -1) {
            return mem[start];
        }
        int result = 0;
        int currentMin = prices[start];
        for (int i = start + 1; i < prices.length; i++) {
            currentMin = Math.min(prices[i], currentMin);
            result = Math.max(result, dp(i + 1, prices) + prices[i] - currentMin);
        }
        mem[start] = result;
        return result;
    }

    public static void main(String[] args) {
        Stock2 stock2 = new Stock2();
        int[] prices = new int[]{1, 3, 5, 10, 9};
        System.out.println(stock2.solution(prices));
        System.out.println(stock2.dpSolution(prices));
        System.out.println(stock2.dpSolution2(prices));
    }
}
