/**
 * Copyright (c) 2018-2020 ixiancheng.com All Rights Reserved.
 */
package com.algorithm.sha.list;

/**
 * @author 沙志鸿
 * @version ListTest.java, v0.1 2020/12/21 沙志鸿 Exp $$
 */
public class ListTest {
    public static class ListNode {
        private ListNode next;
        private Integer data;

        public ListNode(ListNode next, Integer data) {
            this.next = next;
            this.data = data;
        }
    }

    /**
     * 反转
     *
     * @param node 节点
     * @return {@link ListNode}
     */
    public ListNode reverse(ListNode node) {
        ListNode pre, next, curr;
        pre = null;
        next = node;
        curr = node;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 反转
     *
     * @param a 一个
     * @param b b
     * @return {@link ListNode}
     */
    public ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, next, curr;
        pre = null;
        next = a;
        curr = a;
        while (curr != b) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * k 个一组反转链表
     *
     * @param head 头
     * @param k    k
     * @return {@link ListNode}
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode reverseNode = reverse(a, b);
        head.next = reverseKGroup(b, k);
        return reverseNode;
    }

    public void printList(ListNode node) {
        ListNode cur = node;
        while (cur != null) {
            System.out.println(cur.data);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        ListTest listTest = new ListTest();
        ListNode node3 = new ListNode(null, 3);
        ListNode node2 = new ListNode(node3, 2);
        ListNode node1 = new ListNode(node2, 1);
        listTest.printList(node1);
        ListNode head = listTest.reverse(node1);
        System.out.println("翻转");
        listTest.printList(head);
        System.out.println("翻转");
        ListNode node4 = new ListNode(null, 4);
        node3 = new ListNode(node4, 3);
        node2 = new ListNode(node3, 2);
        node1 = new ListNode(node2, 1);
        ListNode head2 = listTest.reverseKGroup(node1, 2);
        listTest.printList(head2);
    }
}
