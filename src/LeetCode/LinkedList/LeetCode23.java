package LeetCode.LinkedList;

import java.util.Arrays;

/**
 * Created by Administrator on 2017/12/26.
 */
public class LeetCode23 {

    public ListNode mergeKLists(ListNode[] lists){
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        if (lists.length == 2) return mergerTwo(lists[0], lists[1]);
        return mergerTwo(mergeKLists(Arrays.copyOfRange(lists,0,lists.length/2)),
                mergeKLists(Arrays.copyOfRange(lists, lists.length/2, lists.length)));
    }

    private ListNode mergerTwo(ListNode node1, ListNode node2){
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while(node1 != null && node2 != null){
            if (node1.val < node2.val){
                cur.next = node1;
                node1 = node1.next;
            }else {
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        if (node1 != null) cur.next = node1;
        if (node2 != null) cur.next = node2;
        return head.next;
    }
}
