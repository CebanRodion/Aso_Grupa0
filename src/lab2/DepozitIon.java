package lab2;

import java.util.LinkedList;
import java.util.Queue;

//Fiecare producator produce cate doua obiecte de fiecare data si
//fiecare consumator consuma cate un obiect de fiecare data
public class DepozitIon implements DepozitInterface
{

    private int sizeStorage;
    private Queue<Integer> storage = new LinkedList<>();


    public DepozitIon(int size)
    {
        sizeStorage = size;
    }

    public synchronized void put(int[] products, String threadName)
    {
        if(storage.size() >= sizeStorage)
        {
            System.out.println("Storage is full");
        }
        while(storage.size() >= sizeStorage)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        if(Producer.planOfWork())
            return;

        for (var i: products)
        {
            storage.add(i);
        }
        int i = Producer.incrementStartProduction(products.length);
        System.out.println("S-au produs" +i);

        if(products.length == 2)
        {
            System.out.println(threadName +"added to buffer" + products[0] +","+products[1]);
            System.out.println("\t" + threadName + "added 2 products");
        }
        if(products.length == 1)
        {
            System.out.println(threadName + " added to buffer: " + products[0]);
            System.out.println("\t" + threadName + " added 1 product");
        }
        notifyAll();

    }

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
                if (Consumer.producedPlan())
                    return null;
            }
            catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
        int[] products;
            products = new int[1];
            products[0] = storage.poll();
            System.out.println(threadName + " consumed from buffer: " + products[0]);
            System.out.println("\t" + threadName + " consumed 1 products");

        int i = Consumer.incElementsConsumed(1);
        System.out.println("S-au consumat " + i);

        notifyAll();

        return products;
    }
}



























