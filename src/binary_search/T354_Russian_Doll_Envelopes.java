package binary_search;

import java.util.Arrays;

/**
 * Created by eric on 5/4/16.
 * https://leetcode.com/problems/russian-doll-envelopes
 * time complexity - O(nlogn)
 */
public class T354_Russian_Doll_Envelopes {
//    Method: find the longest increasing subsequence O(nlogn)
//    binary search + radix sort
    static class Envelope{
        int w, h;
        Envelope(int width, int height){
            w = width; h = height;
        }
    }
    public void radixSort(Envelope[] E){
        int minW = Integer.MAX_VALUE, maxW = Integer.MIN_VALUE;
        int minH = Integer.MAX_VALUE, maxH = Integer.MIN_VALUE;
        for(Envelope e: E){
            minW = minW<e.w?minW:e.w; maxW = maxW>e.w?maxW:e.w;
            minH = minH<e.h?minH:e.h; maxH = maxH>e.h?maxH:e.h;
        }
        for(int pos=1;pos>=0;pos--){
            int len = (pos==1?maxH-minH:maxW-minW)+1;
            int[] count = new int[len+1];
            for(Envelope e: E)
                count[(pos==1?e.h-minH:e.w-minW)+1]++;
            for(int i=0;i<count.length-1;i++)
                count[i+1] += count[i];
            Envelope[] aux = new Envelope[E.length];
            for(Envelope e: E)
                aux[count[pos==1?e.h-minH:e.w-minW]++] = e;
            for(int i=0;i<E.length;i++)
                E[i] = aux[pos==1?E.length-1-i:i];
        }
    }
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes==null || envelopes.length<1) return 0;
        Envelope[] E = new Envelope[envelopes.length];
        for(int i=0;i<E.length;i++)
            E[i] = new Envelope(envelopes[i][0],envelopes[i][1]);
        radixSort(E);
        int len = 0;
        int[] dp = new int[E.length];
        for(Envelope e: E){
            int idx = Arrays.binarySearch(dp,0,len,e.h);
            if(idx<0) idx = -(idx+1);
            dp[idx] = e.h;
            if(idx==len) len++;
        }
        return len;
    }
}
