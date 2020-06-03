import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day1 {
//    public int removeDuplicates(int[] nums) {
//        if (nums.length == 0) return 0;
//        int i = 0;
//        for (int j = 1; j < nums.length; j++) {
//            if (nums[j] != nums[i]) {
//                i++;
//                nums[i] = nums[j];
//            }
//        }
//        return i + 1;
//    }

    // can't hanle 4 consecutive items
//    public int removeDuplicates(int[] nums) {
//        int i =0,count =0;
//        for(int j=1;j<nums.length;j++){
//            if(nums[j]!=nums[i]){
//                i++;
//                nums[i] =nums[j];
//                //handle the j = i and have 2 same
//            }else if(count<1){
//                i++;
//                nums[i] =nums[j];
//                count++;
//            }else if(count==1){
//                count=0;
//            }
//            else {
//
//            }
//        }
//        return i+1;
//    }

    public int removeDuplicates(int[] nums) {
        int i =0,count =0;
        for(int j=1;j<nums.length;j++){
            if(nums[j]!=nums[i]){
                i++;
                nums[i] =nums[j];
                count=0;
                //handle the j = i and have 2 same
            }else {
                if(nums[j]==nums[i]){
                    count++;
                    if(count>1){
                        count=0;
                    }else{
                        i++;

                    }

                }
            }
        }
        return i+1;
    }

//    public int[] remElement(int[] arr, int index) {
//        for (int i = index + 1; i < arr.length; i++) {
//            arr[i - 1] = arr[i];
//        }
//        return arr;
//    }
//
//    public int removeDuplicates(int[] nums) {
//        int i = 1, count = 1, length = nums.length;
//        while (i < length) {
//            if (nums[i] == nums[i - 1]) {
//                count++;
//                if (count > 2) {
//                    this.remElement(nums, i);
//                    i--;
//                    length--;
//                }
//            } else {
//                count = 1;
//            }
//            i++;
//        }
//        return length;
//    }


    public void rotate(int[] nums, int k) {
        int len = nums.length;
        k = k%len;
        int count =0;
        for(int start =0;count<len;start++){

            int curr = start;
            int pre=nums[curr];
            do{
                int next = (curr+k)%len;
                int tmp = nums[next];
                nums[next] = pre;
                pre = tmp;
                curr = next;
                count++;
            }
            while (curr!=start);

        }
    }
    public int firstMissingPositive(int[] nums) {
        int min=nums[0];
        for(int i =0;i<nums.length;i++){
            if(nums[i]>0&&nums[i]<min){
                min = nums[i];
            }
        }
        if(min-1>0){
            return min-1;
        }else {
            for(int i=min+1;;i++){
                for(int j=0;j<nums.length;j++){
                    if(nums[j]<=i){
                        continue;
                    }
                }
                return i;
            }
        }
    }
    public int firstMissingPositive2(int[] nums) {
        int len = nums.length;
        int[] ex =new int[len+1];
        for(int i=0;i<len;i++){
            if(nums[i]>0&&nums[i]<len){
            ex[nums[i]]=nums[i];}
        }
        if(ex[1]!=1){
            return 1;
        }
        for(int i=1;i<=len;i++){
            if(ex[i]!=i){
                return i;
            }
        }
        return len+1;
    }
    public int firstMissingPositive3(int[] nums) {
        int len = nums.length;
        for(int i=0;i<len;i++){
            if(nums[i]>=1&&nums[i]<=len&&nums[nums[i]-1]!=nums[i]){
                swap(nums,i,nums[i]-1);
            }
        }
        for(int i=0;i<len;i++){
            if(nums[i]!=i+1){
                return i+1;
            }
        }
        return len+1;
    }

    private void swap(int[] nums,int index1,int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
    public int firstMissingPositive4(int[] nums) {
        int len = nums.length;

        Set <Integer> hashSet = new HashSet <>();
        for (int num : nums) {
            hashSet.add(num);
        }

        for (int i = 1; i <= len ; i++) {
            if (!hashSet.contains(i)){
                return i;
            }
        }

        return len + 1;
    }
    public int firstMissingPositive5(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);

        for (int i = 1; i <= len; i++) {
            int res = binarySearch(nums, i);
            if (res == -1) {
                return i;
            }
        }
        return len + 1;
    }

    private int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        //{7,8,9,10,11,12}
        int[] one = {3,4,-1,1};
        int ex = day1.firstMissingPositive3(one);
        System.out.println(ex);
        for(int i =0;i<one.length;i++){
            System.out.println(one[i]);
        }

    }
}
