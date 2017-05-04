package dynamic_programming;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by eric on 5/4/17.
 */
public class T072_Edit_DistanceTest {
    @Test
    public void minDistance() throws Exception {
        T072_Edit_Distance t = new T072_Edit_Distance();
        String word1 = "EricCartman", word2 = "ErikCartwoman";
        assertEquals(t.minDistance(word1,word2),3);
    }

}