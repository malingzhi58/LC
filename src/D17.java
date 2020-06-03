import java.util.*;

public class D17 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
    int min = Integer.MIN_VALUE;
    public int minDepth2(TreeNode root) {
        if(root==null) return 0;
        return 1+Math.min(minDepth(root.left),minDepth(root.right));
    }
    public int minDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList <>();

        queue.offer(root);
        int depth=1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                TreeNode cur=queue.poll();
//                depth++;
                if(cur.left==null&&cur.right==null) return depth;
                if(cur.left!=null) queue.offer(cur.left);
                if(cur.right!=null) queue.offer(cur.right);
            }
            depth++;
        }
        return depth;
    }

    int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set <String> deads = new HashSet <>();
        for (String s : deadends) deads.add(s);
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();

                /* 判断是否到达终点 */
                if (deads.contains(cur))
                    continue;
                if (cur.equals(target))
                    return step;

                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    for(int k=-1;k<=1;k+=2){
                        int ul =((cur.charAt(j) - '0')+k +10)%10;
                        String up=cur.substring(0,j)+(""+ul)+cur.substring(j+1);
                        if (!visited.contains(up)) {
                            q.offer(up);
                            visited.add(up);
                        }
                    }
//                    for (int d = -1; d <= 1; d += 2) {
//                        int y = ((node.charAt(i) - '0') + d + 10) % 10;
//                        String nei = node.substring(0, i) + ("" + y) + node.substring(i+1);
//                        if (!seen.contains(nei)) {
//                            seen.add(nei);
//                            queue.offer(nei);
//                        }
//                    }
                    }
                }
            step++;
            }
            /* 在这里增加步数 */


        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }
    String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }
    // 将 s[i] 向下拨动一次
    String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

    int openLock2(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();
                /* 判断是否到达终点 */
                if (deads.contains(cur))
                    continue;
                if (cur.equals(target))
                    return step;
                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }
    int openLock3(String[] deadends, String target) {
        HashSet<String> dead = new HashSet <>();
        HashSet<String> visited = new HashSet <>();
        Queue<String> queue = new LinkedList <>();
        queue.offer("0000");
        visited.add("0000");
        int depth =0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                String cur = queue.poll();
                if(cur.equals(target)) return depth;
                if(dead.contains(cur)) continue;
                for(int j=0;j<4;j++){
                    for(int d=-1;d<=1;d+=2) {
                        int y = ((cur.charAt(j)-'0') + d +10) % 10;
                        String x = cur.substring(0,j) +(""+y)+cur.substring(j+1);

                    }
                }

            }
            depth++;
        }
        return depth;
    }


    int openLock4(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        // 用集合不用队列，可以快速判断元素是否存在
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q1.add("0000");
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            // 哈希集合在遍历的过程中不能修改，用 temp 存储扩散结果
            Set<String> temp = new HashSet<>();

            /* 将 q1 中的所有节点向周围扩散 */
            for (String cur : q1) {
                /* 判断是否到达终点 */
                if (deads.contains(cur))
                    continue;
                if (q2.contains(cur))
                    return step;
                visited.add(cur);

                /* 将一个节点的未遍历相邻节点加入集合 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up))
                        temp.add(up);
                    String down = minusOne(cur, j);
                    if (!visited.contains(down))
                        temp.add(down);
                }
            }
            /* 在这里增加步数 */
            step++;
            // temp 相当于 q1
            // 这里交换 q1 q2，下一轮 while 就是扩散 q2
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }
    public int numSquares2(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // bottom case
        dp[0] = 0;

        // pre-calculate the square numbers.
        int max_square_index = (int) Math.sqrt(n) + 1;
        int square_nums[] = new int[max_square_index];
        for (int i = 1; i < max_square_index; ++i) {
            square_nums[i] = i * i;
        }

        for (int i = 1; i <= n; ++i) {
            for (int s = 0; s < max_square_index; ++s) {
                if (i < square_nums[s])
                    break;
                dp[i] = Math.min(dp[i], dp[i - square_nums[s]] + 1);
            }
        }
        return dp[n];
    }
    public int numSquares3(int n) {
        int maxSqure = (int) (Math.sqrt(n)+1);
        int[] squareSet = new int[maxSqure];
        for(int i=1;i<maxSqure;i++){
            squareSet[i] = (int)Math.pow(i,2);
        }

        int[] dp = new int[n+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<maxSqure;j++){
                if(i>=squareSet[j]){
                    dp[i] = Math.min(dp[i],dp[i-squareSet[j]]+1);
                }
            }
        }
        return dp[n];
    }
    /*
    greedy method
     */
//    public int numSquares(int n) {
//        int count=1
//        for(;count<=n;count++){
//            if(is_dividedby(n,count)){
//                return count;
//            }
//
//        }
//        return count;
//    }
//
//    private boolean is_dividedby(int n, int count) {
//        LinkedList<Integer> squre = new LinkedList <>();
//        for( int i =1;i*i<=n;i++){
//            squre.add(i*i);
//        }
//        if(count==1) return squre.contains(n);
//        else{
////            for(int i=count;i>1;i--){
//                for(int squarNum: squre){
//                    return is_dividedby(n-squarNum,count-1);
////                    if(is_dividedby(n-squarNum,count-1)) return true;
//                }
////            }
//        }
//        return false;
//    }
    Set<Integer> square_nums = new HashSet<Integer>();

    protected boolean is_divided_by(int n, int count) {
        if (count == 1) {
            return square_nums.contains(n);
        }

        for (Integer square : square_nums) {
//            if (is_divided_by(n - square, count - 1)) {
//                return true;
//            }
            return is_divided_by(n - square, count - 1);
        }
        return false;
    }

    public int numSquares(int n) {
        this.square_nums.clear();

        for (int i = 1; i * i <= n; ++i) {
            this.square_nums.add(i * i);
        }

        int count = 1;
        for (; count <= n; ++count) {
             if (is_divided_by(n, count))
                 return count;
//            return is_divided_by(n, count);
        }
        return count;
    }

    public static void main(String[] args) {
//        String[] args = {"on"};
        D17 d17 = new D17();
        int ex = d17.numSquares(12);
//        System.out.println(ex);
        int tw = -3/2;
        System.out.println(tw);

    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack <>();
        for(int i=0;i<s.length();i++){
            switch (s.charAt(i)){
                case '(':
                case '[':
                case '{':
                    stack.push(s.charAt(i));
                    break;
                case ')':
                    if(stack.peek()=='('){
                        stack.pop();
                    }else {
                        return false;
                    }
                    break;
                case ']':
                    if(!stack.isEmpty()&&stack.peek()=='['){
                        stack.pop();
                    }else {
                        return false;
                    }
                    break;
                case '}':
                    if(stack.peek()=='{'){
                        stack.pop();
                    }else {
                        return false;
                    }
                    break;
            }
        }
//        stack.
        if(stack.isEmpty()) return true;
        else return false;
    }
    public boolean isValid2(String s) {
        HashMap <Character, Character> one = new HashMap <>();
        one.put('}','{');
        one.put(')','(');
        one.put(']','[');
        Stack<Character> stack = new Stack <>();
        for(int i=0;i<s.length();i++){
            int popElement;
            if(stack.isEmpty()== false &&one.containsKey(s.charAt(i)) ){
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
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];
        for(int i=0;i<T.length;i++){
            for(int j=i+1;j<T.length;j++){
                if(T[j]>T[i]) result[i] = j-i;
            }
        }
        return result;
    }
    public int[] dailyTemperatures3(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int length = T.length;
        int[] result = new int[length];

        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int pre = stack.pop();
                result[pre] = i - pre;
            }
            stack.add(i);

        }
        return result;
    }
//    public static int evalRPN(String[] tokens) {
//        Stack<Integer> numStack = new Stack<>();
//        int op1,op2;
//        for(String num :tokens){
//            switch (num){
//                default:numStack.push(Integer.valueOf(num));
//                case"+":
//                    op1=numStack.pop();
//                    op2 = numStack.pop();
//                    numStack.push(op2+op1);
//            }
//        }
    }





