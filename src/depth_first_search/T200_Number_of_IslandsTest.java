package depth_first_search;

import static org.junit.Assert.*;

/**
 * Created by eric on 3/10/17.
 */
public class T200_Number_of_IslandsTest {
    char[][] nums1 = {{'1','1','0','1','1'},
                      {'1','1','0','1','1'},
                      {'0','1','0','0','1'},
                      {'0','0','0','1','0'}};
    char[][] nums2 = {{'1','1','0'},
                      {'0','0','1'},
                      {'1','1','0'}};
    @org.junit.Before
    public void setUp() throws Exception {
//        the method doesn't modify the original data
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void numIslands1() throws Exception {
        T200_Number_of_Islands t1 = new T200_Number_of_Islands();
        assertEquals(t1.numIslands1(nums1),3);
        assertEquals(t1.numIslands1(nums2),3);
    }

    @org.junit.Test
    public void numIslands2() throws Exception {
        T200_Number_of_Islands t2 = new T200_Number_of_Islands();
        assertEquals(t2.numIslands2(nums1),3);
        assertEquals(t2.numIslands2(nums2),3);
    }

    @org.junit.Test
    public void numIslandsWithShapeChecking() throws Exception {
        T200_Number_of_Islands t3 = new T200_Number_of_Islands();
        assertEquals(t3.numIslandsWithShapeChecking(nums1),2);
        assertEquals(t3.numIslandsWithShapeChecking(nums2),2);
    }

}