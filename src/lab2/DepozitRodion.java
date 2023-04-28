package lab2;

import java.util.LinkedList;
import java.util.Queue;
public class DepozitRodion implements DepozitInterface
{

    private int sizeStorage;
    private Queue<Integer> storage = new LinkedList<>();

    public DepozitRodion(int size)
    {
        sizeStorage = size;
    }

    @Override
    public synchronized void put(int[] var1, String threadName)
    {
        if (storage.size() >= sizeStorage)
        {
            System.out.println("Storage is full");
        }
        while (storage.size() >= sizeStorage)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (Producer.planOfWork())
            return;

        for (var i : var1)
        {
            storage.add(i);
        }

        int i = Producer.incrementStartProduction(var1.length);
        System.out.println("S-au produs " + i);

        if (var1.length == 2)
        {
            System.out.println(threadName + " added to buffer: " + var1[0] + ", " + var1[1]);
            System.out.println("\t" + threadName + " added 2 products");
        }
        if (var1.length == 1)
        {
            System.out.println(threadName + " added to buffer: " + var1[0]);
            System.out.println("\t" + threadName + " added 1 products");
        }

        notifyAll();
    }

    @Override
    public synchronized int[] get(String threadName)
    {
        if (storage.isEmpty())
        {
            System.out.println("Storage is empty");
        }

        while (storage.isEmpty())
        {
            try
            {
                wait();
                if(Consumer.producedPlan())
                    return null;
            }catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }

        int[] products;
        if (Consumer.getElementsConsumed() == 48)
        {
            products = new int[1];
            products[0] = storage.poll();
            System.out.println(threadName + " consumed from buffer: " + products[0]);
            System.out.println("\t" + threadName + " consumed 1 product");
        }
        else{
            products = new int[2];
            products[0] = storage.poll();
            products[1] = storage.poll();

            System.out.println(threadName + " consumed from buffer: " + products[0] + ", " + products[1]);
            System.out.println("\t" + threadName + " consumed 2 products");
        }

        int i = Consumer.incElementsConsumed(products.length);
        System.out.println("Au fost consumate " + i);

        notifyAll();

        return products;
    }
}
