package test.Info.element;

import org.junit.Test;
import work.Info.element.prio;

import static org.junit.Assert.assertTrue;
import static work.Info.element.prio.same;

/**
 * Created by ting huang on 11/3/2016.
 */
public class prioTest {
    private prio median = prio.median;
    private prio high  = prio.high;
    private prio low = prio.low;

    @Test
    public void testSame(){
        assertTrue(same(low,low) == 0);
        assertTrue(same(low,median) == 2);
        assertTrue(same(low,high) == 2);
    }
    @Test
    public void testSame1(){
        assertTrue(same(median,low) == 1);
        assertTrue(same(median,median) == 0);
        assertTrue(same(median,high) == 2);
    }
    @Test
    public void testSame2(){
        assertTrue(same(high,low) == 1);
        assertTrue(same(high,median) == 1);
        assertTrue(same(high,high) == 0);
    }

}
