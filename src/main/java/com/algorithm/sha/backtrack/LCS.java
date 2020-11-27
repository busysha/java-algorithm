/**
 * Copyright (c) 2018-2020 ixiancheng.com All Rights Reserved.
 */
package com.algorithm.sha.backtrack;

import java.util.Arrays;

/**
 * 最长公共子序列
 * 动态优化
 *
 * @author 沙志鸿
 * @version LCS.java, v0.1 2020/9/15 沙志鸿 Exp $$
 */
public class LCS {

    /**
     * 递归
     *
     * @author 沙志鸿
     * @date 2020/09/15
     */
    static class Solution1 {
        static int dp(int i, int j, String str1, String str2) {
            if (i == -1 || j == -1) {
                return 0;
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                return dp(i - 1, j - 1, str1, str2) + 1;
            } else {
                return Math.max(dp(i, j - 1, str1, str2), dp(i - 1, j, str1, str2));
            }
        }

        static int lcs(String str1, String str2) {
            return dp(str1.length() - 1, str2.length() - 1, str1, str2);
        }
    }

    /**
     * dp备忘录优化
     *
     * @author 沙志鸿
     * @date 2020/09/15
     */
    static class Solution2 {
        static int lcs(String str1, String str2) {
            int m = str1.length();
            int n = str2.length();
            int[][] dp = new int[m + 1][n + 1];
            for (int[] num : dp) {
                Arrays.fill(num, 0);
            }
            for (int i = 1; i < m + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            return dp[m][n];
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution1.lcs("babcde", "ace"));
        System.out.println(Solution2.lcs("babcde", "ace"));
    }
}
