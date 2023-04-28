package lab3;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.LockSupport;
// daca exista un fir de executie care asteapta, il deblocheaza

public class StockIon implements StockInterface{
    private ArrayList<Integer> stock3 = new ArrayList<>();

    boolean response = false;

    public boolean put (int n, String name) {
        try {
            LockSupport.unpark(Thread.currentThread());

        } catch (NoSuchElementException e){
            //e.printStackTrace();
        }

        if (response) {
            LockSupport.park();
            return  response;
        }
        if (stock3.size() <= 7) {
            stock3.add(n);
            //LockSupport.park();
        } else if (stock3.size() == 8) {
            response = true;
            System.out.println("-----Depozitul este plin-----");
            LockSupport.park();
        } else {
            System.out.println("Eroare");
            LockSupport.park();
        }
        return response;
    }

    public int get (String name) {
        int character = 0;
        try {
            LockSupport.unpark(Thread.currentThread());
        } catch (NoSuchElementException e){
            //e.printStackTrace();
        }catch (RuntimeException e) {
            e.printStackTrace();
        }


        if ((response)) {
            if (stock3.isEmpty()){
                response=false;
                System.out.println("-----Depozitul este gol-----");
                LockSupport.park();
                //character = 0;
                return character;
            }

            character = stock3.get(0);
            stock3.remove(0);


        }
        LockSupport.park();
        return character;
    }
}