import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class D6 {
    public int minArray2(int[] numbers) {
        List<Integer> one = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        int len = one.size();
//        one.
        while(one.get(0)>one.get(len-1)){
            one.add(0,one.remove(len-1));
//            one.add
        }
        return one.get(0);
    }
    public int minArray3(int[] numbers) {
        int l = 0,r = numbers.length-1;
        while(l<r){
            int m = (r+l)>>1;
            if(numbers[m]<numbers[r]) r =m;
            else if (numbers[m]>numbers[r]) l = m+1;
            else l--;
        }
        return numbers[l];
    }
    public int findMin(int[] nums) {
        int l = 0,r = nums.length-1;
        while(l<r){
            int m = (r+l)/2;
            if(nums[m]<nums[r]) r =m;
            else if (nums[m]>nums[r]) l = m+1;
            else r--;
        }
        return nums[l];
    }
    public int[] searchRange2(int[] nums, int target) {
        if(nums.length==0) return new int[]{-1,-1};
        int le =0,ri =nums.length-1;
        while(le<=ri){
            int mid = (le+ri)/2;
            if(nums[mid]<target&&le<nums.length-1) le =mid+1;
            else if(nums[mid]>target) ri = mid-1;
            else {
                while(nums[le]!=target&&le<nums.length-1){le++;}
                while(nums[ri]!=target&&ri>0){ri--;}
                if(nums[le]==target&&nums[ri]==target) return new int[]{le,ri};
            }
        }
        return nums[le]==target&&nums[ri]==target? new int[]{le,ri}:new int[]{-1,-1};
    }
    public int[] searchRange(int[] nums, int target) {
        if(nums.length==0) return new int[]{-1,-1};
        int le =0,ri =nums.length-1, mid =(le+ri)/2;
        while(le<=ri){
            mid = (le+ri)/2;
            if(nums[mid]<target) le =mid+1;
            else if(nums[mid]>target) ri = mid-1;
            else break;

        }
        if(nums[mid]==target)
        {
            for(int i= mid;i>0;i--){
                if(nums[i]!=target) {le=i+1;
                    break;
                }
            }
        }
        if(nums[le]==target) {
            for(int i= le;i<nums.length;i++){
                if(nums[i]!=target) {ri=i-1;
                return new int[]{le,ri};
                }
            }
        }
        return new int[]{-1,-1};

    }
//    public List<Integer> preorderTraversal(TreeNode root) {
//        LinkedList<Integer> one = new LinkedList <>();
//        preorderTraversal(root,one);
//        return  one;
//    }
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
    TreeNode(int x) { val = x; }
 }
//    private void preorderTraversal(TreeNode node, List<Integer> list){
//        if(node!=null)
//    }
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> one = new LinkedList<>();
        inorderTraversal(root,one);
        return one;
    }
    public void inorderTraversal(TreeNode root,List<Integer> one) {
        if(root!=null){

            inorderTraversal(root.left);
            one.add(root.val);
            inorderTraversal(root.right);
        }

    }

    public static void main(String[] args) {

        int[] tmp = new int[]{5,7,7,8,8,10};
        D6 one =new D6();
        int[] re =one.searchRange(tmp,8);
        System.out.println(re[0]);
    }
}
