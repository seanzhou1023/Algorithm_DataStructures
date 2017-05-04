package tree;

import specialclass.TreeNode;
import java.util.*;

/**
 * Created by eric on 1/23/17.
 * 1. recursion
 * 2. stack iterative
 * 3. threaded (Morris Traversal) - no extra space iterative
 */
public class T094_BT_Inorder_Traversal {
//    stack
    public List<Integer> inorderTraversalStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null){
            return res;
        }
        Stack<TreeNode> s = new Stack<>();
        TreeNode current = root;
        while(!s.isEmpty() || current!=null){
            if(current!=null){
                s.push(current);
                current = current.left;
            }else{
                current = s.pop();
                res.add(current.val);
                current = current.right;
            }
        }
        return res;
    }
//    morris
    public List<Integer> inorderTraversalMorris(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        TreeNode cur = root;
        while(cur!=null){
            if(cur.left==null){
                res.add(cur.val);
                cur = cur.right;
            }else{
                TreeNode pre = cur.left;
                while(pre.right!=null && pre.right!=cur)
                    pre = pre.right;
                if(pre.right==null){
                    pre.right = cur;
                    cur = cur.left;
                }else{
                    pre.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }
}
