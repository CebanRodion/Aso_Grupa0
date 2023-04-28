package lab3;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class StockAndrei implements StockInterface {
    private LinkedList<Integer> stock = new LinkedList<>();
    ReentrantLock producer_lock = new ReentrantLock(true);
    ReentrantLock consumer_lock = new ReentrantLock(true);
    boolean response = false;

    public boolean put (int n, String name) {
        producer_lock.lock();
        if (response) {
            producer_lock.unlock();
            return response;
        }
        if (stock.size() <= 7) {
            stock.add(n);
            producer_lock.unlock();
        } else if (stock.size() == 8) {
            response = true;
            System.out.println("-----Depozitul este plin-----");
            producer_lock.unlock();
        } else {
            System.out.println("Eroare");
            producer_lock.unlock();
        }
        return response;
    }

    public int get (String name) {
        int character = 0;
        consumer_lock.lock();

        if ((response)) {
            if (stock.isEmpty()){
                response=false;
                System.out.println("-----Depozitul este gol-----");
                consumer_lock.unlock();
                return character;
            }
            character = stock.getFirst();
            stock.removeFirst();
        }
        consumer_lock.unlock();
        return character;
    }
}
