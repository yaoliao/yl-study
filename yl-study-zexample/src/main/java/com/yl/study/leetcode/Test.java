package com.yl.study.leetcode;

/**
 * @author yaoliao
 * @date 2018/10/18
 * @time 23:32
 * @function 功能：
 * @describe 版本描述：
 * @modifyLog 修改日志：
 */
public class Test {


    public static void main(String[] args) {
        ListNode head = new ListNode();
        ListNode second = new ListNode();
        ListNode third = new ListNode();
        ListNode forth = new ListNode();
        head.next = second;
        second.next = third;
        third.next = forth;
        head.data = 1;
        second.data = 2;
        third.data = 3;
        forth.data = 4;
        Test test = new Test();
//        ListNode result = ReverseList(head);
        ListNode result = ReverseList2(head);
        System.out.println(result.data);
    }

    public static ListNode ReverseList2(ListNode head) {

        if (head == null) return null;
        ListNode cur = head;
        ListNode next = head.next;
        ListNode temp = null;
        cur.next = null;
        while (next != null) {
            temp = next;
            next = next.next;
            temp.next = cur;
            cur = temp;
        }
        return cur;
    }

    public static ListNode ReverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode preListNode = null;
        ListNode nowListNode = head;

        while (nowListNode != null) {
            ListNode nextListNode = nowListNode.next;   //保存下一个结点
            nowListNode.next = preListNode;             //当前结点指向前一个结点
            preListNode = nowListNode;                  //前任结点 到现任节点
            nowListNode = nextListNode;                    //现任节点到下一结点
        }
        return preListNode;
    }

    public static class ListNode {
        int data;
        ListNode next;

        public ListNode() {
        }

        ListNode(int x) {
            data = x;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

}

