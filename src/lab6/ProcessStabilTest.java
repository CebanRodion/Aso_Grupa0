package lab6;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProcessStabilTest {

    @Test
    public void testConstructor() {
        ProcessStabil process = new ProcessStabil(1);
        assertEquals(1, process.pid);
    }


    @Test
    public void testRun() {
        ProcessStabil process = new ProcessStabil(1);
        Thread processThread = new Thread(process);
        processThread.start();
        try {
            processThread.join();
        } catch (InterruptedException e) {
            fail("Process was interrupted");
        }
    }
}