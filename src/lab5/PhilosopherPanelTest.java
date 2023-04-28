package lab5;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class PhilosopherPanelTest {

    @Test
    public void testPreferredSize() {
        PhilosopherPanel panel = new PhilosopherPanel(Color.RED);
        Dimension expectedSize = new Dimension(50, 50);
        assertEquals(expectedSize, panel.getPreferredSize());
    }

    @Test
    public void testBackgroundColor() {
        Color expectedColor = Color.RED;
        PhilosopherPanel panel = new PhilosopherPanel(expectedColor);
        assertEquals(expectedColor, panel.getBackground());
    }
}
