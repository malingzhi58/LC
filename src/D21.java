import java.util.*;

public class D21 {
//    public List <Integer> spiralOrder(int[][] matrix) {
//        int rows = matrix.length;
//        int cols = matrix[0].length;
//        int k =rows;
//        int i=0,j=0;
//        List<int[]> res = new LinkedList <>();
//        for(;k>rows-1;k--){
//            res.add(new int[]{i,j});
//            for(;j<k;){
//                res.add(new int[]{i,++j});
//            }
//        }
//        return res;
//    }


    public List <Integer> spiralOrder(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0) return ans;
        int R = matrix.length, C = matrix[0].length;
        boolean[][] seen = new boolean[R][C];
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        int r = 0, c = 0, di = 0;
        for (int i = 0; i < R * C; i++) {
            ans.add(matrix[r][c]);
            seen[r][c] = true;
            //用一个新的来计算++后的位置，超过范围就不用
            int cr = r + dr[di];
            int cc = c + dc[di];
            //通过seen来转向
            if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {
                r = cr;
                c = cc;
            } else {
                di = (di + 1) % 4;
                r += dr[di];
                c += dc[di];
            }
        }
        return ans;
    }

    public List <Integer> spiralOrder2(int[][] matrix) {
        List ans = new ArrayList();
        if (matrix.length == 0)
            return ans;
        int r1 = 0, r2 = matrix.length - 1;
        int c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int c = c1; c <= c2; c++) ans.add(matrix[r1][c]);
            for (int r = r1 + 1; r <= r2; r++) ans.add(matrix[r][c2]);
            if (r1 < r2 && c1 < c2) {
                for (int c = c2 - 1; c > c1; c--) ans.add(matrix[r2][c]);
                for (int r = r2; r > r1; r--) ans.add(matrix[r][c1]);
            }
            r1++;
            r2--;
            c1++;
            c2--;
        }
        return ans;
    }

    public List <List <Integer>> generate(int numRows) {
        List <List <Integer>> res = new LinkedList <>();
        int[][] matrix = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            List <Integer> subList = new LinkedList <>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || i == j) {
                    matrix[i][j] = 1;
                } else if (j < i && i > 1 && j > 1) {
                    matrix[i][j] = matrix[i - 1][j - 1] + matrix[i - 1][j];
                }
                subList.add(matrix[i][j]);
            }
            res.add(subList);
        }
        return res;
    }

    public List <List <Integer>> generate2(int numRows) {
        List <List <Integer>> triangle = new ArrayList <List <Integer>>();
        triangle.add(new ArrayList <>());
        triangle.get(0).add(1);
        for (int i = 1; i < numRows; i++) {
            List <Integer> tmp = new ArrayList <>();
            List <Integer> pre = triangle.get(i - 1);
            tmp.add(1);
            for (int j = 1; j < i; j++) {
                tmp.add(pre.get(j - 1) + pre.get(j));
            }
            tmp.add(1);
            triangle.add(tmp);
        }
        return triangle;

    }

    public int singleNumber2(int[] nums) {
        HashMap <Integer, Integer> hashmap = new HashMap <>();
        for (int num : nums)
            hashmap.put(num, hashmap.getOrDefault(num, 0) + 1);

        for (int k : hashmap.keySet())
            if (hashmap.get(k) == 1) return k;
        for (Map.Entry <Integer, Integer> entry : hashmap.entrySet()) {
            entry.getValue();
        }
        return -1;
    }

    /*
             // first appearence:
            // add num to seen_once
            // don't add to seen_twice because of presence in seen_once

            // second appearance:
            // remove num from seen_once
            // add num to seen_twice

            // third appearance:
            // don't add to seen_once because of presence in seen_twice
            // remove num from seen_twice
//            num = Integer.toBinaryString(num);
     */
    public int singleNumber(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }

        return seenOnce;
    }

    public int[] singleNumber3(int[] nums) {
        Map <Integer, Integer> hashmap = new HashMap();
        for (int n : nums)
            hashmap.put(n, hashmap.getOrDefault(n, 0) + 1);

        int[] output = new int[2];
        int idx = 0;
        for (Map.Entry <Integer, Integer> item : hashmap.entrySet())
            if (item.getValue() == 1) output[idx++] = item.getKey();

        return output;
    }

//    作者：LeetCode
//    链接：https://leetcode-cn.com/problems/single-number-iii/solution/zhi-chu-xian-yi-ci-de-shu-zi-iii-by-leetcode/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public int strStr(String haystack, String needle) {
        int L = needle.length();
        int N = haystack.length();
        int pN = 0;
        while (pN < N - L + 1) {
            if (haystack.charAt(pN) != needle.charAt(0)) pN++;
            int pL = 0;
            while (pL < L && pN < N && needle.charAt(pL) == haystack.charAt(pN)) {
                pL++;
                pN++;
            }
            if (pL == L) return pN - L;
            else pN -= pL + 1;
        }
        return -1;
    }

    public int strStr2(String haystack, String needle) {
        int L = needle.length(), n = haystack.length();
        if (L == 0) return 0;

        int pn = 0;
        while (pn < n - L + 1) {
            // find the position of the first needle character
            // in the haystack string
            while (pn < n - L + 1 && haystack.charAt(pn) != needle.charAt(0)) ++pn;

            // compute the max match string
            int currLen = 0, pL = 0;
            while (currLen < L && pn < n && haystack.charAt(pn) == needle.charAt(currLen)) {
                ++pn;
//                ++pL;
                ++currLen;
            }

            // if the whole needle string is found,
            // return its start position
            if (currLen == L) return pn - L;

            // otherwise, backtrack
            pn = pn - currLen + 1;
        }
        return -1;
    }

    public String longestCommonPrefix(String[] strs) {
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            while (strs[i].indexOf(pre) != 0) {
                pre = pre.substring(0, pre.length() - 1);
                if (pre.isEmpty()) {
                    return "";
                }
                i++;
            }
            return pre;
        }
        return "";
    }
//    作者：LeetCode
//    链接：https://leetcode-cn.com/problems/implement-strstr/solution/shi-xian-strstr-by-leetcode/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public void reverseString(char[] s) {
        int i = 0, j = s.length - 1;
        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
        }
    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.length; i += 2) {
            sum += nums[i];
        }
        return sum;
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, p = 0, count = 0;
        for (; p < nums.length; ) {
            if (p < nums.length && nums[p] != 1) p++;
            count = 0;
            while (p < nums.length && nums[p] == 1) {
                count++;
                p++;
            }
            max = count > max ? count : max;
        }
        return max;
    }

    public int minSubArrayLen(int s, int[] nums) {
        int left = 0, sum = 0, ans = Integer.MAX_VALUE, minAns = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                ans = i - left + 1;
                minAns = ans < minAns ? ans : minAns;
                sum -= nums[left++];
            }
        }
        return minAns == Integer.MAX_VALUE ? 0 : minAns;

    }

    public List <Integer> getRow(int rowIndex) {
        List <List <Integer>> res = new LinkedList <>();
        res.add(new LinkedList <>());
        res.get(0).add(1);
        for (int i = 1; i < rowIndex; i++) {
            List <Integer> tmp = new LinkedList <>();
            List <Integer> pre = res.get(i - 1);
            tmp.add(1);
            for (int j = 1; j < i; j++) {
                tmp.add(pre.get(j - 1) + pre.get(j));
            }
            tmp.add(1);
            res.add(tmp);
        }
        return res.get(rowIndex - 1);
    }

    //    public String reverseWords(String s) {
//        List<String> res = Arrays.asList(s.split("//s+"));
//        Collections.reverse(res);
//        String.join(res," ");
//    }
    public String reverseWords2(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List <String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    public String reverseWords(String s) {
        s = s.trim();
        List <String> res = Arrays.asList(s);
        List <String> res2 = new LinkedList <>();
        for (String ex : res) {
            StringBuilder sb = new StringBuilder();
            res2.add(sb.append(ex).reverse().toString());
        }
        return String.join(" ", res2);

    }

    public void moveZeroes(int[] nums) {
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                //swap
                j++;
            }
        }
    }

    class TrieNode {
        // R links to node children
        private TrieNode[] links;
        private final int R = 26;
        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        public TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        public void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean isEnd() {
            return isEnd;
        }
    }

    class Trie2 {
        private TrieNode root;

        public Trie2() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char currentChar = word.charAt(i);
                if (!node.containsKey(currentChar)) {
                    node.put(currentChar, new TrieNode());
                }
                node = node.get(currentChar);
            }
            node.setEnd();
        }

        // search a prefix or whole key in trie and
        // returns the node where search ends
        private TrieNode searchPrefix(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char curLetter = word.charAt(i);
                if (node.containsKey(curLetter)) {
                    node = node.get(curLetter);
                } else {
                    return null;
                }
            }
            return node;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node = searchPrefix(word);
            return node != null && node.isEnd();
        }

        public boolean startsWith(String prefix) {
            TrieNode node = searchPrefix(prefix);
            return node != null;
        }


    }

    public class Trie {
        private boolean is_end = false;
        private Trie next[] = new Trie[26];

        public Trie() {
        }

        public void insert(String word) {//插入单词
            Trie root = this;
            for (int i = 0; i < word.length(); i++) {
                if (root.next[word.charAt(i) - 'a'] == null) {
                    root.next[word.charAt(i) - 'a'] = new Trie();
                }
                root = next[word.charAt(i) - 'a'];
            }
            root.is_end = true;
        }

        public boolean search(String word) {//查找单词
            Trie root = this;
            for (int i = 0; i < word.length(); i++) {
                if (root.next[word.charAt(i) - 'a'] != null) {
                    root = next[word.charAt(i) - 'a'];
                } else return false;

            }
            return is_end;
        }

        public boolean startsWith(String prefix) {//查找前缀
            Trie root = this;
            for (int i = 0; i < prefix.length(); i++) {
                if (root.next[prefix.charAt(i) - 'a'] != null) {
                    root = next[prefix.charAt(i) - 'a'];
                } else return false;

            }
            return true;
        }


    }
    class MapSum {
        boolean isEnd = false;
        MapSum[] next = new MapSum[26];
        int value = 0;

        /**
         * Initialize your data structure here.
         */
        public MapSum() {

        }

        public void insert(String key, int val) {
            MapSum root = this;
            char[] list = key.toCharArray();
            for (int i = 0; i < list.length; i++) {
                if (root.next[list[i] - 'a'] == null) root.next[list[i] - 'a'] = new MapSum();
                root = root.next[list[i] - 'a'];
            }
            root.value = val;
            root.isEnd = true;
        }

        public int sum(String prefix) {
            char[] list = prefix.toCharArray();
            int sum = 0;
            MapSum root = this;
            for (int i = 0; i < list.length; i++) {
                if (root.next[list[i] - 'a'] != null) {
                    root = root.next[list[i] - 'a'];
//                    sum += root.value;
                }else{
                    return 0;
                }
            }

//            sum+=sumChildren(root);
            return sumChildren(root);
        }
        private int sumChildren(MapSum root) {
            int val = root.value;
            for (int i = 0; i < 26; i++) {
                if (root.next[i] != null) {
                   val+=sumChildren(root.next[i]);

                }
            }
            return val;
        }

    }



    public static void main(String[] args) {
        int rows = 4;
//        String s1 = "Hello World";
////        s1[5] = ',';
//        System.out.println(s1);
//        int one = -129;
//        System.out.println(Integer.toBinaryString(one));
//        System.out.println();
//        System.out.println(Integer.toBinaryString(129));
        D21 d21 = new D21();
        String[] ex = {"a", "b", "c"};
        List <String> one = Arrays.asList(ex);
        String res = String.join("", one);
        System.out.println(res);
        Integer[] ex2 = {1, 2, 3};
        List <Integer> two = Arrays.asList(ex2);
        String exa = "Let's take LeetCode contest";
        int[] exb = {0, 1, 0, 3, 12};
        d21.moveZeroes(exb);
//        System.out.println(two);
//        //        int tw=d21.findMaxConsecutiveOnes(ex);
//        System.out.println();
    }

}
