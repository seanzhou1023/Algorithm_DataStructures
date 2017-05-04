package linkedlist;
import specialclass.ListNode;

/**
 * Created by eric on 1/6/17.
 * idea: partition the nodes into k-group, then reverse one by one
 * test: test reverse function separately
 */
public class T025_Reverse_Nodes_in_kGroup {
    public static ListNode build(){
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        l1.next = l2;l2.next = l3;l3.next = l4;
        return l1;
    }
    public static void reverse(ListNode head, ListNode tail){
        ListNode cur = head, pre = null;
        while(pre!=tail){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }
    public static ListNode reverseKGroup(ListNode head, int k){
        if(head==null || head.next==null || k==1) return head;
        ListNode cur = head;
        int cnt = 0;
        while(cur!=null){
            cur = cur.next;
            cnt++;
        }
        int groups = cnt/k;
        ListNode nextHead = head;
        ListNode newHead = new ListNode(0);
        ListNode newHead_cp = newHead;
        for(int i=0;i<groups;i++){
            ListNode curHead = nextHead;
            ListNode curTail = curHead;
            for(int j=0;j<k-1;j++){
                curTail = curTail.next;
            }
            nextHead = curTail.next;
            reverse(curHead,curTail);
            newHead.next = curTail;
            newHead = curHead;
        }
        if(nextHead!=null) newHead.next = nextHead;
        return newHead_cp.next;
    }

    public static void main(String[] args) {
        ListNode head = build();
        head = reverseKGroup(head,3);
        while(head!=null){
            System.out.println(head.val);
            head = head.next;
        }
    }
}

