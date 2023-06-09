package lab2;
//    atata timp cat nu s-au produs 31 obiecte are loc run adica Consumatorul conusuma
public class Consumer implements Runnable{
    private Thread thread;
    private DepozitInterface depozit;
    private static int elementsToConsume = 49;
    private static int elementsConsumed = 0;

    public Consumer(DepozitInterface depozit, String threadName){
        thread = new Thread(this, threadName);
        this.depozit = depozit;
    }

    public void start()
    {
        thread.start();
    }


    public void run() {
        int[] element;
        while (!producedPlan()) {
            element = depozit.get(thread.getName());
        }
        System.out.println("!!!Thread-ul " + thread.getName() + " a finisat activitatea");
    }

    public static int incElementsConsumed(int value)
    {
        return elementsConsumed += value;
    }

    public static int getElementsConsumed()
    {
        return elementsConsumed;
    }

    public static boolean producedPlan() { return  elementsConsumed >= elementsToConsume;}
}
