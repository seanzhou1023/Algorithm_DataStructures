package depth_first_search;

import java.util.*;

/**
 * Created by eric on 3/10/17.
 * if visiting a node cost too much, then maintaining a using boolean function
 * union find
 * follow up: check if two islands are of the same shape
 */
public class T200_Number_of_Islands {
//    Method 1: simple dfs - original matrix unmodified
//    optimization: direction array & stack depth control
    public void explore(int i, int j, char[][] grid, boolean[][] visited){
        //visited, return right away (assuming been visited beyond territory)
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || visited[i][j]) return;
        //unvisited? then visit
        visited[i][j] = true;
        //have to stop exploring when '0' comes across
        if(grid[i][j]=='0') return;
        //exploring the neighbors recursively
        explore(i-1,j,grid,visited);
        explore(i,j+1,grid,visited);
        explore(i+1,j,grid,visited);
        explore(i,j-1,grid,visited);
    }
    public int numIslands1(char[][] grid){
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int cnt = 0;
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++){
                if(!visited[i][j] && grid[i][j]=='1'){
                    explore(i,j,grid,visited);
                    cnt++;
                }
                visited[i][j] = true;
            }
        return cnt;
    }
//    Method 2: dfs + union find labeling
//    key: serialize 2-d array to 1-d array
//    optimization:
    int count;
    final int[] dir = {0,1,0,-1,0};//generate the next step
    private void dfs(char[][] grid, boolean[] visited, int[] id, int i, int j){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || visited[j+i*grid[0].length] || grid[i][j]=='0')
            return ;
        visited[j+i*grid[0].length] = true;
        id[j+i*grid[0].length] = count+1;
        for(int k=0;k<dir.length-1;k++){
            int r = i+dir[k], c = j+dir[k+1];
            dfs(grid,visited,id,r,c);
        }
    }
    public int numIslands2(char[][] grid) {
        if(grid==null || grid.length<1) return 0;
        int row = grid.length, col = grid[0].length;
        count = 0;
        boolean[] visited = new boolean[row*col];
        int[] id = new int[row*col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]=='1' && !visited[j+i*col]){
                    dfs(grid,visited,id,i,j);
                    count++;
                }
            }
        }
        return count;
    }
//    follow up question: how to check the different shape of islands
//    for simplicity, rotation is ignored
//    check the offset of union find label, if the offset for each node
//    in two island is consistent, we consider them the same shape
    public boolean offset(List<Integer> l1, List<Integer> l2){
        int len1 = l1.size(), len2 = l2.size();
        if(len1!=len2) return false;
        int offset = l1.get(0)-l2.get(0);
        for(int i=1;i<len1;i++)
            if(l1.get(i)-l2.get(i)!=offset) return false;
        return true;
    }
    public int checkShape(int[] id){
        List<Integer>[] pos = new ArrayList[count];
        for(int i=0;i<id.length;i++){
            if(id[i]==0) continue;
            if(pos[id[i]-1]==null){
                pos[id[i]-1] = new ArrayList();
            }
            pos[id[i]-1].add(i);
        }
        List<Integer> shape = new ArrayList<>();
        for(int i=0;i<count;i++) shape.add(i);
        for(int i=1;i<pos.length;i++){
            for(int j=0;j<i;j++){
                if(offset(pos[j],pos[i])){
                    shape.set(i,shape.get(j));
                }
            }
        }
        Set<Integer> res = new HashSet<>(shape);
        return res.size();
    }
    public int numIslandsWithShapeChecking(char[][] grid) {
        if(grid==null || grid.length<1) return 0;
        int row = grid.length, col = grid[0].length;
        count = 0;
        boolean[] visited = new boolean[row*col];
        int[] id = new int[row*col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]=='1' && !visited[j+i*col]){
                    dfs(grid,visited,id,i,j);
                    count++;
                }
            }
        }
        int shapes = checkShape(id);
        return shapes;
    }
}
