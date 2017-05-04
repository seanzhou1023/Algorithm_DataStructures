package tree;

import specialclass.TreeNode;
import java.util.*;

/**
 * Created by eric on 8/13/16.
 * 1. recursion
 * 2. stack iterative
 * 3. threaded binary tree - no extra space iterative
 */
public class T144_BT_Preorder_Traversal {
//    2. one stack method
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode top = stack.pop();
            res.add(top.val);
            if(top.right!=null) stack.push(top.right);
            if(top.left!=null) stack.push(top.left);
        }
        return res;
    }
}
