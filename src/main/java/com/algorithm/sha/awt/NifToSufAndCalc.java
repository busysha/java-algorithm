/**
 * Copyright (c) 2018-2020 ixiancheng.com All Rights Reserved.
 */
package com.algorithm.sha.awt;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 输入一个 中缀表达式 转化为 后缀表达式 然后求值
 */
public class NifToSufAndCalc {

    public static BigDecimal count(String str) {
        if (str == null || "".equals(str) || "=".equals(str)) {
            return BigDecimal.ZERO;
        }
        str = str.replace("=", "");
        char[] chars = str.toCharArray();
        // 获得后缀表达式
        List<String> suffixList = nifToSuf(chars);
        System.out.println(suffixList);
        // 计算后缀表达式
        BigDecimal res = calcSuffixExpression(suffixList);
        return res.setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 中缀表达式转化为后缀表达式
     *
     * @param chars 中缀表达式
     * @return 返回后缀表达式
     */
    public static List<String> nifToSuf(char[] chars) {
        List<String> exp = new ArrayList<>();
        Stack<Character> op = new Stack<>();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < chars.length; i++) {

            // 如果是数字
            if ((i == 0 && chars[i] != '(') || (i != 0 && isDigit(chars[i], chars[i - 1]))) {
                sb.append(chars[i]);
                // 如果是最后一位 或者下一位是字符，数字添加到exp队列,sb清空
                if (i == chars.length - 1 || (i + 1 < chars.length && isSymbol(chars[i + 1]))) {

                    exp.add(sb.toString());
                    sb = new StringBuilder("");
                }
                // 如果是括号
            } else if (isBracket(chars[i])) {
                // 如果是左括号 直接入op栈
                if (chars[i] == '(') {
                    op.push(chars[i]);
                } else {
                    // 右括号
                    // 将op栈出栈 添加到exp直到遇到'(',将这一对 '(' ')' 舍去
                    char temp;
                    while ((temp = op.pop()) != '(') {
                        exp.add(temp + "");
                    }
                }
                // 如果是操作符
            } else if (isOperation(chars[i])) {
                while (true) {
                    // 空的栈直接入栈
                    if (op.isEmpty()) {
                        op.push(chars[i]);
                        break;
                        // 如果栈顶的符号优先级小于 扫描到的符号 入栈
                    } else if (getPriority(op.peek()) < getPriority(chars[i])) {
                        op.push(chars[i]);
                        break;
                        // 栈顶的符号优先级大于等于 扫描到的符号 出栈给exp,并继续扫描栈顶下一个符号
                    } else {
                        exp.add(op.pop() + "");
                    }
                }

            }
        }
        // 将剩余的符号全部入exp
        while (!op.isEmpty()) {
            exp.add(op.pop() + "");
        }

        return exp;
    }

    /**
     * 计算后缀表达式
     *
     * @param suffixExp 输入后缀表达式
     * @return @code{BigDecimal}
     */
    public static BigDecimal calcSuffixExpression(List<String> suffixExp) {
        Stack<BigDecimal> numStack = new Stack<>();
        for (String str : suffixExp) {
            // 如果是操作符
            if (str.length() == 1 && isOperation(str.charAt(0))) {
                BigDecimal num2 = numStack.pop();
                BigDecimal num1 = numStack.pop();
                numStack.push(calcValueOfTwoNum(num1, num2, str));

            } else {
                numStack.push(new BigDecimal(str));
            }
        }
        return numStack.peek();
    }

    public static BigDecimal calcValueOfTwoNum(BigDecimal num1, BigDecimal num2, String op) {
        switch (op) {
            case "+":
                return num1.add(num2);
            case "-":
                return num1.subtract(num2);
            case "*":
                return num1.multiply(num2);
            case "/":
                if (num2.signum() == 0) {
                    throw new RuntimeException("除数不能为0");
                }
                // 除法保留2位小数，四舍五入
                return num1.divide(num2, 2, RoundingMode.HALF_UP);
        }
        throw new RuntimeException("没有此操作符");
    }

    // 获取优先级
    public static int getPriority(char ch) {
        switch (ch) {
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        throw new RuntimeException("不是加减乘除或者(");
    }

    public static boolean isSymbol(char ch) {
        return isOperation(ch) || isBracket(ch);
    }

    public static boolean isOperation(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static boolean isBracket(char ch) {
        return ch == '(' || ch == ')';
    }

    public static boolean isDigit(char ch, char leftBracket) {
        // 如果前一个是左括号 右边的数字可能带有正负号
        if (leftBracket == '(') {
            return ch == '-' || ch == '+' || (ch >= 48 && ch <= 57);
        }
        // 前一个符号不是左括号只能是数字或小数点
        return (ch >= 48 && ch <= 57) || ch == '.';

    }
}
