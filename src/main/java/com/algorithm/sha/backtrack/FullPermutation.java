package com.algorithm.sha.backtrack;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * @Author: 沙志鸿
 * @Date: 2020/8/21 11:49
 */
public class FullPermutation {
    List<List<Integer>> res = Lists.newArrayList();

    /**
     * 主函数，输入一组不重复的数字，返回它们的全排列
     */
    List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    /**
     * 路径：记录在 track 中
     * 选择列表：nums 中不存在于 track 的那些元素
     * 结束条件：nums 中的元素全都在 track 中出现
     */
    void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(Lists.newLinkedList(track));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 排除不合法的选择
            if (track.contains(nums[i])) {
                continue;
            }
            // 做选择
            track.add(nums[i]);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        FullPermutation t = new FullPermutation();
        int[] nums = new int[]{1, 2, 3};
        List res = t.permute(nums);
        System.out.println(JSON.toJSONString(res));
    }
}
