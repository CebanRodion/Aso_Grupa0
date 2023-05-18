package lab7;


public class EndWorkDay extends Thread{
    public static int end;
    private boolean workDayEnded = false;
    @Override
    public void run(){
        try {
            sleep(20000);
            workDayEnded = true;
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    public boolean isWorkDayEnded() {
        return workDayEnded;
    }
}
