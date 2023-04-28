package lab2;

import java.util.Random;


public class Producer implements Runnable {

    private Thread thread;
    private DepozitInterface buffer;
    private int prodNum;
    private static int maxProduction = 49;
    private static int startProduction = 0;
    private static int[] production = new int[]{2,4,6,8,10,12,14,16};

    public Producer (DepozitInterface buffer, String name,int prodNum)
    {
        this.thread = new Thread(this,name);
        this.buffer = buffer;
        this.prodNum = prodNum;
    }
 //   atata timp cat numarul produs e mai mic ca 31 Producatorii produc
    public void start()
    {
        this.thread.start();
    }
    public void run() {

        Random randElement = new Random();


            while(startProduction < maxProduction)
            {
                if (startProduction == 48) {
                    int randomElement = randElement.nextInt(0,7);
                   int[] products = new int[1];
                   products[0] = production[randomElement];
                   buffer.put(products,thread.getName());
                }else
                {
                    int randomElement;
                    int[] products = new int[prodNum];
                    for (int i = 0; i < prodNum; i++)
                    {
                        randomElement = randElement.nextInt(0,7);
                        products[i] = production[randomElement];
                    }
                    buffer.put(products,thread.getName());
                }
            }
            System.out.println(thread.getName() + " a terminat lucrul");

    }

    public static int incrementStartProduction(int num)

    {
        return startProduction +=num;
    }

    public static boolean planOfWork()

    {
        return startProduction>=maxProduction;
    }
}
