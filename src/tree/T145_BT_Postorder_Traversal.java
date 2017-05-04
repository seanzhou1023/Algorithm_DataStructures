package tree;

import specialclass.TreeNode;
import java.util.*;

/**
 * Created by eric on 12/17/16.
 * 1. recursion
 * 2. two stack iterative
 * 3. one stack iterative
 * 4. threaded binary tree
 */
public class T145_BT_Postorder_Traversal {
    public static List<Integer> postorderTraversal(TreeNode root){//two stack
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            if(cur.left!=null) stack.add(cur.left);
            if(cur.right!=null) stack.add(cur.right);
            res.add(0,cur.val);
        }
        return res;
    }
    public static List<Integer> postorderTraversal1(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if(root==null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root, pre;
        while(cur!=null || !stack.isEmpty()){
            System.out.println(stack);
            System.out.println(res);
            if(stack.isEmpty()) stack.push(cur);
            while(cur.left!=null){
                stack.push(cur.left);
                cur = cur.left;
            }
            if(cur.right==null){//a leaf
                pre = stack.pop();
                res.add(pre.val);
                cur = null;
                if(stack.isEmpty()) return res;
                else{
                    TreeNode tmp = stack.peek();
                    if(tmp.left == pre){
                        if(tmp.right!=null){
                            stack.push(tmp.right);
                            cur = tmp.right;
                        }else cur = null;
                    }else if(tmp.right == pre){
                        pre = stack.pop();
                        res.add(pre.val);
                        cur = null;
                    }
                }
            }else{
                stack.add(cur.right);
                cur = cur.right;
            }
        }
        return res;
    }
}
