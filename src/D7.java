import java.util.*;

public class D7 {
    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public List <List <Integer>> levelOrder(TreeNode root) {
        List result = new ArrayList();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            LinkedList<Integer> level = new LinkedList <>();
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode one = queue.poll();
                level.add(one.val);
                if (one.left != null) {
                    queue.offer(one.left);
                }
                if (one.right != null) {
                    queue.offer(one.right);
                }
            }
            result.add(level);

        }
        return result;

    }

    public List <List <Integer>> levelOrder2(TreeNode root) {
        List result = new ArrayList();

        if (root == null) {
            return result;
        }

        Queue <TreeNode> queue = new LinkedList <TreeNode>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            ArrayList <Integer> level = new ArrayList <Integer>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }

        return result;
    }
    public int maxDepth(TreeNode root) {

        if(root == null)
            return 0;

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
    public boolean isSymmetric(TreeNode root) {
        if(root==null) return true;

        return isSymmetric(root.left,root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if(left==null&&right==null) return true;
        if(left==null||right==null) return false;
        return left.val==right.val&&isSymmetric(left.left,left.right)&&isSymmetric(right.left,right.right);
    }
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null) return sum==0;
        sum -=root.val;
        if(root.left==null&&root.right==null) return sum==0;
        return hasPathSum(root.left,sum)||hasPathSum(root.right,sum);
    }



    int post_idx;
    int[] postorder;
    int[] inorder;
    HashMap <Integer, Integer> idx_map = new HashMap<Integer, Integer>();

    public TreeNode helper(int in_left, int in_right) {
        // if there is no elements to construct subtrees
        if (in_left > in_right)
            return null;

        // pick up post_idx element as a root
        int root_val = postorder[post_idx];
        TreeNode root = new TreeNode(root_val);

        // root splits inorder list
        // into left and right subtrees
        int index = idx_map.get(root_val);

        // recursion
        post_idx--;
        // build right subtree
        root.right = helper(index + 1, in_right);
        // build left subtree
        root.left = helper(in_left, index - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        this.inorder = inorder;
        // start from the last postorder element
        post_idx = postorder.length - 1;

        // build a hashmap value -> its index
        int idx = 0;
        for (Integer val : inorder)
            idx_map.put(val, idx++);
        return helper(0, inorder.length - 1);
    }







}
