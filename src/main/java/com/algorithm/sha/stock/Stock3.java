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
public class Stock3 {
    public static int[][] mem;

    /**
     * 备忘录
     *
     * @param prices 价格
     * @param k      k
     * @return int
     */
    public int dpSolution(int[] prices, int k) {
        mem = new int[prices.length][k];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < k; j++) {
                mem[i][j] = -1;
            }
        }
        return dp(0, k, prices);
    }

    /**
     * 隔一天才能交易
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
     * 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
     *
     * @param prices 价格
     * @return int
     */
    public int dpSolution2(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        // 代表 dp[i-2][0]
        int dp_pre_0 = 0;
        for (int i = 0; i < prices.length; i++) {
            int tmp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
            dp_pre_0 = tmp;
        }
        return dp_i_0;
    }

    /**
     * k = 2
     * 原始的动态转移方程，没有可化简的地方
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     * @param prices 价格
     * @return int
     */
    public int dpSolution3(int[] prices) {
        int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
        int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i20 = Math.max(dp_i20, dp_i21 + price);
            dp_i21 = Math.max(dp_i21, dp_i10 - price);
            dp_i10 = Math.max(dp_i10, dp_i11 + price);
            dp_i11 = Math.max(dp_i11, -price);
        }
        return dp_i20;
    }

    /**
     * k = any integer
     *
     * @param prices 价格
     * @return int
     */
    public int dpSolution4(int max_k, int[] prices) {
        int n = prices.length;
        if (max_k > n / 2) {
            //所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k = +infinity。这种情况是之前解决过的。
            return 0;
        }
        int[][][] dp = new int[n][max_k + 1][2];
        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                if (i == 0) {
                    //处理 base case
                    dp[0][k][0] = 0;
                    dp[0][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[n - 1][max_k][0];
    }

    public int dp(int start, int k, int[] prices) {
        if (start >= prices.length) {
            return 0;
        }
        if (k == 0) {
            return 0;
        }
        if (mem[start][k - 1] != -1) {
            return mem[start][k];
        }
        int result = 0;
        int currentMin = prices[start];
        for (int i = start + 1; i < prices.length; i++) {
            currentMin = Math.min(prices[i], currentMin);
            result = Math.max(result, dp(i + 1, k - 1, prices) + prices[i] - currentMin);
        }
        mem[start][k - 1] = result;
        return result;
    }

    public static void main(String[] args) {
        Stock3 stock2 = new Stock3();
        int[] prices = new int[]{1, 3, 5, 10, 9};
        System.out.println(stock2.dpSolution(prices, 2));
        System.out.println(stock2.dpSolution2(prices));
        System.out.println(stock2.dpSolution3(prices));
    }
}
