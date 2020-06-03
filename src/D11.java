import java.util.*;

public class D11 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    public String serialize(TreeNode root) {
        return getSerialize(root,"");

    }

    private String getSerialize(TreeNode root, String s) {
        if(root==null) s+="null,";
        else {
            s+=root.val+",";
            s+=getSerialize(root.left,s);
            s+=getSerialize(root.right,s);
        }
        return s;
    }
    public boolean isValidBST(TreeNode root) {

        return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long minValue, long maxValue) {
        if(root==null) return true;
        if(root.val<=minValue||root.val>=maxValue) return false;
        return isValidBST(root.left,minValue,root.val)&&isValidBST(root.right,root.val,maxValue);
    }
    static class BSTIterator  {
        LinkedList<Integer> inorderList;
        int index;
        public BSTIterator(TreeNode root) {
            inorderList = new LinkedList <>();
            this.index = 0;
            this.inorder(root);
        }
        public void inorder(TreeNode root){
            if(root==null) return ;
            inorder(root.left);
            inorderList.add(root.val);
            inorder(root.right);
        }
        /** @return the next smallest number */
        public int next() {
            int result =inorderList.get(index++);
            return result;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return index<inorderList.size();
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
        D11 d9 = new D11();
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
