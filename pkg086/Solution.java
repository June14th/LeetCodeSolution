package pkg086;

/**
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 *
 * 你应当保留两个分区中每个节点的初始相对位置。
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
public class Solution {
    public static void main(String[] args) {

    }
    public static ListNode partition(ListNode head, int x) {
        if(head == null) return head;
        ListNode head0 = new ListNode(0);
        head0.next = head;
        ListNode pre = head0;

        while(pre.next!=null && pre.next.val < x){
            pre = pre.next;
        }
        ListNode cur = pre;
        while(cur!=null && cur.next!=null){
            if(cur.next.val < x){
                ListNode mid = cur.next;
                cur.next = mid.next;
                mid.next = pre.next;
                pre.next = mid;
                pre = pre.next;
            }else{
                cur = cur.next;
            }
        }
        return head0.next;
    }
}
