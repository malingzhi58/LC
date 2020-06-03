import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class D10 {
    public class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;

        TreeNode(String x) {
            val = x;
        }
    }

    public String serialize(TreeNode root) {
        return helper1(root, new StringBuilder());
    }

    private String helper1(TreeNode root, StringBuilder sb) {
        // terminator
        if (root == null) {
            sb.append("#,");
        } else {
            // process
            sb.append(root.val).append(",");
            // drill down
            helper1(root.left, sb);
            helper1(root.right, sb);
        }
        // return states
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_String = data.split(",");
        List <String> list = new LinkedList <>(Arrays.asList(data_String));
        return helper2(list);
    }

    private TreeNode helper2(List<String> list) {
        // terminator
        if (list.get(0).equals("#")) {
            list.remove(0);
            return null;
        }
        // process
        TreeNode root = new TreeNode(String.valueOf(list.get(0)));
        list.remove(0);
        // drill down
        root.left = helper2(list);
        root.right = helper2(list);
        // return states
        return root;
    }
    public static void main(String[] args) {
        D10 d9 = new D10();
//        {"1","2","3","null","null","4","5"};
        String ex = "1,2,3,null,null,4,null,null,5,null,null";
        TreeNode r9=d9.deserialize(ex);
        String s9 = d9.serialize(r9);
        System.out.println(s9);
    }
}
