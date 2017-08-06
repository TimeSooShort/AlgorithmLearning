package Other;

public class MergeNode {
	
	private static class ListNode {
		ListNode next;
		int val;
		public ListNode(int val) {
			// TODO Auto-generated constructor stub
			this.val = val;
		}
	}

    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode head = (list1.val < list2.val)?list1:list2;
        ListNode cur1 = (head == list1)?list1:list2;
        ListNode cur2 = (head == list1)?list2:list1;
        ListNode pre = null;
        ListNode next = null;
        while(cur1 != null && cur2 != null) {
            if(cur1.val <= cur2.val) {
                pre = cur1;
                cur1 = cur1.next;
            }
            else {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }
}
