package dynamic_programming;

/**
 * Created by eric on 6/9/16.
 */
public class T072_Edit_Distance {
//    bottom up version: O(n^2)
    public int minDistance(String word1, String word2){
        int l1 = word1.length(), l2 = word2.length();
        if(l1==0 || l2==0) return l1==0?l2:l1;
        int[][] res = new int[l1+1][l2+1];
        for(int i=0;i<l1;i++) res[i][l2] = l1-i;
        for(int i=0;i<l2;i++) res[l1][i] = l2-i;
        for(int i=l1-1;i>=0;i--){
            for(int j=l2-1;j>=0;j--){
                if(word1.charAt(i)==word2.charAt(j))
                    res[i][j] = res[i+1][j+1];
                else{
                    res[i][j] = res[i+1][j+1]<res[i][j+1]?res[i+1][j+1]:res[i][j+1];
                    res[i][j] = res[i][j]<res[i+1][j]?res[i][j]:res[i+1][j];
                    res[i][j]++;
                }
            }
        }
        return res[0][0];
    }
}
