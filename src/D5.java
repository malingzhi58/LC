import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import junit.*;

public class D5 {
    //    public static void main(String[] args) {
//        List <String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
//        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
////        System.out.println(filtered);
//        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
//// 获取对应的平方数
//        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
//        System.out.println(squaresList);
//    }
//public static void main(String[] args) {
//
//    User x = new User("x",11);
//    User y = new User("y",12);
//    User w = new User("w",10);
//
//    Stream.of(w,y,y,x,x)
//            .sorted((e1,e2)->e1.age>e2.age?1:e1.age==e2.age?0:-1)
//            .forEach(e->System.out.println(e.toString()));
//
//}
//
//    static class User {
//        private String name;
//        private int age;
//        public User(String name, int age) {
//            this.name = name;
//            this.age = age;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }
//
//        @Override
//        public String toString() {
//            return "User{" +
//                    "name='" + name + '\'' +
//                    ", age=" + age +
//                    '}';
//        }
//    }
    public List <Integer> findClosestElements(int[] arr, int k, int x) {
        List <Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(ret, (a, b) -> a == b ? a - b : Math.abs(a - x) - Math.abs(b - x));
//        ret = ret.subList(0, k);
//        Collections.sort(ret);
        return ret;
    }


    public List <Integer> findClosestElements2(int[] arr, int k, int x) {
        List <Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(ret, (a, b) -> a == b ? a - b : Math.abs(a - x) - Math.abs(b - x));
        ret = ret.subList(0, k);
        Collections.sort(ret);
        return ret;
    }

    public List <Integer> findClosestElements3(int[] arr, int k, int x) {
        List <Integer> ret = Arrays.stream(arr).boxed().collect(Collectors.toList());
        int n = ret.size();
        if (x <= ret.get(0)) {
            return ret.subList(0, k);
        } else if (ret.get(n - 1) <= x) {
            return ret.subList(n - k, n);
        } else {
            int index = Collections.binarySearch(ret, x);
            if (index < 0)
                index = -index - 1;
            int low = Math.max(0, index - k - 1), high = Math.min(ret.size() - 1, index + k - 1);
//            int low =
            while (high - low > k - 1) {
                if ((x - ret.get(low)) <= (ret.get(high) - x))
                    high--;
                else if ((x - ret.get(low)) > (ret.get(high) - x))
                    low++;
                else
                    System.out.println("unhandled case: " + low + " " + high);
            }
            return ret.subList(low, high + 1);
        }
    }

    //    public List <Integer> findClosestElements4(int[] arr, int k, int x) {
//        List<Integer> one = Arrays.stream(arr).boxed().collect(Collectors.toList());
//        int len = one.size();
//        if(one.get(0)>x){
//            return one.subList(0,k);
//        }else if(one.get(len-1)<x){
//            return one.subList(len-k,len-1);
//        }
//        int index = Collections.binarySearch(one,x);
//        if(index<0) index= -index-1;
//        int low = Math.max(0,index-k-1), high = Math.min(len-1,index+k-1);
//    }
//    public List <Integer> findClosestElements(int[] arr, int k, int x) {
//        int left =0,right =arr.length-1;
//        int deleteNum = arr.length-k;
//        while(deleteNum>0){
//            if(Math.abs(-arr[left]+x)>Math.abs(arr[right]-x)){
//                left++;
//            }else{
//                right--;
//            }
//        }
////        List<Integer> result =Arrays.stream()
//    }
    public int findMagicIndex(int[] nums) {
        int left = 0, right = nums.length - 1;
        result = binarySearch(nums, left, right);
        return result == -1 ? -1 : nums[result];
    }

    public int result = -1;

    private int binarySearch(int[] nums, int left, int right) {
        int mid = (left + right) >>> 1;
        if (left <= right) {
            if (nums[mid] == mid) {
                result = mid;
                int res = binarySearch(nums, left, mid - 1);
                result = res == -1 ? result : res;
//                right=mid-1;
            } else {
                int res = binarySearch(nums, left, mid - 1);
                if (res == -1) res = binarySearch(nums, mid + 1, right);
                result = res == -1 ? result : res;
            }
        }
        return result;

    }

    //    public int findMagicIndex2(int[] nums) {
//        int left = 0,right = nums.length-1;
//        int result =-1;
//        while(left<right){
//            int mid = (left+right)/2;
//            if(nums[mid]==mid){
//                result= mid;
//                right =mid-1;
//            }else{
//
//            }
//        }
//    }
    public int findString(String[] words, String s) {
        if (words == null || words.length == 0) return -1;
        int left = 0, right = words.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            while (words[mid].length() == 0 && mid < right) {
                mid++;
            }
            if (words[mid].compareTo(s) < 0) {
                left = mid + 1;
            } else if (words[mid].compareTo(s) > 0) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

//    public int[] twoSum(int[] numbers, int target) {
//        int i = 0, j = 1;
//        for (; i < numbers.length; i++) {
//            for (i = i + 1; j < numbers.length; j++) {
//                if (numbers[i] + numbers[j] == target) {
//                    int[] result = new int[2];
//                    result[0] = i;
//                    result[1] = j;
//                    return result;
//                }
//            }
//        }
//        return new {
//            0, 0
//        } ;
//    }

    public int[] kWeakestRows(int[][] mat, int k) {
        int[][] tmp = new int[mat.length][2];
        int[] result = new int[k];
        for (int i = 0; i < mat.length; i++) {
            tmp[i][0] = i;
        }
        for (int i = 0; i < mat.length; i++) {

            tmp[i][1] += count(mat[i]);

        }
        Arrays.sort(tmp,(o1, o2) -> o1[1]-o2[1]);
        for(int i=0;i<k;i++){
            result[i]=tmp[i][0];
        }
        return result;
    }

    private int count(int[] ints) {
        int sum = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] != 0)
                sum += 1;
        }
        return sum;
    }
//    public int[] kWeakestRows(int[][] mat, int k) {


    public static void main(String[] args) {
        //[1,2,3,4,5], k=4, x=3
//        []
//[]
//["at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""]
//        "ta"
        String[] ex = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        int k = 1, x = 9;
        D5 one = new D5();
        int result = one.findString(ex, "ta");
        System.out.println(result);
//        result.forEach(System.out::println);
    }
}
