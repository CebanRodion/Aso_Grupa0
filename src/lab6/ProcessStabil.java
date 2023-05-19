package lab6;

import java.util.Arrays;

import static lab6.BancherAlgorithm.*;

public class ProcessStabil implements Runnable {
    public int pid;

    public ProcessStabil(int pid) {
        this.pid = pid;
    }

    public void run() {
        boolean[] safeSequenceFound = {false};

        while (!safeSequenceFound[0]) {
            System.out.println("Process " + pid + " is starting");

            int[] request = new int[NUM_RESOURCES];
            for (int i = 0; i < NUM_RESOURCES; i++) {
                request[i] = MAX[pid][i] - ALLOCATED[pid][i];
                //System.out.println("Process " + pid + " is requesting " + request[i] + " of resource " + i);
            }
            System.out.println("Process " + pid + " is requesting " + Arrays.toString(request) + " of resource");

            boolean acquired = tryAcquire(request);

            if (acquired) {
                System.out.println("Process " + pid + " has acquired resources");
                for (int i = 0; i < NUM_RESOURCES; i++) {
                    ALLOCATED[pid][i] += request[i];
                    AVAILABLE_STABIL[i] -= request[i];
                    System.out.println("Resource " + i + " now has " + AVAILABLE_STABIL[i] + " available");
                    resources[i].release(request[i]);
                }

                safeSequenceFound[0] = isSafe();

                if (safeSequenceFound[0]) {
                    System.out.println("Process " + pid + " a primit resurse");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    for (int i = 0; i < NUM_RESOURCES; i++) {
                        ALLOCATED[pid][i] -= request[i];
                        AVAILABLE_STABIL[i] += request[i];
                        System.out.println("Resource " + i + " now has " + AVAILABLE_STABIL[i] + " available");

                    }

                } else {
                    System.out.println("Process " + pid + " is waiting for resources, nu avem resurse destule.");
                    for (int i = 0; i < NUM_RESOURCES; i++) {
                        ALLOCATED[pid][i] -= request[i];
                        AVAILABLE_STABIL[i] += request[i];
                        System.out.println("Resource " + i + " now has " + AVAILABLE_STABIL[i] + " available");
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Process " + pid + " is requesting more resources than i have.");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("Process " + pid + " has completed.");
    }

    public boolean tryAcquire(int[] request) {
        boolean acquired = true;
        for (int i = 0; i < NUM_RESOURCES; i++) {
            if (request[i] > resources[i].availablePermits()) {
                acquired = false;
                System.out.println("Process " + pid + " could not acquire enough of resource " + i);
                break;
            }
        }
        if (acquired) {
            for (int i = 0; i < NUM_RESOURCES; i++) {
                resources[i].acquireUninterruptibly(request[i]);
                //System.out.println("Process " + pid + " has acquired " + request[i] + " of resource " + i);
            }
            System.out.println("Process " + pid + " has acquired " + Arrays.toString(request) + " of resource");
        }
        return acquired;
    }

    public boolean isSafe() {
        boolean[] finished = new boolean[NUM_PROCESSES];
        int[] work = new int[NUM_RESOURCES];

        for (int i = 0; i < NUM_RESOURCES; i++) {
            work[i] = AVAILABLE_STABIL[i];
        }

        int count = 0;
        while (count < NUM_PROCESSES) {
            boolean found = false;

            for (int i = 0; i < NUM_PROCESSES; i++) {
                if (!finished[i]) {
                    boolean canFinish = true;
                    for (int j = 0; j < NUM_RESOURCES; j++) {
                        if (MAX[i][j] - ALLOCATED[i][j] > work[j]) {
                            canFinish = false;
                            System.out.println("Process " + pid + " can't finish due to lack of resource " + j);
                            break;
                        }
                    }
                    if (canFinish) {
                        for (int j = 0; j < NUM_RESOURCES; j++) {
                            work[j] += ALLOCATED[i][j];
                        }
                        finished[i] = true;
                        found = true;
                        count++;
                    }
                }
            }
            if (!found) {
                break;
            }
        }
        return count == NUM_PROCESSES;
    }
}