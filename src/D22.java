import java.util.*;

public class D22 {
    class MapSum {

        // 创建字典树节点类
        private class Node {

            public int value;
            public HashMap <Character, Node> next;

            public Node(int value) {
                this.value = value;
                this.next = new HashMap <>();
            }

            public Node() {
                this(0);
            }

        }

        private Node root;

        /**
         * Initialize your data structure here.
         */
        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int val) {
            Node curr = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (curr.next.get(c) == null)
                    curr.next.put(c, new Node());
                curr = curr.next.get(c);
            }
            curr.value = val;
        }

        public int sum(String prefix) {
            Node curr = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if (curr.next.get(c) == null)
                    return 0;
                curr = curr.next.get(c);
            }

            return sum(curr);
        }

        private int sum(Node node) {
            int res = node.value;
            for (char c : node.next.keySet())
                res += sum(node.next.get(c));

            return res;
        }
    }

    public int findMaximumXOR2(int[] nums) {
        int Len = nums[0];
        for (int num : nums) {
            Len = Math.max(num, Len);
        }
        int L = Integer.toBinaryString(Len).length();
        int max = 0, cur;
        HashSet <Integer> set = new HashSet <>();
        for (int i = L - 1; i > -1; i--) {
            set.clear();
            max <<= 1;
            cur = max | 1;

            for (int num : nums) {
                set.add(num >> i);

            }
            for (int prefix : set) {
                if (set.contains(cur ^ prefix)) {
                    max = cur;
                    break;
                }
            }
        }
        return max;
    }



    class TrieNode {
        HashMap <Character, TrieNode> children = new HashMap <Character, TrieNode>();

        public TrieNode() {
        }
    }

    public int findMaximumXOR(int[] nums) {
        int L = nums[0];
        for (int num : nums) {
            L = Math.max(L, num);
        }
        int len = Integer.toBinaryString(L).length();
        TrieNode trie = new TrieNode();
        int max = 0;
        for (int num : nums) {
            TrieNode node = trie, xornode = trie;
            int curXor = 0;
            char[] number = Integer.toBinaryString(num).toCharArray();
            for (int i = len - 1; i > -1; i--) {
                if (node.children.containsKey(number[i])) {
                    node = node.children.get(number[i]);
                } else {
                    TrieNode newNode = new TrieNode();
                    node.children.put(number[i], newNode);
                    node = newNode;
                }
            }
            for (int i = len - 1; i > -1; i--) {
                int toggledBit = number[i] == 1 ? 0 : 1;
                if (xornode.children.containsKey(toggledBit)) {
                    curXor <<= 1;
                    xornode = xornode.children.get(toggledBit);
                } else {
                    curXor <<= 0;
                    xornode = xornode.children.get(number[i]);
                }
            }
            max = Math.max(curXor, max);
        }
        return max;
    }

    class Solution {
        public int findMaximumXOR(int[] nums) {
            // Compute length L of max number in a binary representation
            int maxNum = nums[0];
            for (int num : nums) maxNum = Math.max(maxNum, num);
            int L = (Integer.toBinaryString(maxNum)).length();

            // zero left-padding to ensure L bits for each number
            int n = nums.length, bitmask = 1 << L;
            String[] strNums = new String[n];
            for (int i = 0; i < n; ++i) {
                strNums[i] = Integer.toBinaryString(bitmask | nums[i]).substring(1);
            }
//            int i=0;
//            bitmask = 1<<L;
//            strNums[i] = Integer.toBinaryString(bitmask|nums[i]).substring(1);

            TrieNode trie = new TrieNode();
            int maxXor = 0;
            for (String num : strNums) {
                TrieNode node = trie, xorNode = trie;
                int currXor = 0;
                for (Character bit : num.toCharArray()) {
                    // insert new number in trie
                    if (node.children.containsKey(bit)) {
                        node = node.children.get(bit);
                    } else {
                        TrieNode newNode = new TrieNode();
                        node.children.put(bit, newNode);
                        node = newNode;
                    }

                    // compute max xor of that new number
                    // with all previously inserted
                    Character toggledBit = bit == '1' ? '0' : '1';
                    if (xorNode.children.containsKey(toggledBit)) {
                        currXor = (currXor << 1) | 1;
                        xorNode = xorNode.children.get(toggledBit);
                    } else {
                        currXor = currXor << 1;
                        xorNode = xorNode.children.get(bit);
                    }
                }
                maxXor = Math.max(maxXor, currXor);
            }

            return maxXor;
        }
    }


    private boolean[][] marked;

    //        x-1,y
    // x,y-1  x,y    x,y+1
    //        x+1,y
    private int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    // 盘面上有多少行
    private int m;
    // 盘面上有多少列
    private int n;
    private String word;
    private char[][] board;

    public List <String> findWords(char[][] board, String[] words) {
        List <String> res = new LinkedList <>();
        for (String word : words) {
            if (exist(board, word)) {
                res.add(word);
            }
        }
        return res;
    }

    public boolean exist(char[][] board, String word) {
        m = board.length;
        if (m == 0) {
            return false;
        }
        n = board[0].length;
        marked = new boolean[m][n];
        this.word = word;
        this.board = board;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int start) {
        if (start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }
        if (board[i][j] == word.charAt(start)) {
            marked[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                if (inArea(newX, newY) && !marked[newX][newY]) {
                    if (dfs(newX, newY, start + 1)) {
                        return true;
                    }
                }
            }
            marked[i][j] = false;
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    class S1 {
        class TrieNode {
            HashMap <Character, TrieNode> children = new HashMap <Character, TrieNode>();
            String word = null;

            public TrieNode() {
            }
        }

        class Solution {
            char[][] _board = null;
            ArrayList <String> _result = new ArrayList <String>();

            public List <String> findWords(char[][] board, String[] words) {

                // Step 1). Construct the Trie
                TrieNode root = new TrieNode();
                for (String word : words) {
                    TrieNode node = root;

                    for (Character letter : word.toCharArray()) {
                        if (node.children.containsKey(letter)) {
                            node = node.children.get(letter);
                        } else {
                            TrieNode newNode = new TrieNode();
                            node.children.put(letter, newNode);
                            node = newNode;
                        }
                    }
                    node.word = word;  // store words in Trie
                }

                this._board = board;
                // Step 2). Backtracking starting for each cell in the board
                for (int row = 0; row < board.length; ++row) {
                    for (int col = 0; col < board[row].length; ++col) {
                        if (root.children.containsKey(board[row][col])) {
                            backtracking(row, col, root);
                        }
                    }
                }

                return this._result;
            }

            private void backtracking(int row, int col, TrieNode parent) {
                Character letter = this._board[row][col];
                TrieNode currNode = parent.children.get(letter);

                // check if there is any match
                if (currNode.word != null) {
                    this._result.add(currNode.word);
                    currNode.word = null;
                }

                // mark the current letter before the EXPLORATION
                this._board[row][col] = '#';

                // explore neighbor cells in around-clock directions: up, right, down, left
                int[] rowOffset = {-1, 0, 1, 0};
                int[] colOffset = {0, 1, 0, -1};
                for (int i = 0; i < 4; ++i) {
                    int newRow = row + rowOffset[i];
                    int newCol = col + colOffset[i];
                    if (newRow < 0 || newRow >= this._board.length || newCol < 0
                            || newCol >= this._board[0].length) {
                        continue;
                    }
                    if (currNode.children.containsKey(this._board[newRow][newCol])) {
                        backtracking(newRow, newCol, currNode);
                    }
                }

                // End of EXPLORATION, restore the original letter in the board.
                this._board[row][col] = letter;

                // Optimization: incrementally remove the leaf nodes
                if (currNode.children.isEmpty()) {
                    parent.children.remove(letter);
                }
            }
        }
    }

    public List <List <Integer>> palindromePairs(String[] words) {

        List <List <Integer>> pairs = new ArrayList <>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) continue;
                String combined = words[i].concat(words[j]);
                StringBuilder sb = new StringBuilder();
                if (combined == sb.append(combined).reverse().toString()) {
                    pairs.add(Arrays.asList(i, j));
                }
            }

        }
        return pairs;
    }
    class Solution2 {
        private Node root;
        public List<List<Integer>> palindromePairs(String[] words) {
            this.root = new Node();
            int n = words.length;
            // 字典树的插入，注意维护每个节点上的两个列表
            for (int i = 0; i < n; i++) {
                String rev = new StringBuilder(words[i]).reverse().toString();
                Node cur = root;
                if (isPalindrome(rev.substring(0))) cur.suffixs.add(i);
                for (int j = 0; j < rev.length(); j++) {
                    char ch = rev.charAt(j);
                    if (cur.children[ch-'a']==null) cur.children[ch-'a'] = new Node();
                    cur = cur.children[ch-'a'];
                    if (isPalindrome(rev.substring(j+1))) cur.suffixs.add(i);
                }
                cur.words.add(i);
            }
            // 用以存放答案的列表
            List<List<Integer>> ans = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String word = words[i];
                Node cur = root;
                int j = 0;
                for ( ;j < word.length(); j++) {
                    // 到j位置，后续字符串若是回文对，则在该节点位置上所有单词都可以与words[i]构成回文对
                    // 因为我们插入的时候是用每个单词的逆序插入的:)
                    if(isPalindrome(word.substring(j)))
                        for (int k : cur.words)
                            if (k != i) ans.add(Arrays.asList(i,k));

                    char ch = word.charAt(j);
                    if (cur.children[ch-'a'] == null) break;
                    cur = cur.children[ch-'a'];

                }
                // words[i]遍历完了，现在找所有大于words[i]长度且符合要求的单词，suffixs列表就派上用场了:)
                if (j == word.length())
                    for (int k : cur.suffixs)
                        if (k != i) ans.add(Arrays.asList(i,k));

            }
            return ans;

        }
        //  判断一个字符串是否是回文字符串
        private boolean isPalindrome(String w) {
            int i = 0, j = w.length()-1;
            while (i < j) {
                if (w.charAt(i) != w.charAt(j)) return false;
                i++; j--;
            }
            return true;
        }
    }
    class Node {
        public Node[] children;
        public List<Integer> words;
        public List<Integer> suffixs;
        public Node() {
            this.children = new Node[26];
            this.words = new ArrayList<>();
            this.suffixs = new ArrayList<>();
        }
    }
    class Solution3 {

        private List<String> allValidPrefixes(String word) {
            List<String> validPrefixes = new ArrayList<>();
            for (int i = 0; i < word.length(); i++) {
                if (isPalindromeBetween(word, i, word.length() - 1)) {
                    validPrefixes.add(word.substring(0, i));
                }
            }
            return validPrefixes;
        }

        private List<String> allValidSuffixes(String word) {
            List<String> validSuffixes = new ArrayList<>();
            for (int i = 0; i < word.length(); i++) {
                if (isPalindromeBetween(word, 0, i)) {
                    validSuffixes.add(word.substring(i + 1, word.length()));
                }
            }
            return validSuffixes;
        }

        // Is the prefix ending at i a palindrome?
        private boolean isPalindromeBetween(String word, int front, int back) {
            while (front < back) {
                if (word.charAt(front) != word.charAt(back)) return false;
                front++;
                back--;
            }
            return true;
        }

        public List<List<Integer>> palindromePairs(String[] words) {
            // Build a word -> original index mapping for efficient lookup.
            Map<String, Integer> wordSet = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                wordSet.put(words[i], i);
            }

            // Make a list to put all the palindrome pairs we find in.
            List<List<Integer>> solution = new ArrayList<>();

            for (String word : wordSet.keySet()) {

                int currentWordIndex = wordSet.get(word);
                String reversedWord = new StringBuilder(word).reverse().toString();

                // Build solutions of case #1. This word will be word 1.
                if (wordSet.containsKey(reversedWord)
                        && wordSet.get(reversedWord) != currentWordIndex) {
                    solution.add(Arrays.asList(currentWordIndex, wordSet.get(reversedWord)));
                }

                // Build solutions of case #2. This word will be word 2.
                for (String suffix : allValidSuffixes(word)) {
                    String reversedSuffix = new StringBuilder(suffix).reverse().toString();
                    if (wordSet.containsKey(reversedSuffix)) {
                        solution.add(Arrays.asList(wordSet.get(reversedSuffix), currentWordIndex));
                    }
                }

                // Build solutions of case #3. This word will be word 1.
                for (String prefix : allValidPrefixes(word)) {
                    String reversedPrefix = new StringBuilder(prefix).reverse().toString();
                    if (wordSet.containsKey(reversedPrefix)) {
                        solution.add(Arrays.asList(currentWordIndex, wordSet.get(reversedPrefix)));
                    }
                }
            }
            return solution;
        }
    }
//    public boolean hasCycle(ListNode head) {
//        if(head ==null||head.next==null){rerturn false;}
//        slow, fast =;
//
//    }
private ListNode getIntersect(ListNode head) {
    ListNode tortoise = head;
    ListNode hare = head;

    // A fast pointer will either loop around a cycle and meet the slow
    // pointer or reach the `null` at the end of a non-cyclic list.
    while (hare != null && hare.next != null) {
        tortoise = tortoise.next;
        hare = hare.next.next;
        if (tortoise == hare) {
            return tortoise;
        }
    }

    return null;
}

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at some
        // node. Otherwise, there is no cycle, so we cannot find an e***ance to
        // a cycle.
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the e**ance to the cycle, we have two pointers traverse at
        // the same speed -- one from the front of the list, and the other from
        // the point of intersection.
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        HashSet set = new HashSet();
        while(headA!=null&&headB!=null){
            if(set.contains(headA)) return headA;
            set.add(headA);
            if(set.contains(headB)) return headB;
            set.add(headB);
            headA = headA.next;
            headB = headB.next;
        }
        while(headA!=null){
            if(set.contains(headA)) return headA;
            set.add(headA);
            headA = headA.next;
        }
        while(headB!=null){
            if(set.contains(headB)) return headB;
            set.add(headB);
            headB = headB.next;
        }
        return null;
    }
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA;
        ListNode B = headB;
        while(A!=null||B!=null){
            if(A==null) A=headB;
            if(B==null) B=headA;
            if(A.val==B.val) return A;
            A = A.next;
            B =B.next;
        }
        return null;
    }
    public ListNode removeNthFromEnd(ListNode head, int n){
//        if(head.next)
        ListNode pre =head, cur = head.next;
        if(cur==null){
            if(n==1) return null;
            else return head;
        }
        if(n==0){
            return head.next;
        }
        if(n>1){
            for(int i=0;i<n-1;i++){
                cur=cur.next;
            }
        }
        while(cur.next!=null){
                pre = pre.next;
                cur = cur.next;
            }
        pre.next=pre.next.next;
        return head;

    }
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next=head;
        if(head==null) return null;
        if(head.next==null) return head;
        ListNode pre =head,cur = head.next,suc = cur.next;
        pre.next=null;
        while(suc!=null){
            cur.next=pre;
            pre = cur;
            cur = suc;
            suc = suc.next;
        }
        cur.next=pre;
        return cur;
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {

        // Empty list
        if (head == null) {
            return null;
        }

        // Move the two pointers until they reach the proper starting point
        // in the list.
        ListNode cur = head, prev = null;
        while (m > 1) {
            prev = cur;
            cur = cur.next;
            m--;
            n--;
        }

        // The two pointers that will fix the final connections.
        ListNode con = prev, tail = cur;

        // Iteratively reverse the nodes until n becomes 0.
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = prev;
            prev = cur;
            cur = third;
            n--;
        }

        // Adjust the final connections as explained in the algorithm
        if (con != null) {
            con.next = prev;
        } else {
            head = prev;
        }

        tail.next = cur;
        return head;
    }
    ListNode reverse(ListNode head) {
        if(head.next==null){
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next=head;
        head.next=null;
        return last;
    }
    ListNode succ;
    ListNode reverseN(ListNode head, int n) {
        if(n==1){
            succ=head.next;
            return head;
        }
        ListNode last = reverseN(head.next,n-1);
        head.next.next=head;
        head.next=succ;
        return last;
    }
    ListNode reverseBetween2(ListNode head, int m, int n) {
        // base case
        if (m == 1) {
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

//    public ListNode reverseBetween3(ListNode head, int m, int n) {
//        if(head==null) return null;
//        ListNode pre =null,cur =head;
//        while(m>1){
//            pre=cur;
//            cur = cur.next;
//            m--;
//            n--;
//
//        }
//        ListNode conc = pre,tail = cur;
//        while(n>0){
//
//        }
//    }
    public ListNode removeElements2(ListNode head, int val) {
        if(head==null) return head;
        ListNode pre = null,cur = head;
        while(cur.next!=null){
            if(cur.val==val){
                pre.next=cur.next;
                cur=cur.next;
            }else {
                pre = cur;
                cur = cur.next;
            }
        }
        if(cur.val==val){
            if(pre ==null) return null;
            pre.next=null;
        }
        return head;
}
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;

        ListNode prev = sentinel, curr = head;
        while (curr != null) {
            if (curr.val == val) prev.next = curr.next;
            else prev = curr;
            curr = curr.next;
        }
        return sentinel.next;
    }
    public ListNode oddEvenList2(ListNode head) {
        ListNode odd = new ListNode(-1);
        ListNode even = new ListNode(-1);
        ListNode evenhead =even,oddHead = odd;
        while(head!=null){
            odd.next=head;
            head = head.next;
            odd = odd.next;

            if(head!=null){
                    even.next=head;
            head = head.next;
            even = even.next;
            }
        }
        odd.next=evenhead;
        return oddHead;
    }
    public ListNode oddEvenList(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow=slow.next;
        }
        ListNode mid = reverse2(slow);
        boolean flag = true;
        while(mid!=head){
            if(head.next==mid){
                return head.val==mid.val;

            }
            if(mid.val!=head.val){
                flag =false;
                break;
            }else{
                mid=mid.next;
                head= head.next;
            }
        }
        return flag;
    }

    private ListNode reverse2(ListNode head) {
        ListNode pre =null,cur = head;
        while(cur!=null){
            ListNode tmp = cur.next;
            cur.next=pre;
            pre=cur;
            cur=tmp;
        }
        return pre;
    }
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int carry =0;
//        ListNode res = new ListNode(-1);
        while(l1!=null&&l2!=null){
            int sum = l1.val+l2.val+carry;
            l1.val=sum%10;
            carry=sum/10;
            l1 =l1.next;
            l2=l2.next;
        }
        while (l2!=null){
            l1=l2;
            l1.val=l2.val+carry;
            carry=l1.val/10;
            l2=l2.next;
            l1=l1.next;
        }
        while(l1!=null){
            int sum = l1.val+carry;
            l1.val = sum%10;
            carry=sum/10;
        }
        return l1;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p1 =l1,p2 = l2,cur = dummy;
        int carry =0;
        while(p1!= null||p2!=null){
            int x = p1==null?0:p1.val;
            int y = p2==null?0:p2.val;
            int sum = x +y+carry;
            carry = sum/10;
            cur.next = new ListNode(sum%10);
            if(p1!=null) p1 = p1.next;
            if(p2!=null) p2 = p2.next;
        }
        return dummy.next;
    }
    public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int i = 5;
//        int i2 =Integer.toBinaryString(i);
//        System.out.println(i2);
        int[] ex = {1};
        D22 d22 = new D22();
        StringBuilder sb = new StringBuilder();
        for(int num:ex){
            sb.append(num);
        }
        System.out.println(sb.toString());
        System.out.println(sb.reverse().toString());
        System.out.println(sb.reverse().toString().equals(sb.toString()));
//        int res = d22.findMaximumXOR(ex);
//        System.out.println(res);

    }


}
