package lab3;

import java.util.concurrent.CyclicBarrier;

public class Consumer extends Thread {

    CyclicBarrier barr;
    private StockInterface depozit;
    private String name;
    static int consumed = 0;

    public Consumer(StockInterface depozit, CyclicBarrier barr, String name) {
        this.depozit = depozit;
        this.name = name;
        this.barr = barr;
    }

    public void run() {
        int tmp;
        int cons = 0;
        while(true) {
            try {
                if (consumed < 48) {
                    tmp = depozit.get(name);
                    if (tmp == 0) {
                        barr.await();
                    } else {
                        System.out.println("Consumatorul#" + name + " a consumat " + tmp);
                        cons++;
                        consumed++;
                        System.out.println("S-au consumat: " + consumed + " elemente");
                        sleep((int) (Math.random() * 1500));
                    }
                } else {
                    tmp = depozit.get(name);
                    sleep(3000);
                    System.out.println("Consumatorul#" + name+" a consumat in total "+cons+" de elemente");
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
