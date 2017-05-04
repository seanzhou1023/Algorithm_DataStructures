package priorityqueue;

import java.util.*;

/**
 * Created by eric on 9/25/16.
 * key: priority queue kind of slow(250-300ms), treemap is faster(30-50ms)
 * other methods: define new data structures
 */
public class T218_The_Skyline_Problem {
    static class Points{
        int x, y;
        boolean isStart;
        Points(int x, int y, boolean isStart){
            this.x = x;
            this.y = y;
            this.isStart = isStart;
        }
    }
    public List<int[]> getSkylinePriorityQueue(int[][] buildings){
        List<int[]> res = new ArrayList<>();
        List<Points> points = new ArrayList<>();
        for(int[] building:buildings){
            Points p1 = new Points(building[0],building[2],true);
            Points p2 = new Points(building[1],building[2],false);
            points.add(p1);points.add(p2);
        }
        points.sort(new Comparator<Points>() {
            @Override
            public int compare(Points p1, Points p2) {
                if(p1.x!=p2.x) return p1.x-p2.x;
                else{
                    if(p1.isStart && p2.isStart) return p2.y-p1.y;
                    else if(!p1.isStart && !p2.isStart) return p1.y-p2.y;
                    else return p1.isStart?-1:1;
                }
            }
        });
        TreeSet<Integer> pq = new TreeSet<>(Collections.reverseOrder());
        pq.add(0);
        for(int i=0;i<points.size();i++){//O(n^2)
            Points cur = points.get(i);
            int preMax = pq.first();
            if(cur.isStart){
                pq.add(cur.y);
                if(preMax!=pq.first()) res.add(new int[]{cur.x,cur.y});

            }else{
                pq.remove(cur.y);//priority queue remove op takes O(n)
                if(preMax!=pq.first()) res.add(new int[]{cur.x,pq.first()});
            }
        }
        return res;
    }
    public List<int[]> getSkylineTreeMap(int[][] buildings){
        List<int[]> res = new ArrayList<>();
        List<Points> points = new ArrayList<>();
        for(int[] building:buildings){
            Points p1 = new Points(building[0],building[2],true);
            Points p2 = new Points(building[1],building[2],false);
            points.add(p1);points.add(p2);
        }
        points.sort(new Comparator<Points>() {
            public int compare(Points p1, Points p2) {
                if(p1.x!=p2.x) return p1.x-p2.x;
                else{
                    if(p1.isStart && p2.isStart) return p2.y-p1.y;
                    else if(!p1.isStart && !p2.isStart) return p1.y-p2.y;
                    else return p1.isStart?-1:1;
                }
            }
        });
        TreeMap<Integer,Integer> tm = new TreeMap<>();
        tm.put(0,1);
        for(Points cur:points){
            int preMax = tm.lastKey();
            Integer freq = tm.get(cur.y);
            if(cur.isStart){
                if(freq!=null) tm.put(cur.y,freq+1);
                else tm.put(cur.y,1);
                if(preMax!=tm.lastKey()) res.add(new int[]{cur.x,cur.y});
            }else{
                if(freq!=null && freq>1) tm.put(cur.y,freq-1);
                else tm.remove(cur.y);
                if(preMax!=tm.lastKey()) res.add(new int[]{cur.x,tm.lastKey()});
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        List<int[]> res = (new T218_The_Skyline_Problem()).getSkylineTreeMap(buildings);
        for(int[] r:res)
            System.out.println(Arrays.toString(r));
    }
}

