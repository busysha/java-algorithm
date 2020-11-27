package com.algorithm.sha.backtrack;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 八皇后
 * 回溯算法
 *
 * @Author: 沙志鸿
 * @Date: 2020/8/21 14:50
 */
public class Queen {

    private static List<String> charToString(char[][] array) {
        List<String> result = new LinkedList<>();
        for (char[] chars : array) {
            result.add(String.valueOf(chars));
        }
        return result;
    }

    /**
     * 决策树的每一层表示棋盘上的每一行；每个节点可以做出的选择是，在该行的任意一列放置一个皇后。
     *
     * 思考路径：
     * 1. 定位这是backtrack problem
     * 2. 思考决策树的构建过程
     * 3. 思考回溯的模板
     */
    static class Solution1 {
        static List<List<String>> res;

        public static List<List<String>> solveNQueens(int n) {
            if (n <= 0) {
                return null;
            }
            res = new LinkedList<>();
            char[][] board = new char[n][n];
            for (char[] chars : board) {
                Arrays.fill(chars, '.');
            }
            backtrack(board, 0);
            return res;
        }

        /**
         * 路径：board中小于row的那些行都已经成功放置了皇后
         * 可选择列表: 第row行的所有列都是放置Q的选择
         * 结束条件: row超过board的最后一行
         *
         * @param board
         * @param row
         */
        private static void backtrack(char[][] board, int row) {
            if (row == board.length) {
                res.add(charToString(board));
                return;
            }
            int n = board[row].length;
            for (int col = 0; col < n; col++) {
                if (!isValid(board, row, col)) {
                    continue;
                }
                board[row][col] = 'Q';
                backtrack(board, row + 1);
                board[row][col] = '.';
            }
        }

        private static boolean isValid(char[][] board, int row, int col) {
            int rows = board.length;
            // check is valid in col
            for (char[] chars : board) {
                if (chars[col] == 'Q') {
                    return false;
                }
            }
            // check is valid upright
            for (int i = row - 1, j = col + 1; i >= 0 && j < rows; i--, j++) {
                if (board[i][j] == 'Q') {
                    return false;
                }
            }
            // check is valid upleft
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (board[i][j] == 'Q') {
                    return false;
                }
            }
            return true;
        }

    }


    public static void main(String[] args) {
        List<List<String>> res = Solution1.solveNQueens(8);
        System.out.println(JSON.toJSONString(res));
    }

}
