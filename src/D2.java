import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class D2 {
    public int firstMissingPositive5(int[] nums) {
        int len =nums.length;
        for(int i=0;i<len;i++){
            if(nums[i]>0&&nums[i]<len&&nums[nums[i]]!=nums[i]){
                swap(nums,i,nums[i]-1);
            }
        }
        for(int i=0;i<len;i++){
            if(nums[i]<=0){
                return i;
            }
        }
        return len+1;
    }

    private void swap(int[] nums,int index1,int index2) {
        int temp = nums[index1];
        nums[index1] =nums[index2];
        nums[index2] =temp;
    }
    public List <Integer> findDuplicates(int[] nums) {
        List<Integer> one = new LinkedList <>();
        for(int i=0;i<nums.length;i++){
            int index =  Math.abs(nums[i])-1;
            if(nums[index]<0){
                one.add(Math.abs(nums[i]) );
                continue;
            }
            nums[index] = - Math.abs(nums[index]);
        }
        return one;

    }
    public int reverse(int x) {
        int rev =0;
        while(x!=0){
            int pop = x%10;
            x=x/10;
            if(rev>Integer.MAX_VALUE||rev==Integer.MAX_VALUE/10) break;
            rev = rev*10+pop;
//            INT_MAX
        }
        return rev;
    }
    public int romanToInt(String s) {
        int sum =0;
        int pre =getValue(s.charAt(0));
        for(int i=1;i<s.length();i++){
            int cur=getValue(s.charAt(i));
            if(pre>cur){
                sum+=pre;
            }else {
                sum-=pre;
            }
            pre = cur;
        }
        sum+=pre;
        return sum;
    }

    private int getValue(char charAt) {
        switch(charAt){
            case 'I':return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
    public int romanToInt2(String s) {
        HashMap<String,Integer> map = new HashMap <>();
        int sum =0;
        map.put("I", 1);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("X", 10);
        map.put("XL", 40);
        map.put("L", 50);
        map.put("XC", 90);
        map.put("C", 100);
        map.put("CD", 400);
        map.put("D", 500);
        map.put("CM", 900);
        map.put("M", 1000);
        for(int i=0;i<s.length();){
            if(map.containsKey(s.substring(i,i+2))){
                sum+=map.get(s.substring(i,i+2));
                i+=2;
            }else {
                sum+=map.get(s.substring(i,i+1));
                i++;
            }
        }
        return sum;
    }

    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for(int i=1;i<strs.length;i++){
            while(strs[i].indexOf(prefix)!=0){
                prefix = prefix.substring(0,prefix.length()-1);
                if(prefix.isEmpty()){
                    return "";
                }
            }
        }
        return prefix;
    }
    public String longestCommonPrefix2(String[] strs) {
        String first = strs[0];
        int k=0;
        for(;k<first.length();k++) {
            int count=0;
            for (int i=1; i < strs.length; i++) {
                if (first.charAt(k)==strs[i].charAt(k)){
                    count++;
                }else {
                    break;
                }
            }
            if(count==strs.length){
                continue;
            }
            else if(count<strs.length-1){
//                return first.substring(0,k);
                break;
            }

        }
        if(k==0){
            return "";
        }else {
            return first.substring(0, k);
        }
    }
    private HashMap<Character, Character> mappings;

    // Initialize hash map with mappings. This simply makes the code easier to read.
    public D2() {
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid(String s) {

        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the current character is a closing bracket.
            if (this.mappings.containsKey(c)) {

                // Get the top element of the stack. If the stack is empty, set a dummy value of '#'
                char topElement = stack.empty() ? '#' : stack.pop();

                // If the mapping for this bracket doesn't match the stack's top element, return false.
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                // If it was an opening bracket, push to the stack.
                stack.push(c);
            }
        }

        // If the stack still contains elements, then it is an invalid expression.
        return stack.isEmpty();
    }
    public boolean isValid2(String s) {
        HashMap <Character, Character> one = new HashMap <>();
        one.put('}','{');
        one.put(')','(');
        one.put(']','[');
        Stack<Character> stack = new Stack <>();
        for(int i=0;i<s.length();i++){
            int popElement;
            if(one.containsKey(s.charAt(i))&&stack.isEmpty() != true ){
                 popElement = stack.pop();
                if(one.get(s.charAt(i))!=popElement){
                    return false;
                }
            }else {
                stack.push(s.charAt(i));
            }

        }
        if(!stack.isEmpty()) return false;
        else return true;

    }
    public int strStr(String haystack, String needle) {
        int len = needle.length();
        if(len ==0) return 0;
        if(needle.length()>haystack.length()) return -1;
        for(int i=0;i<haystack.length();i++){
            if(i+needle.length()>haystack.length()) break;
            if(haystack.charAt(i)==needle.charAt(0)){
                if(needle.length()==1) return 0;
                int j=1,count=0;
                while(j<needle.length()){
                    if(i+j<haystack.length()&&haystack.charAt(i+j)!=needle.charAt(j)){
                        break;
                    }else{
                        count++;
                        j++;
                    }
                }
                if(count==needle.length()-1&&needle.length()>1){
                    return i;
                }
//                if(count <needle.length()) return -1;
            }
        }
        return -1;
    }
    public int maxSubArray(int[] nums) {
        int sum =0,max = 0;

        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                for(int j=0;j+i<nums.length;j++){
                    if(nums[i+j]>0){
                        sum+=nums[i+j];
                    }else { break;
                    }
                }
//                sum+=nums[i];
                max = sum>max? sum:max;
            }
        }
        return max;
    }
    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i- 1] + nums[i], nums[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }
    public int maxSubArray3(int[] nums) {
        int sum = nums[0],ans=nums[0];
        for(int i =1;i<nums.length;i++){
            if(sum>0){
                sum+=nums[i];
            }else {
                sum = nums[i];
            }
            ans = sum>ans?sum:ans;
        }
        return ans;
    }
    public int maxSubArray4(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] =nums[0];
        for(int i=1;i<nums.length;i++){
            dp[i] = Math.max(dp[i-1]+nums[i],nums[i]);
        }
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        String[] ex = {"flower","flow","flight"};
        String arg1 = "mississippi";
        String arg2 = "issipi";

        D2 d2 = new D2();

        int result = d2.strStr(arg1,arg2);
        System.out.println(result);
//        String example= ex[0].substring(0,0);
//        System.out.println(example);
//        System.out.println(1);
//        String[] strs = {"leets","leetcode"};
//        int index =strs[1].indexOf(strs[0]);
//        System.out.println(index);
    }
}
