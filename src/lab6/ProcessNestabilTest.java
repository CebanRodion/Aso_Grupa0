package lab6;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProcessNestabilTest {

    @Test
    public void testConstructor() {
        ProcessNestabil process = new ProcessNestabil(1);
        assertEquals(1, process.pid);
    }


    @Test
    public void testRun() {
        ProcessNestabil process = new ProcessNestabil(1);
        Thread processThread = new Thread(process);
        processThread.start();
        try {
            processThread.join();
        } catch (InterruptedException e) {
            fail("Process was interrupted");
        }
    }
}
