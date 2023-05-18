package lab7;


import java.util.Scanner;
import static java.lang.Thread.sleep;

public class Barber {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Dati numarul de barbieri:");
        int b = sc.nextInt();
        boolean[] isBarberFree = new boolean[b];
        for(int i = 0; i < b; i++){
            isBarberFree[i] = true;
        }
        System.out.println("Dati numarul de locuri de asteptare pentru clienti:");
        int c = sc.nextInt();
        boolean[] isChairFree = new boolean[c];
        for(int i = 0; i < c; i++){
            isChairFree[i] = true;
        }
        EndWorkDay end = new EndWorkDay();
        end.start();
        int i = 1;
        while(!end.isWorkDayEnded()){
            Client c1 = new Client(isBarberFree, isChairFree, end);
            c1.setName("Client# " + i);
            i++;
            c1.start();
            if(i>20){
                sleep(2500);
            }
        }
    }
}

