package com.canthny.common.base.leetCode;

import com.alibaba.fastjson.JSONObject;

/**
 * Description： TODO
 * Created By tanghao on 2020/7/10
 */
class Solution2 {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode beforeNode = null;
        int currentCount = 0;
        while(l1 != null || l2 !=null){
            int val = 0;
            if(null != l1){
                val += l1.getVal();
                l1 = l1.getNext();
            }
            if(null != l2){
                val +=l2.getVal();
                l2 = l2.getNext();
            }
            val += currentCount;
            currentCount = val >= 10 ? 1 : 0;
            ListNode temp = new ListNode(val >= 10 ? val - 10 : val);
            if(head == null){
                head = temp;
                beforeNode = head;
            }else{
                beforeNode.setNext(temp);
                beforeNode = temp;
            }
        }
        if(currentCount == 1){
            ListNode temp = new ListNode(currentCount);
            beforeNode.setNext(temp);
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(9);
        ListNode a3 = new ListNode(2);
        a1.setNext(a2);
        a2.setNext(a3);

        ListNode b1 = new ListNode(9);
        ListNode b2 = new ListNode(6);
        ListNode b3 = new ListNode(7);
        b1.setNext(b2);
        b2.setNext(b3);

        System.out.println(JSONObject.toJSONString(addTwoNumbers(a1,b1)));
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }
}
