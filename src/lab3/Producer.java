package lab3;

import java.util.concurrent.CyclicBarrier;

public class Producer extends Thread{

    private CyclicBarrier barr;
    private StockInterface depozit;
    private String name;
    private int[] dep = new int []{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32};
    static int produced = 0;

    public Producer(StockInterface depozit, CyclicBarrier barr, String name) {
        this.depozit = depozit;
        this.name = name;
        this.barr = barr;
    }

    public void run() {
        int tmp;
        boolean response;
        int prod = 0;

        while(true){
            try {
                if(produced < 48) {
                    tmp = (int) (Math.random() * 5);
                    response = depozit.put(dep[tmp], name);
                    if(response) {
                        barr.await();
                    }
                    else {
                        System.out.println("Producatorul#" + name + " a produs " + dep[tmp]);
                        prod++;
                        produced++;
                        sleep((int) (Math.random() * 1500));
                    }
                }
                else {
                    tmp = (int) (Math.random() * 5);
                    response = depozit.put(dep[tmp], name);
                    if(response) {
                        barr.await();
                    }
                    sleep(3000);
                    System.out.println("Producatorul#" + name+ " a produs in total "+prod+" elemente");
                    break;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}