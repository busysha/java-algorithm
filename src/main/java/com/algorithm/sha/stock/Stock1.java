/**
 * Copyright (c) 2018-2020 ixiancheng.com All Rights Reserved.
 */
package com.algorithm.sha.stock;

/**
 * 只能完成一笔交易，最大收益
 *
 * @author 沙志鸿
 * @version Stock1.java, v0.1 2020/12/19 沙志鸿 Exp $$
 */
public class Stock1 {
    public int solution(int[] prices) {
        int res = 0;
        int curMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            curMin = Math.min(curMin, prices[i]);
            res = Math.max(res, prices[i] - curMin);
        }
        return res;
    }

    /**
     * 状态机
     * dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
     * dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
     *             = max(dp[i-1][1][1], -prices[i])
     * 解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
     *
     * 现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
     * 可以进行进一步化简去掉所有 k：
     * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
     * dp[i][1] = max(dp[i-1][1], -prices[i])
     *
     * 新状态只和相邻的一个状态有关，其实不用整个 dp 数组，
     * 只需要一个变量储存相邻的那个状态就足够了，这样可以把空间复杂度降到 O(1)
     * @param prices 价格
     * @return int
     */
    public int solution2(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int i = 0; i < prices.length; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, - prices[i]);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        Stock1 stock1 = new Stock1();
        int[] prices = new int[]{1, 3, 5, 10, 9};
        System.out.println(stock1.solution(prices));
        System.out.println(stock1.solution2(prices));
    }
}
