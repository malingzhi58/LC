import java.util.*;

public class D13 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        if (root.val < val) return searchBST(root.right, val);
        if (root.val > val) return searchBST(root.left, val);
        return null;
    }

    public TreeNode insertIntoBST2(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);
        if (root.val < val)
            return root.right = insertIntoBST2(root.right, val);
        if (root.val > val)
            return root.left = insertIntoBST2(root.left, val);
        return root;
    }
//    public TreeNode insertIntoBST(TreeNode root, int val) {
//
//    }


//    public static void main(String[] args) {
//        D13 d9 = new D13();
////        {"1","2","3","null","null","4","5"};
//        String ex = "1,2,3,null,null,4,null,null,5,null,null";
//        TreeNode r9=d9.deserialize(ex);
//        TreeNode on= d9.insertIntoBST2(r9,10);
//        String two =d9.serialize(on);
//        System.out.println(two);
////        String s9 = d9.serialize(r9);
////        System.out.println(s9);
//    }

    private TreeNode deserialize(String ex) {
        String[] one = ex.split(",");
        List <String> two = new LinkedList <>(Arrays.asList(one));
        return deserialize(two);
    }

    private TreeNode deserialize(List <String> two) {
        if (two.get(0).equals("null")) {
            two.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(two.get(0)));
        two.remove(0);
        root.left = deserialize(two);
        root.right = deserialize(two);
        return root;
    }

    public String serialize(TreeNode root) {
        return getSerialize(root, "");

    }

    private String getSerialize(TreeNode root, String s) {
        if (root == null) s += "null,";
        else {
            s += root.val + ",";
            s += getSerialize(root.left, s);
            s += getSerialize(root.right, s);
        }
        return s;
    }

    public int successor(TreeNode root) {
        root = root.right;
        if (root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val < key) root.right = deleteNode(root.right, key);
        if (root.val > key) root.left = deleteNode(root.left, key);
        if (root.val == key) {
            if (root.right != null) {
                root.val = successor(root);
                root.right = deleteNode(root.right, root.val);
            } else if (root.left != null) {
                root.val = presuccessor(root);
                root.left = deleteNode(root.left, root.val);
            } else {
                root = null;
            }
        }
        return root;
    }

    private int presuccessor(TreeNode root) {
        root = root.left;
        if (root.right != null) root = root.right;
        return root.val;
    }

    class KthLargest {
        PriorityQueue <Integer> one;
        int limit;

        public KthLargest(int k, int[] nums) {
            one = new PriorityQueue <>(k);
            limit = k;
            for (int num : nums) {
                add(num);
            }
        }

        public int add(int val) {
            if (one.size() < limit) {
                one.add(val);
            } else {
                if (val > one.peek()) {
                    one.poll();
                    one.add(val);
                }
            }
            return one.peek();
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (p.val > q.val) return lowestCommonAncestor(root, q, p);
        if (p.val < root.val && q.val > root.val) return root;
        if (p.equals(root) || q.equals(root)) return root;
        if (q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        if (p.val > root.val) return lowestCommonAncestor(root.right, p, q);
        return root;
    }

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int i, j;
        for (i = 0; i < nums.length; i++) {
            for (j = i + 1; j < nums.length; j++) {
                if (j - i == k && Math.abs(nums[j]) - Math.abs(nums[i]) == t) return true;
            }
        }
        return false;
    }

    public int lengthOfLongestSubstring2(String s) {

        int max = 0;
        for (int j = 0; j < s.length(); j++) {
            HashSet <Character> one = new HashSet <>();
            one.add(s.charAt(j));
            for (int i = j + 1; i < s.length(); i++) {
                if (!one.contains(s.charAt(i))) {
                    one.add(s.charAt(i));
                    if (one.size() > max) max = one.size();
                } else {
                    break;
                }
            }
        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        HashMap <Character, Integer> map = new HashMap <Character, Integer>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // left = Math.max(left,map.get(s.charAt(i)) + 1);
                left = map.get(s.charAt(i)) + 1;
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;

    }
//    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
//        TreeSet<Integer> one = new TreeSet<>();
//
//        for(int i=0;i<nums.length;i++){
//            int ceiling = one.ceiling(nums[i]-t);
//            if((ceiling != null) && (ceiling <= (nums[i] + t))) {
//                return true;
//            }
//            one.add(nums[i]);
//            if(one.size()>k){
//                one.remove(nums[i-k]);
//            }
//        }
//        return false;
//    }
//    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//        // 滑动窗口结合查找表，此时滑动窗口即为查找表本身（控制查找表的大小即可控制窗口大小）
//        TreeSet<Long> set = new TreeSet<>();
//        for (int i = 0; i < nums.length; i++) {
//            // 边添加边查找
//            // 查找表中是否有大于等于 nums[i] - t 且小于等于 nums[i] + t 的值
//            Long ceiling = set.ceiling((long) nums[i] - (long) t);
//            if (ceiling != null && ceiling <= ((long) nums[i] + (long) t)) {
//                return true;
//            }
//            // 添加后，控制查找表（窗口）大小，移除窗口最左边元素
//            set.add((long) nums[i]);
//            if (set.size() == k + 1) {
//                set.remove((long) nums[i - k]);
//            }
//        }
//        return false;
//    }


    public static void main(String[] args) {
        String ex = "abba";
        D13 d13 = new D13();
        int tw = d13.lengthOfLongestSubstring(ex);
        System.out.println(tw);
//        HashMap<Integer,Integer> two = new HashMap <>();
//        two.put(1,0);
//        two.put(1,10);
//        System.out.println(two.get(1));
//    }

    }
//    public boolean isBalanced(TreeNode root) {
//        if(root ==null) return true;
//        if(Math.abs(height(root.left)-height(root.left))>1) return false;
//        return isBalanced(root.left)&&isBalanced(root.right);
//    }
//
//    private int height(TreeNode root) {
//        if(root==null) return -1;
//        return 1+Math.max(height(root.left),height(root.right));
//    }
public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root).balanced;
}
    class Balance{
        boolean balanced;
        int height;
        public Balance(int height,boolean balanced) {
            this.balanced = balanced;
            this.height = height;
        }
    }
    private Balance isBalancedHelper(TreeNode root) {
        if(root==null) return new Balance(-1,true);
        Balance left = isBalancedHelper(root.left);
        if(!left.balanced) return new Balance(-1,false);
        Balance right = isBalancedHelper(root.right);
        if(!right.balanced) return new Balance(-1,false);
        if(Math.abs(left.height-right.height)>1|| left.balanced==false||right.balanced==false)
            return new Balance(Math.max(left.height,right.height),false);
        return new Balance(Math.max(left.height,right.height),true);
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums,0,nums.length-1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int le, int ri) {
        if(le>ri ) return null;
        else{
            int mid = (le+ri)/2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left =sortedArrayToBST(nums,le,mid-1);
            root.right = sortedArrayToBST(nums,mid+1,ri);
            return root;
        }

    }
}
