import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class D12 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class BSTIterator {


        Stack <TreeNode> stack;

        public BSTIterator(TreeNode root) {

            // Stack for the recursion simulation
            this.stack = new Stack <TreeNode>();

            // Remember that the algorithm starts with a call to the helper function
            // with the root node as the input
            this._leftmostInorder(root);
        }

        private void _leftmostInorder(TreeNode root) {

            // For a given node, add all the elements in the leftmost branch of the tree
            // under it to the stack.
            while (root != null) {
                this.stack.push(root);
                root = root.left;
            }
        }

        /**
         * @return the next smallest number
         */
        public int next() {
            // Node at the top of the stack is the next smallest element
            TreeNode topmostNode = this.stack.pop();

            // Need to maintain the invariant. If the node has a right child, call the
            // helper function for the right child
            if (topmostNode.right != null) {
                this._leftmostInorder(topmostNode.right);
            }

            return topmostNode.val;
        }
        public boolean hasNext() {
            return this.stack.size() > 0;
        }

    }
     public TreeNode deserialize(String data) {
        String[] data_String = data.split(",");
        List <String> list = new LinkedList <>(Arrays.asList(data_String));
        return helper2(list);
    }

    private TreeNode helper2(List<String> list) {
        // terminator
        if (list.get(0).equals("null")) {
            list.remove(0);
            return null;
        }
        // process
        TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
        list.remove(0);
        // drill down
        root.left = helper2(list);
        root.right = helper2(list);
        // return states
        return root;
    }
    public static void main(String[] args) {
        D12 d9 = new D12();
//        {"1","2","3","null","null","4","5"};
        String ex = "1,2,3,null,null,4,null,null,5,null,null";
        TreeNode r9=d9.deserialize(ex);
        BSTIterator bstIterator = new BSTIterator(r9);
        while(bstIterator.hasNext()){
            System.out.println(bstIterator.next());
        }
//        String s9 = d9.serialize(r9);
//        System.out.println(s9);
    }

}