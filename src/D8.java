import java.util.*;

public class D8 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    int[] inorder;
    int[] postorder;
    HashMap<Integer,Integer> map = new HashMap <>();

    int pos;
    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        for(int i=0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        pos=postorder.length-1;
        return helper(0,postorder.length-1);
    }

    private TreeNode helper(int left, int right) {
        if(left>right) return null;
        TreeNode root = new TreeNode(postorder[pos]);
        int index = map.get(postorder[pos]);
        pos--;
        root.right =helper(index+1,right);
        root.left = helper(left,index-1);
        return root;
    }
//    int[] preorder;
//    int[] inorder;
//    int pos;
//    HashMap<Integer,Integer> map = new HashMap <>();
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        this.preorder = preorder;
//        this.inorder = inorder;
//        pos = 0;
//        int i =0;
//        for(int one:inorder){
//            map.put(one,i++);
//        }
//        return helper2(0,preorder.length-1);
//    }
//
//    private TreeNode helper2(int left, int right) {
//        if(left>right) return null;
//        TreeNode root = new TreeNode(preorder[pos]);
//        int index = map.get(preorder[pos++]);
//        root.left = helper2(left,index-1);
//        root.right = helper2(index+1,right);
//        return  root;
//    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList <>();
        queue.offer(root);
//        queue.
        while(!queue.isEmpty()){
            int n = queue.size();
            for(int i =0;i<n;i++){
             Node next = queue.poll();
             if(i<n-1){
                 next.next=queue.peek();
             }
                if(next.left!=null) queue.offer(next.left);
                if(next.right!=null) queue.offer(next.right);
            }

        }
        return root;
    }


//       -----------
//    class Solution {
//        public Node connect(Node root) {
//
//            if (root == null) {
//                return root;
//            }
//
//            // Initialize a queue data structure which contains
//            // just the root of the tree
//            Queue<Node> Q = new LinkedList<Node>();
//            Q.add(root);
//
//            // Outer while loop which iterates over
//            // each level
//            while (Q.size() > 0) {
//
//                // Note the size of the queue
//                int size = Q.size();
//
//                // Iterate over all the nodes on the current level
//                for(int i = 0; i < size; i++) {
//
//                    // Pop a node from the front of the queue
//                    Node node = Q.poll();
//
//                    // This check is important. We don't want to
//                    // establish any wrong connections. The queue will
//                    // contain nodes from 2 levels at most at any
//                    // point in time. This check ensures we only
//                    // don't establish next pointers beyond the end
//                    // of a level
//                    if (i < size - 1) {
//                        node.next = Q.peek();
//                    }
//
//                    // Add the children, if any, to the back of
//                    // the queue
//                    if (node.left != null) {
//                        Q.add(node.left);
//                    }
//                    if (node.right != null) {
//                        Q.add(node.right);
//                    }
//                }
//            }
//
//            // Since the tree has now been modified, return the root node
//            return root;
//        }
//    }

}
