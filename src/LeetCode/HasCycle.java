package LeetCode;

/**
 * Given a linked list, determine if it has a cycle in it.
 * Follow up:
 * Can you solve it without using extra space?
 * @author Administrator
 *
 */
public class HasCycle {

	public HasCycle() {
		// TODO Auto-generated constructor stub
	}
	
	class ListNode {
		int val;
		ListNode next;
		public ListNode(int val) {
			this.val = val;
		}
	}
	
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode oneStep = head;
        ListNode twoStep = head.next;
        while (twoStep != null && twoStep.next != null) {
            if (oneStep == twoStep) {
                return true;
            }
            oneStep = oneStep.next;
            twoStep = twoStep.next.next;
        }
        return false;
    }

}
