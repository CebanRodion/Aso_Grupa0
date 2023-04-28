package lab3;

import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class Main {
    public static void main (String[] args) {
        System.out.println("Alegeti varianta de realizare:");
        System.out.println("1. Ceban Rodion(Semaphore)");
        System.out.println("2. Spatari Andrei(ReentrantLock)");
        System.out.println("3. Ciuchitu Ion(LockSupport)");
        System.out.print(">>> ");

        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        switch(number) {
            case 1:
                StockRodion data = new StockRodion();
                CyclicBarrier bar1 = new CyclicBarrier(6);
                Producer p1 = new Producer(data, bar1, "1");
                Producer p2 = new Producer(data, bar1, "2");
                Producer p3 = new Producer(data, bar1, "3");
                Consumer c1 = new Consumer(data, bar1, "1");
                Consumer c2 = new Consumer(data, bar1, "2");
                Consumer c3 = new Consumer(data, bar1, "3");
                p1.start();
                p2.start();
                p3.start();
                c1.start();
                c2.start();
                c3.start();

                break;
            case 2:

                StockAndrei data1 = new StockAndrei();
                CyclicBarrier bar2 = new CyclicBarrier(6);
                Producer p4 = new Producer(data1, bar2, "1");
                Producer p5 = new Producer(data1, bar2, "2");
                Producer p6 = new Producer(data1, bar2, "3");
                Consumer c4 = new Consumer(data1, bar2, "1");
                Consumer c5 = new Consumer(data1, bar2, "2");
                Consumer c6 = new Consumer(data1, bar2, "3");
                p4.start();
                p5.start();
                p6.start();
                c4.start();
                c5.start();
                c6.start();
                break;

            case 3:
                StockIon data3 = new StockIon();
                CyclicBarrier bar3 = new CyclicBarrier(6);
                Producer p7 = new Producer(data3, bar3, "1");
                Producer p8 = new Producer(data3, bar3, "2");
                Producer p9 = new Producer(data3, bar3, "3");
                Consumer c7 = new Consumer(data3, bar3, "1");
                Consumer c8 = new Consumer(data3, bar3, "2");
                Consumer c9 = new Consumer(data3, bar3, "3");
                p7.start();
                p8.start();
                p9.start();
                c7.start();
                c8.start();
                c9.start();
                break;
            default:
                System.out.println("Asa numar de optiune nu exista");
                break;
        }

    }
}

