package lab2;

public interface DepozitInterface {
    void put(int[] var1, String threadName);
    int[] get(String threadName);
}
