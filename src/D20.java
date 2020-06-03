import java.awt.*;
import java.util.*;
import java.util.List;

public class D20 {
    public int[][] floodFill2(int[][] image, int sr, int sc, int newColor) {
        int rows = image.length;
        int cols = image[0].length;
//        int[][] visited = new int[rows][cols];
//        visited[sr][sc] = 1;
//        dfs(sr,sc);
        int color = image[sr][sc];
        if (color != newColor) dfs(image, sr, sc, color, newColor);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int color, int newColor) {
        if (image[sr][sc] == color) image[sr][sc] = newColor;
        if (sr >= 1) dfs(image, sr - 1, sc, color, newColor);
        if (sc >= 1) dfs(image, sr, sc - 1, color, newColor);
        if (sr < image.length - 1) dfs(image, sr + 1, sc, color, newColor);
        if (sc < image[0].length - 1) dfs(image, sr, sc + 1, color, newColor);
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int rows = image.length;
        int cols = image[0].length;
        Queue <Integer> queue = new LinkedList <>();

        if (image[sr][sc] != newColor) {
            int color = image[sr][sc];
            queue.offer(sr * 10 + sc);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    int cur = queue.poll();
                    int x = cur / 10;
                    int y = cur % 10;
                    if (image[x][y] == color) {
                        image[x][y] = newColor;
                        if (x >= 1) queue.offer((x - 1) * 10 + y);
                        if (y >= 1) queue.offer(x * 10 + y - 1);
                        if (x < rows - 1) queue.offer((x + 1) * 10 + y);
                        if (y < rows - 1) queue.offer(x * 10 + y + 1);
                    }

                }

            }
        }
        return image;
    }
//    int[][] directions={{1,0},{0,1},{-1,0},{0,-1}};

    public int[][] floodFill3(int[][] image, int sr, int sc, int newColor) {
        if (newColor == image[sr][sc]) return image;
        int h = image.length, w = image[0].length;  //记录image的长宽
        int[][] direct = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};  //移动方向的数组
        Queue <int[]> q = new LinkedList <>();  //队列
        q.offer(new int[]{sr, sc});  //队列保存的是位置坐标
        int oldColor = image[sr][sc];  //记录旧颜色
        while (!(q.size() == 0)) {
            int[] p = q.poll();  //取出队首元素
            image[p[0]][p[1]] = newColor;  //上色
            for (int[] d : direct) {  //将四周相同颜色的点的位置入队
                int new_sr = p[0] + d[0];
                int new_sc = p[1] + d[1];
                if (new_sr >= 0 && new_sr < h && new_sc >= 0 && new_sc < w && image[new_sr][new_sc] == oldColor) {
                    q.offer(new int[]{new_sr, new_sc});
                }
            }
        }
        return image;
    }

    int rows, cols;

    public int[][] updateMatrix2(int[][] matrix) {
        rows = matrix.length;
        cols = matrix[0].length;
        int[][] res = new int[rows][cols];
        int[][] seen = new int[rows][cols];
        Queue <int[]> queue = new LinkedList <>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    seen[i][j] = 1;
                    queue.offer(new int[]{i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int m = 0; m < size; m++) {
                int[] cur = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int x = cur[0] + directions[k][0];
                    int y = cur[1] + directions[k][1];
                    if (x >= 0 && x < rows && y >= 0 && y < cols && seen[x][y] != 1) {
                        seen[x][y] = 1;
                        res[x][y] = res[cur[0]][cur[1]] + 1;
                        queue.offer(new int[]{x, y});
                    }
//                    else if (x >= 0 && x < rows && y >= 0 && y < cols) queue.offer(new int[]{x,y});
                }
            }
        }

        return res;
    }

    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};




    public int[][] updateMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] res = new int[rows][cols];
        int[][] seen = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                } else res[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i >= 1) res[i][j] = Math.min(res[i][j], res[i - 1][j] + 1);
                if (i < rows - 1) res[i][j] = Math.min(res[i][j], res[i + 1][j] + 1);
                if (j >= 1) res[i][j] = Math.min(res[i][j], res[i ][j-1] + 1);
                if (j < cols - 1) res[i][j] = Math.min(res[i][j], res[i][j+1] + 1);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i >= 1) res[i][j] = Math.min(res[i][j], res[i - 1][j] + 1);
                if (i < rows - 1) res[i][j] = Math.min(res[i][j], res[i + 1][j] + 1);
                if (j >= 1) res[i][j] = Math.min(res[i][j], res[i ][j-1] + 1);
                if (j < cols - 1) res[i][j] = Math.min(res[i][j], res[i][j+1] + 1);
            }
        }
        return res;
    }
    int[] seen;
    int count;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int len = rooms.size();
         count =0;
        seen = new int[len];
        seen[0] =1;
        count++;
        for(int i=0;i<rooms.get(0).size();i++){
            int num = rooms.get(0).get(i);
            if(seen[num]==0) {
                seen[num] =1;
                count++;
                dfsRoom(rooms, num);
            }
        }
        return count==len;
    }

    private void dfsRoom(List<List<Integer>> rooms, int num) {
        for(int i=0;i<rooms.get(num).size();i++){
            int k = rooms.get(num).get(i);
            if(seen[k]==0) {
                seen[k] =1;
                count++;
                dfsRoom(rooms, k);
            }
        }
    }

    class Solution {
        public boolean canVisitAllRooms(List<List<Integer>> rooms) {
            boolean[] seen = new boolean[rooms.size()];
            seen[0] = true;
            Stack<Integer> stack = new Stack();
            stack.push(0);

            //At the beginning, we have a todo list "stack" of keys to use.
            //'seen' represents at some point we have entered this room.
            while (!stack.isEmpty()) { // While we have keys...
                int node = stack.pop(); // Get the next key 'node'
                for (int nei: rooms.get(node)) // For every key in room # 'node'...
                    if (!seen[nei]) { // ...that hasn't been used yet
                        seen[nei] = true; // mark that we've entered the room
                        stack.push(nei); // add the key to the todo list
                    }
            }

            for (boolean v: seen)  // if any room hasn't been visited, return false
                if (!v) return false;
            return true;
        }
    }

    public int pivotIndex(int[] nums) {
        if(nums.length==0)return -1;
        int left =0,right =nums.length-1,lSum=nums[left],rSum=nums[right];
        while(right-left>1){
            if(lSum>rSum)rSum+=nums[--right];
            else lSum+=nums[++left];
//            lSum+=nums[left++];
//            rSum+=nums[right--];
        }
        int r1= lSum==rSum?left+1:-1;
        int left2 =0,right2 =nums.length-1,lSum2=nums[left2],rSum2=nums[right2];
        while(right2-left2>1){
            if(lSum2>rSum2)lSum2+=nums[++left2];
            else rSum2+=nums[--right2];
//            lSum+=nums[left++];
//            rSum+=nums[right--];
        }
        int r2= lSum2==rSum2?left2+1:-1;
        return Math.max(r1,r2);

    }
    public int dominantIndex2(int[] nums) {
        quickSort(nums,0,nums.length-1);
        return nums[nums.length-1]>nums[nums.length-2]*2?1:2;
    }
    public void quickSort(int[] nums,int left ,int right ){
        int key = nums[0];
        int p1=left,p2 = right;
        if(p2<p1) return;
        while(p1<p2){
            while(nums[p2]>=key&&p2>p1){
                p2--;
            }
            while(nums[p1]<=key&&p2>p1){
                p1++;
            }
            if(p2>p1){
                int tmp = nums[p2];
                nums[p2] = nums[p1];
                nums[p1] = tmp;
            }
        }
        nums[0]=nums[p1];
        nums[p1]=key;
        quickSort(nums,left,p1-1);
        quickSort(nums,p1+1,right);
    }
    public int dominantIndex(int[] nums) {
        int max=Integer.MIN_VALUE,maxIndex=-1,secondMax=Integer.MIN_VALUE,secondIndex=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]>max) {max = nums[i];
            maxIndex=i;}
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]<max&&nums[i]>secondMax) {secondMax = nums[i];secondIndex=i;}
        }
        return max>secondMax*2?maxIndex:-1;

    }
//    public int[] plusOne(int[] digits) {
//        int len = digits.length;
//        digits[len-1]+=1;
////        System.arraycopy(src, srcPos, dest, destPos, length);
//        if(digits[len-1]==10) {
//            int[] res = new int[len+1];
//            System.arraycopy(digits,0,res,0,len-1);
//            res[len-1]=1;
//            res[len]=0;
//        }
//
//
//        return res;
//    }
class Solution2 {
    public int[] findDiagonalOrder2(int[][] matrix) {
        if(matrix==null||matrix.length==0) return new int[0];
        int N = matrix.length;
        int M = matrix[0].length;
        int[] res = new int[M+N];
        int k=0;
        ArrayList<Integer> intermedia = new ArrayList <>();
        for(int d=0;d<M+N-1;d++){
            intermedia.clear();
            int r= d>N?d-M+1:0;
            int c = d>N?d:N-1;
            while(r<N&&c>-1){
                intermedia.add(matrix[r][c]);
                r++;
                c--;
            }
            if(d%2==0){
                Collections.reverse(intermedia);
            }
            for(int i=0;i<intermedia.size();i++){
                res[k++]=intermedia.get(i);
            }

        }
        return res;
    }
    public int[] findDiagonalOrder(int[][] matrix) {

        // Check for empty matrices
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        // Variables to track the size of the matrix
        int N = matrix.length;
        int M = matrix[0].length;

        // The two arrays as explained in the algorithm
        int[] result = new int[N*M];
        int k = 0;
        ArrayList<Integer> intermediate = new ArrayList<Integer>();

        // We have to go over all the elements in the first
        // row and the last column to cover all possible diagonals
        for (int d = 0; d < N + M - 1; d++) {

            // Clear the intermediate array every time we start
            // to process another diagonal
            intermediate.clear();

            // We need to figure out the "head" of this diagonal
            // The elements in the first row and the last column
            // are the respective heads.
            int r = d < M ? 0 : d - M + 1;
            int c = d < M ? d : M - 1;

            // Iterate until one of the indices goes out of scope
            // Take note of the index math to go down the diagonal
            while (r < N && c > -1) {
                intermediate.add(matrix[r][c]);
                ++r;
                --c;
            }

            // Reverse even numbered diagonals. The
            // article says we have to reverse odd
            // numbered articles but here, the numbering
            // is starting from 0 :P
            if (d % 2 == 0) {
                Collections.reverse(intermediate);
            }

            for (int i = 0; i < intermediate.size(); i++) {
                result[k++] = intermediate.get(i);
            }
        }
        return result;
    }
}
    class Solution3 {
        public int[] findDiagonalOrder(int[][] matrix) {

            // Check for empty matrices
            if (matrix == null || matrix.length == 0) {
                return new int[0];
            }

            // Variables to track the size of the matrix
            int N = matrix.length;
            int M = matrix[0].length;

            // Incides that will help us progress through
            // the matrix, one element at a time.
            int row = 0, column = 0;

            // As explained in the article, this is the variable
            // that helps us keep track of what direction we are
            // processing the current diaonal
            int direction = 1;

            // The final result array
            int[] result = new int[N*M];
            int r = 0;

            // The uber while loop which will help us iterate over all
            // the elements in the array.
            while (row < N && column < M) {

                // First and foremost, add the current element to
                // the result matrix.
                result[r++] = matrix[row][column];

                // Move along in the current diagonal depending upon
                // the current direction.[i, j] -> [i - 1, j + 1] if
                // going up and [i, j] -> [i + 1][j - 1] if going down.
                int new_row = row + (direction == 1 ? -1 : 1);
                int new_column = column + (direction == 1 ? 1 : -1);

                // Checking if the next element in the diagonal is within the
                // bounds of the matrix or not. If it's not within the bounds,
                // we have to find the next head.
                if (new_row < 0 || new_row == N || new_column < 0 || new_column == M) {

                    // If the current diagonal was going in the upwards
                    // direction.
                    if (direction == 1) {

                        // For an upwards going diagonal having [i, j] as its tail
                        // If [i, j + 1] is within bounds, then it becomes
                        // the next head. Otherwise, the element directly below
                        // i.e. the element [i + 1, j] becomes the next head
                        row += (column == M - 1 ? 1 : 0) ;
                        column += (column < M - 1 ? 1 : 0);

                    } else {

                        // For a downwards going diagonal having [i, j] as its tail
                        // if [i + 1, j] is within bounds, then it becomes
                        // the next head. Otherwise, the element directly below
                        // i.e. the element [i, j + 1] becomes the next head
                        column += (row == N - 1 ? 1 : 0);
                        row += (row < N - 1 ? 1 : 0);
                    }

                    // Flip the direction
                    direction = 1 - direction;

                } else {

                    row = new_row;
                    column = new_column;
                }
            }
            return result;
        }
    }


    public static void main(String[] args) {
//    int[] b ={1,2};
//    Queue<int[]> queue2 = new LinkedList <>();
//    queue2.offer(b);
//    int[] c = queue2.poll();
//    System.out.println(c[0]);
//    Queue<int> queue3 = new LinkedList <>();
        //泛型不能是基本数据类型，但是int[]不是基本数据类型！
        D20 d20 = new D20();
        int[] ex = {1,7,3,6,5,6};
        int res = d20.dominantIndex(ex);
        System.out.println(res);

    }
}
