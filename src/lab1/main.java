package lab1;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Alegeti care interfata sa fie aratata: 1 - Rodion, 2 - Ion, 3 - Andrei, 4 - Finiseaza programul");
            int value = input.nextInt();
            switch (value) {
                case 1:
                    new TimerIon();
                    break;
                case 2:
                    var rodion = new TimerRodion();
                    rodion.RodionInterface();
                    break;
                case 3:
                    new TimerAndrei();
                    break;
                case 4:
                    System.exit(1);
                default:
                    break;
            }
        }
    }
}
