import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class D16 {

    private final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    // 标记数组，标记了 grid 的坐标对应的格子是否被访问过
    private boolean[][] marked;
    // grid 的行数
    private int rows;
    // grid 的列数
    private int cols;
    private int[][] grid;

//    public int numIslands(char[][] grid) {
//        rows = grid.length;
//        if (rows == 0) {
//            return 0;
//        }
//        cols = grid[0].length;
//        this.grid = grid;
//        marked = new boolean[rows][cols];
//        int count = 0;
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                // 如果是岛屿中的一个点，并且没有被访问过
//                // 就进行深度优先遍历
//                if (!marked[i][j] && grid[i][j] == '1') {
//                    count++;
//                    dfs(i, j);
//                }
//            }
//        }
//        return count;
//    }
    int area;
    int maxArea;
    public int maxAreaOfIsland(int[][] grid) {
        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;
        this.grid = grid;
        marked = new boolean[rows][cols];
        area = 0;
         maxArea =0;
        for(int i=0;i<rows;i++){
            for(int j =0;j<cols;j++){
                if(grid[i][j]==1&&marked[i][j]==false){
                    area=1;
                    maxArea = area>maxArea?area:maxArea;
                    dfs(i,j);
                }
            }
        }
        return maxArea;
    }


    // 从坐标为 (i,j) 的点开始进行深度优先遍历
    private void dfs(int i, int j) {
        marked[i][j] = true;
        // 得到 4 个方向的坐标
        for (int k = 0; k < 4; k++) {
            int newX = i + directions[k][0];
            int newY = j + directions[k][1];
            // 如果不越界、没有被访问过、并且还要是陆地
            if (inArea(newX, newY) && grid[newX][newY] == 1 && !marked[newX][newY]) {
                area++;
                maxArea = area>maxArea?area:maxArea;
                dfs(newX, newY);
            }
        }
    }

    // 封装成 inArea 方法语义更清晰
    private boolean inArea(int x, int y) {
        // 等于号不要忘了
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }


    public int openLock(String[] deadends, String target) {
        Set <String> dead = new HashSet();
        for (String d: deadends) dead.add(d);

        Queue <String> queue = new LinkedList();
        queue.offer("0000");
        queue.offer(null);

        Set<String> seen = new HashSet();
        seen.add("0000");

        int depth = 0;
        while (!queue.isEmpty()) {
            String node = queue.poll();
            if (node == null) {
                depth++;
                if (queue.peek() != null)
                    queue.offer(null);
            } else if (node.equals(target)) {
                return depth;
            } else if (!dead.contains(node)) {
                for (int i = 0; i < 4; ++i) {
                    for (int d = -1; d <= 1; d += 2) {
                        int y = ((node.charAt(i) - '0') + d + 10) % 10;
                        String nei = node.substring(0, i) + ("" + y) + node.substring(i+1);
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        D16 d16 = new D16();
//        int[][] ex={{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
//        d16.maxAreaOfIsland(ex);
        String one = "1111";
        int tw1 = one.charAt(0) -'0';
        int tw2 = one.charAt(0);
        char tw3 = one.charAt(0);
        boolean th;
        //int y = ((node.charAt(i) - '0') + d + 10) % 10;

        System.out.println(tw1);//1
        System.out.println(tw2);//49
        System.out.println(tw3);//1
        char nono ='1';
        System.out.println(nono);//1
//        char tw4 = null;


    }
    public int openLock2(String[] deadends, String target) {
        Set<String> dead = new HashSet();
        for (String d: deadends) dead.add(d);

        Queue<String> queue = new LinkedList();
        queue.offer("0000");
        queue.offer(null);

        Set<String> seen = new HashSet();
        seen.add("0000");

        int depth = 0;
        while (!queue.isEmpty()) {
            String node = queue.poll();

            depth++;

            if (node.equals(target)) {
                return depth;
            } else if (!dead.contains(node)) {
                for (int i = 0; i < 4; ++i) {
                    for (int d = -1; d <= 1; d += 2) {
                        int y = ((node.charAt(i) - '0') + d + 10) % 10;
                        String nei = node.substring(0, i) + ("" + y) + node.substring(i+1);
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            queue.offer(nei);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
