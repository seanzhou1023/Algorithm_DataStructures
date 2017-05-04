package tree;
import specialclass.TreeNode;
import java.util.*;
/**
 * Created by eric on 5/23/16.
 */
public class T102_BT_Level_Order_Traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<List<Integer>>();
        if(root==null){
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            LinkedList<Integer> level = new LinkedList<Integer>();
            int size = q.size();
            for(int i=0;i<size;i++){
                TreeNode current = q.poll();
                level.add(current.val);
                if(current.left!=null){
                    q.offer(current.left);
                }
                if(current.right!=null){
                    q.offer(current.right);
                }
            }
            res.add(level);
        }
        return res;
    }
}
