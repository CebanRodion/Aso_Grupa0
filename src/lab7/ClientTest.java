package Lab7;

import org.junit.Test;
import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void checkBarber() {
        boolean[] isBarberFree = {true, false, true};
        boolean[] isChairFree = {true, false, true};
        EndWorkDay endWorkDay = new EndWorkDay();
        Client client = new Client(isBarberFree, isChairFree, endWorkDay);

        assertEquals(0, client.checkBarber());
    }

    @Test
    public void testCheckChair() {
        boolean[] isBarberFree = {true, true};
        boolean[] isChairFree = {true, true};
        Laborator7.EndWorkDay end = new Laborator7.EndWorkDay();
        Laborator7.Client client = new Laborator7.Client(isBarberFree, isChairFree, end);
        int chairIndex = client.checkChair();
        assertTrue(chairIndex >= 0 && chairIndex < isChairFree.length);
    }

    @Test
    public void isWorkDayEnded() throws InterruptedException {
        EndWorkDay endWorkDay = new EndWorkDay();
        endWorkDay.start();
        Thread.sleep(21000);
        assertTrue(endWorkDay.isWorkDayEnded());
    }
}
