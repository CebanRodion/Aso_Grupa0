package lab7;

import java.util.concurrent.locks.ReentrantLock;

public class Client extends Thread{
    static boolean[] isBarberFree, isChairFree;
    static EndWorkDay end;
    private String barberName;
    private static final int NO_FREE_BARBER = -1;
    private static final int NO_FREE_CHAIR = -1;
    private final ReentrantLock barberLock = new ReentrantLock();
    private final ReentrantLock chairLock = new ReentrantLock();

    public Client(boolean[] isBarberFree, boolean[] isChairFree, EndWorkDay end){
        Client.isBarberFree = isBarberFree;
        Client.isChairFree = isChairFree;
        Client.end = end;
    }

    @Override
    public void run(){
        try {
            while(true){
                int timeSleep = (int)(Math.random()*1000+5000);
                sleep(timeSleep);
                if(!end.isAlive()){
                    System.out.println("!!!!!!" + getName() + " nu poate fi servit, ziua de lucru s-a sfirsit ");
                    break;
                }
                System.out.println(getName() + " a intrat in frezerie");
                int i = checkBarber();
                if(i!=NO_FREE_BARBER){
                    barberName = "Barber " + (i+1);
                    System.out.println(getName() + " s-a asezat in fotoliu " + (i+1) + " si este servit de catre " + barberName);
                    timeSleep = (int)(Math.random()*10000+5000);
                    sleep(timeSleep);
                    System.out.println(getName() + " a eliberat fotoliu " + (i+1) + " si a iesit din frezerie");
                    isBarberFree[i] = true;
                    break;
                }else{
                    int j = checkChair();
                    if(j!=NO_FREE_CHAIR){
                        System.out.println(getName() + " a ocupat locul de asteptare " + (j+1) + " si asteapta rindul");
                        int b = NO_FREE_BARBER;
                        while(b==NO_FREE_BARBER){
                            sleep(1);
                            b = checkBarber();
                        }
                        barberName = "Barber " + (b+1);
                        isChairFree[j] = true;
                        System.out.println(getName() + " s-a asezat in fotoliu " + (b+1) + " si este deservit de catre " + barberName);
                        timeSleep = (int)(Math.random()*10000+5000);
                        sleep(timeSleep);
                        System.out.println(getName() + " a eliberat fotoliu " + (b+1) + " si a iesit din frezerie");
                        isBarberFree[b] = true;
                        break;
                    }else{
                        System.out.println(getName() + " nu sunt locuri de asteptare, paraseste frezeria");
                        sleep(2000);
                    }
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }
}
