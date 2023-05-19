package lab6;

import org.junit.Test;
import java.lang.reflect.Field;
import static org.junit.Assert.assertArrayEquals;

public class BancherAlgorithmTest {

    @Test
    public void testStableState() throws Exception {
        BancherAlgorithm bancher = new BancherAlgorithm();
        Field availableStabilField = BancherAlgorithm.class.getDeclaredField("AVAILABLE_STABIL");
        availableStabilField.setAccessible(true);
        int[] expected = (int[]) availableStabilField.get(null);
        int[] actual = bancher.getAvailableStabilResources();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testUnstableState() throws Exception {
        BancherAlgorithm bancher = new BancherAlgorithm();
        Field availableNestabilField = BancherAlgorithm.class.getDeclaredField("AVAILABLE_NESTABIL");
        availableNestabilField.setAccessible(true);
        int[] expected = (int[]) availableNestabilField.get(null);
        int[] actual = bancher.getAvailableNestabilResources();
        assertArrayEquals(expected, actual);
    }
}