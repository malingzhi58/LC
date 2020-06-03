import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class D3 {
    public int maxProfit(int[] prices) {
        if(prices.length==0) return 0;
        int buyIndex =0,buy=prices[buyIndex];
        int sellIndex =prices.length-1,sell=prices[sellIndex];
        for(int i=1;i<prices.length;i++){
            if(prices[i]<buy&&i>sellIndex) {buy =prices[i];buyIndex=i;}
            if(prices[i]>sell && i>buyIndex) {sell=prices[i];sellIndex=i;}
        }
        if(sell>buy) return sell-buy;
        else return 0;
    }
    public int maxProfit2(int[] prices) {
        int profit,max=0;
        for(int i=0;i<prices.length;i++){
            for(int j=i;j<prices.length;j++){
                profit=prices[j]-prices[i];
                if(max<profit) max = profit;
            }
        }
        return max;
    }

    /**
     * this is clear in logic
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfic =0;
        for(int i=0;i<prices.length;i++){
            if(prices[i]<minPrice) minPrice=prices[i];
            else if(prices[i]-minPrice>maxProfic) maxProfic=prices[i]-minPrice;
        }
        return maxProfic;

    }
    public int searchInsert(int[] nums, int target) {
        int result=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==target) {result=i; break;}
            else if(nums[0]>target) {result= 0;break;}
            else if(nums[i]>target) {result= i-1;break;}
            else {continue;}
        }
        return result;
    }
    public int searchInsert2(int[] nums, int target) {
        int left = 0,right =nums.length-1;
        while(left<right){
            int mid = (left+right)/2;
            if(nums[mid]<target){
                left =mid+1;
            }else {
                right = mid-1;
            }
        }
        return target<=nums[left]?left:left+1;
    }
    public int firstBadVersion(int n) {
        int left = 0,right =n;
        while(left<right){
            int mid =(left+right)/2;
            if(isBadVersion(mid)!=true){
                right=mid-1;
            }else {
                left=mid+1;
            }
        }
        return isBadVersion(left)?left:left+1;
    }
    public int firstBadVersion2(int n) {
        int l = 1;
        int r = n;
        int res = 0;
        while(l <= r){
            int mid = l + ((r-l)>>1);
            boolean flag = isBadVersion(mid);
            if(flag){
                res = mid;
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        return res;
    }

    private boolean isBadVersion(int n) {
        return true;
    }
    public char nextGreatestLetter(char[] letters, char target) {
        int le =0,ri = letters.length-1;
        while(le<=ri){
            int mid = (ri-le)/2+le;
            if(letters[mid]<=target) le=mid+1;
            else ri=mid-1;
        }
        return letters[le% letters.length];
        //% letters.length
    }
    public List <Integer> findClosestElements(int[] arr, int k, int x) {
        if(x<arr[0]){
            List<Integer> result = new LinkedList <>();
            for(int i=0;i<k;i++){
                result.add(arr[i]);
            }
            return result;
        }else if(x>arr[arr.length-1]){
            List<Integer> result = new LinkedList <>();
            for(int i=arr.length-1;i>arr.length-1-k;i--){
                result.add(arr[i]);
                Collections.sort(result);
            }
            return result;
        }
        int left=0,right =arr.length-1;
        while(right-left>k-1){
            if(x-arr[left]>arr[right]-x){
                left++;
            }else if(x-arr[left]<=arr[right]-x){
                right--;
            }
        }
        List<Integer> result = new LinkedList <>();
        for(int i=left;i<=right;i++){
            result.add(arr[i]);
            Collections.sort(result);
        }
        return result;

    }
    public static void main(String[] args) {
        D3 example = new D3();
//        char[] ex = {'c','f','j'};
        int[] ex={1,2,3,4,5};
        List result =example.findClosestElements(ex,4,3);
        System.out.println(result);
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(3%3);
    }
}
