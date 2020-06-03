import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class D9 {
    public class TreeNode2 {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode2(int x) {
            val = x;
        }
    }
    public class TreeNode {
        String val;
        TreeNode left;
        TreeNode right;

        TreeNode(String x) {
            val = x;
        }
    }
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) {
            return root;
        }
        Stack<TreeNode> stack = new Stack <>();
        //中序遍历判断两个节点是否在左子树
        TreeNode cur = root.left;
        boolean pLeft = false;
        boolean qLeft = false;
        while (cur != null || !stack.isEmpty()) {
            // 节点不为空一直压栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; // 考虑左子树
            }
            // 节点为空，就出栈
            cur = stack.pop();
            // 判断是否等于 p 节点
            if (cur == p) {
                pLeft = true;
            }
            // 判断是否等于 q 节点
            if (cur == q) {
                qLeft = true;
            }

            if(pLeft && qLeft){
                break;
            }
            // 考虑右子树
            cur = cur.right;
        }

        //两个节点都在左子树
        if (pLeft && qLeft) {
            return lowestCommonAncestor(root.left, p, q);
            //两个节点都在右子树
        } else if (!pLeft && !qLeft) {
            return lowestCommonAncestor(root.right, p, q);
        }
        //一左一右
        return root;
    }
//    public TreeNode lowestCommonAncestor4(TreeNode2 root, TreeNode2 p, TreeNode2 q) {
//        if(p.val>q.val) return lowestCommonAncestor(root,q,p);
//        if(q.val<root.val) return lowestCommonAncestor(root.left,q,p);
//        if(p.val>root.val) return lowestCommonAncestor(root.right,q,p);
//        return root;
//    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null||root==p|root==q) return root;
        TreeNode leftLCA = lowestCommonAncestor(root.left,p,q);
        TreeNode rightLCA = lowestCommonAncestor(root.right,p,q);
        if(leftLCA==null) return rightLCA;
        if(rightLCA==null) return leftLCA;
        return root;
//        if(leftLCA==null&&rightLCA==null) return null;
//        if(leftLCA!=null&&rightLCA==null) return leftLCA;
//        if(leftLCA==null&&rightLCA!=null) return rightLCA;
//        if(leftLCA!=null&&rightLCA!=null) return root;
    }
//    public String serialize(TreeNode root) {
//
//        return serialize(root,"");
//    }
//
//    private String serialize(TreeNode root, String s) {
//        if(root==null) s+=toString().valueOf("null");
//        else {
//            s += s.valueOf(root.val);
//            serialize(root.left,s);
//            serialize(root.left,s);
//        }
//return s;
//
//    }
//    public TreeNode deserialize(String data) {
//        String[] dataArray = data.split(",");
//        LinkedList<String> result = new LinkedList <>(Arrays.asList(dataArray));
//        return deserialize(result);
//    }
//
//    private TreeNode deserialize(LinkedList<String> result) {
//        if(result.get(0)==null) return null;
//        TreeNode root = new TreeNode(result.get(0));
//        result.remove();
//        root.left=deserialize(result);
//        root.right=deserialize(result);
//        return root;
//    }
//    public int[] serialize2(TreeNode root) {
//        int[] result = new int[];
//    }
public String serialize(TreeNode root) {
    return serialize(root,new StringBuilder());
}
//    public String serialize(TreeNode root,String str) {
//        if(root==null)  str+="null,";
//        else{str+=str.valueOf(root.val)+",";
//        serialize(root.left,str);
//        serialize(root.right,str);
//        }
//        return str;
//
//    }
    public String serialize(TreeNode root,StringBuilder str) {
        if(root==null)  str.append("null,");
        else{str.append(root.val).append(",");
            serialize(root.left,str);
            serialize(root.right,str);
        }
        return str.toString();

    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        LinkedList<String> result = new LinkedList <>(Arrays.asList(dataArray));
        return deserialize(result);
    }

    private TreeNode deserialize(LinkedList<String> result) {

        if(result.get(0).equals("null")) {
            result.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(String.valueOf(result.get(0)));
        result.remove(0);
        root.left=deserialize(result);
        root.right=deserialize(result);
        return root;
    }

    public static void main(String[] args) {
        D9 d9 = new D9();
//        {"1","2","3","null","null","4","5"};
        String ex = "1,2,3,null,null,4,null,null,5,null,null";
        TreeNode r9=d9.deserialize(ex);
        String s9 = d9.serialize(r9);
        System.out.println(s9);
    }
}
