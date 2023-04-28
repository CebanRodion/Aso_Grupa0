package lab2;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

            System.out.println("Alegeti varianta de realizare:");
            System.out.println("1. Ceban Rodion");
            System.out.println("2. Ciuchitu Ion");
            System.out.println("3. Spatari Andrei");
            System.out.print(">>> ");

            Scanner scanner = new Scanner(System.in);
            int number = scanner.nextInt();

            switch (number) {
                case 1:
                    DepozitRodion buffer1 = new DepozitRodion(8);

                    Producer producer1 = new Producer(buffer1, "Producer-1", 2);
                    Producer producer2 = new Producer(buffer1, "Producer-2", 2);

                    Consumer consumer1 = new Consumer(buffer1, "Consumer-1");
                    Consumer consumer2 = new Consumer(buffer1, "Consumer-2");
                    Consumer consumer3 = new Consumer(buffer1, "Consumer-3");

                    producer1.start();
                    producer2.start();

                    consumer1.start();
                    consumer2.start();
                    consumer3.start();

                    break;
                case 2:
                    DepozitIon buffer2 = new DepozitIon(8);
                    Producer producer3 = new Producer(buffer2, "Producer-3", 2);
                    Producer producer4 = new Producer(buffer2, "Producer-4", 2);

                    Consumer consumer6 = new Consumer(buffer2, "Consumer-3");
                    Consumer consumer4 = new Consumer(buffer2, "Consumer-4");
                    Consumer consumer5 = new Consumer(buffer2, "Consumer-5");

                    producer3.start();
                    producer4.start();

                    consumer6.start();
                    consumer4.start();
                    consumer5.start();
                    break;
                case 3:
                    DepozitAndrei depozitAndrei = new DepozitAndrei(8);

                    Producer producer5 = new Producer(depozitAndrei, "Producer #1", 1);
                    Producer producer6 = new Producer(depozitAndrei, "Producer #2", 1);

                    Consumer consumer7 = new Consumer(depozitAndrei, "Consumer #1");
                    Consumer consumer8 = new Consumer(depozitAndrei, "Consumer #2");
                    Consumer consumer9 = new Consumer(depozitAndrei, "Consumer #3");

                    producer5.start();
                    producer6.start();

                    consumer7.start();
                    consumer8.start();
                    consumer9.start();
                    break;
                default:
                    System.out.println("Asa numar de optiune nu exista");
                    break;
            }

    }
}
