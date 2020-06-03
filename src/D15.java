import java.util.LinkedList;
import java.util.Queue;

public class D15 {
    //           x-1,y
    //  x,y-1    x,y      x,y+1
    //           x+1,y
    // 方向数组，它表示了相对于当前位置的 4 个方向的横、纵坐标的偏移量，这是一个常见的技巧
    private final int[][] directions = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    // 标记数组，标记了 grid 的坐标对应的格子是否被访问过
    private boolean[][] marked;
    // grid 的行数
    private int rows;
    // grid 的列数
    private int cols;
    private char[][] grid;

    public int numIslands(char[][] grid) {
        rows = grid.length;
        if (rows == 0) {
            return 0;
        }
        cols = grid[0].length;
        this.grid = grid;
        marked = new boolean[rows][cols];
        int count = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果是岛屿中的一个点，并且没有被访问过
                // 就进行深度优先遍历
                if (!marked[i][j] && grid[i][j] == '1') {
                    count++;
                    dfs3(i, j);
                }
            }
        }
        return count;
    }

    private void dfs3(int i, int j) {
        marked[i][j] = true;
        if(i>=1&&!marked[i-1][j] && grid[i-1][j] == '1') dfs3(i-1,j);
        if(j>=1&&!marked[i][j-1] && grid[i][j-1] == '1') dfs3(i,j-1);
        if(i<rows-1&&!marked[i+1][j] && grid[i+1][j] == '1') dfs3(i+1,j);
        if(j<cols-1&&!marked[i][j+1] && grid[i][j+1] == '1') dfs3(i,j+1);
    }


    // 从坐标为 (i,j) 的点开始进行深度优先遍历
    private void dfs(int i, int j) {
        marked[i][j] = true;
        // 得到 4 个方向的坐标
        for (int k = 0; k < 4; k++) {
            int newX = i + directions[k][0];
            int newY = j + directions[k][1];
            // 如果不越界、没有被访问过、并且还要是陆地
            if (inArea(newX, newY) && grid[newX][newY] == '1' && !marked[newX][newY]) {
                dfs(newX, newY);
            }
        }
    }

    // 封装成 inArea 方法语义更清晰
    private boolean inArea(int x, int y) {
        // 等于号不要忘了
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public static void main(String[] args) {
        D15 solution = new D15();
        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}};
        int numIslands1 = solution.numIslands(grid1);
        System.out.println(numIslands1);

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}};
        int numIslands2 = solution.numIslands(grid2);
        System.out.println(numIslands2);
    }

 /*

  */
 boolean[][] marked2;
 char[][] grid2;
 int rows2;
 int cols2;
 int count2;
 public int numIslands2(char[][] grid) {
     this.grid2=grid;
     rows2=grid.length;
     cols2=grid[0].length;
     count2=0;
     for(int i=0;i<rows2;i++){
         for(int j=0;j<cols2;j++){
             if(marked2[i][j]==false&&grid2[i][j]=='1'){
                 count2++;
                 dfs2(i,j);
             }
         }
     }
     return count2;
 }
    final int[][] direction2 = {{0,1},{0,-1},{1,0},{-1,0}};

    private void dfs2(int i, int j) {
        for(int num=0;num<direction2.length;num++){
            i +=direction2[num][0];
            j+=direction2[num][1];
            if(inArea2(i,j)&&grid2[i][j]=='1'&&marked2[i][j]==false){
                dfs2(i,j);
            }
        }
    }

    private boolean inArea2(int i, int j) {
        return i>=0&&i<rows2&&j<cols2&&j>=0;
    }
    boolean[][] marked3;
    char[][] grid3;
    int rows3;
    int cols3;
    int count3;
    public int numIslands3(char[][] grid) {
        this.grid3=grid;
        rows3=grid.length;
        cols3=grid[0].length;
        count3=0;
        for(int i=0;i<rows3;i++){
            for(int j=0;j<cols3;j++){
                if(marked3[i][j]==false&&grid3[i][j]=='1'){
                    count3++;
                    bfs3(i,j);
                }
            }
        }
        return count3;
    }

    private void bfs3(int i, int j) {
        Queue<Character> queue = new LinkedList <>();
        marked3[i][j] =true;
        queue.offer(grid3[i][j]);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int k=0;k<size;k++){
                queue.poll();
//                for(int num=0;num<direction2.length;num++){
//                    i +=direction2[num][0];
//                    j+=direction2[num][1];
//                    if(inArea2(i,j)&&grid2[i][j]=='1'&&marked2[i][j]==false){
//                        dfs2(i,j);
//                    }
//                }
            }
        }
    }
}
