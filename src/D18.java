import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class D18 {
    class Node {
        public int val;
        public List <Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList <Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public Node cloneGraph(Node node) {
        Map <Node, Node> lookup = new HashMap <>();
        return dfs(node, lookup);
    }

    private Node dfs(Node node, Map<Node,Node> lookup) {
        if (node == null) return null;
        if (lookup.containsKey(node)) return lookup.get(node);
        Node clone = new Node(node.val, new ArrayList<>());
        lookup.put(node, clone);
        for (Node n : node.neighbors)clone.neighbors.add(dfs(n,lookup));
        return clone;
    }
    int count = 0;
    public int findTargetSumWays(int[] nums, int S) {
        calculate(nums,0,0,S);
        return count;
    }

    private void calculate(int[] nums, int start, int sum, int s) {
        if(start==nums.length){
         if (sum==s) count++;
        }
        calculate(nums,start+1,sum+=nums[start],s);
        calculate(nums,start+1,sum-=nums[start],s);
    }
    public boolean divisorGame(int N) {
        int[] dp = new int[N+1];
        dp[1]=0;
        dp[2]=1;
        if(N <1) return false;
        for(int i=3;i<=N;i++){
            for(int j=i/2;j>=1;j--){
                if(i%j==0&&dp[i-j]==0){
                    dp[i]=1;
                    break;
                }


            }
        }
        return dp[N]==1;
    }
    public int waysToStep(int n) {
        int[] dp = new int[n+1];
        dp[0]=1;
        dp[1] =1;
        if(n>1)dp[2]=2;
        for(int i=3;i<=n;i++){
            dp[i]=dp[i-1]%1000000007+dp[i-2]%1000000007+dp[i-3]%1000000007;
        }
        return dp[n]%1000000007;
    }

    public int waysToStep2(int n) {
        int dp[] = new int[n+1];
        if(n==1)
            return 1 ;
        if(n==2)
            return 2 ;
        dp[1] = 1 ;
        dp[2] = 2 ;
        dp[3] = 4 ;
        for(int i=4;i<=n;i++){
            if(i-1>=0){
                dp[i] = (dp[i-1]%1000000007) ;
            }
            if(i-2>=0){
                dp[i] = (dp[i]+(dp[i-2]%1000000007))%1000000007 ;
            }
            if(i-3>=0){
                dp[i] = (dp[i]+(dp[i-3]%1000000007))%1000000007 ;
            }
        }
        return dp[n] ;
    }



    public int rob(int[] nums) {
        if(nums==null || nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        if(nums.length==2) return Math.max(nums[0], nums[1]);
        return Math.max(robsub(nums, 0, nums.length-2), robsub(nums, 1, nums.length-1));
    }

    private int robsub(int[] nums, int s, int e) {
        int n = e - s + 1;
        int[] d =new int[n];
        d[0] = nums[s];
        d[1] = Math.max(nums[s], nums[s+1]);

        for(int i=2; i<n; i++) {
            d[i] = Math.max(d[i-2]+nums[s+i], d[i-1]);
        }
        return d[n-1];
    }
    private int robsub2(int[] nums, int s, int e) {
        int len = e-s+1;
        int pre2 =nums[s],pre1 = Math.max(nums[s+1],nums[s]);
        for(int i =s+2;i<=e;i++){
            int cur = Math.max(pre2+nums[i],pre1);
            pre2 = pre1;
            pre1 = cur;

        }
        return pre1;
    }
    public int minCostClimbingStairs(int[] cost) {
        if(cost.length<=0) return 0;
        if(cost.length==1) return cost[0];
        int pre2=cost[0],pre1=Math.min(pre2,cost[1]);
        for(int i =2;i<=cost.length;i++){
            int cur = Math.min(pre2+cost[i],pre1);
            pre2 = pre1;
            pre1 = cur;
        }
        return pre1;
    }
    public int maxSubArray(int[] nums) {
        int sum =nums[0];
        int max =Integer.MIN_VALUE;
        for(int i=1;i<nums.length;i++){
            if(sum>0) sum +=nums[i];
            else sum = nums[i];
            max = Math.max(sum,max);
        }
        return sum;

    }
    public boolean checkSubarraySum2(int[] nums, int k) {
        int sum=0;
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                sum=nums[i];
                sum+= nums[j];
                if(sum%k==0) return true;
            }
        }
        return false;
    }
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer,Integer> result = new HashMap <>();
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(k!=0) {
//                sum%=k;
                if(result.containsKey(sum%k)&&i-result.get(sum%k)>1){
                    return true;
                }
                result.put(sum % k,i);
            }

        }
        return false;
    }
    public static void main(String[] args) {
        D18 d18 = new D18();
//        []
//        6
        int[] sub = {23,2,4,6,7};
        boolean xe = d18.checkSubarraySum(sub,6);
        System.out.println(xe);
    }
//    public int[] sortArray(int[] nums) {
//        for(int i=0;i<nums.length){
//            for(int j=i+1;j<nums.length;j++){
//                if(nums[i]>nums[j]){
//
//                }
//            }
//        }
//    }
    public int[] sortArray2(int[] nums) {

        for(int i=1;i<nums.length;i++){
            int iIndex = nums[i];
            int j =i;
            while (j>0&&nums[j-1]>iIndex){
                nums[j]=nums[j-1];
                j--;
            }
            nums[j] =iIndex;
        }
        return nums;
    }

}
