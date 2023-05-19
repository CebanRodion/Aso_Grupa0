package lab6;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class BancherAlgorithm {
    public static final int NUM_PROCESSES = 3;
    public static final int NUM_RESOURCES = 6;

    public static final int[][] MAX = {{3, 3, 2, 2, 2, 3},
            {1, 2, 2, 1, 2, 2},
            {2, 1, 1, 2, 1, 2}};

    public static final int[][] ALLOCATED = {{1, 2, 2, 1, 0, 0},
            {1, 0, 1, 1, 0, 0},
            {0, 1, 0, 0, 1, 0}};

    public static final int[] AVAILABLE_STABIL = {10, 10, 10, 10, 10, 10};
    public static final int[] AVAILABLE_NESTABIL = {4, 4, 4, 4, 4, 4};

    public static Semaphore[] resources = new Semaphore[NUM_RESOURCES];

    public int[] getAvailableStabilResources() {
        return AVAILABLE_STABIL;
    }

    public int[] getAvailableNestabilResources() {
        return AVAILABLE_NESTABIL;
    }


    public static void main(String[] args) {

        Scanner inputValue = new Scanner(System.in);
        System.out.println("Introduceti optiunea dorita: 1 - Stare Stabila, 2 - Stare Nestabila");
        int option = inputValue.nextInt();
        switch (option) {
            case 1:
                for (int i = 0; i < NUM_RESOURCES; i++) {
                    resources[i] = new Semaphore(AVAILABLE_STABIL[i]);
                }
                System.out.println("Stare Stabila");
                Thread[] processes = new Thread[NUM_PROCESSES];
                for (int i = 0; i < NUM_PROCESSES; i++) {
                    processes[i] = new Thread(new ProcessStabil(i));
                    processes[i].start();
                }
                break;
            case 2:
                for (int i = 0; i < NUM_RESOURCES; i++) {
                    resources[i] = new Semaphore(AVAILABLE_NESTABIL[i]);
                }
                System.out.println("Stare Nestabila");
                Thread[] processes1 = new Thread[NUM_PROCESSES];
                for (int i = 0; i < NUM_PROCESSES; i++) {
                    processes1[i] = new Thread(new ProcessNestabil(i));
                    processes1[i].start();
                }
                break;
            default:
                break;
        }
    }
}