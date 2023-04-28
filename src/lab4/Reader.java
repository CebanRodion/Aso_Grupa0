package lab4;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader extends Thread {
    public final Lock readerLock = Library.readerLock;
    ArrayList<String> readBooksArray = new ArrayList<>();
    ArrayList<String> library = Library.libraryStore;
    String readerName;

    private static final Logger logger = Logger.getLogger(Reader.class.getName());

    public Reader(String name) {
        this.readerName = name;
    }

    @Override
    public void run() {
        while(readBooksArray.size() < Library.booksToRead){
            try {
                if (Library.rwl.isWriteLocked()){
                    System.out.println(readerName + " Atentie!: un scriitor este in librarie");
                }
                readerLock.lock();
                int random = (int)(Math.random()*library.size());
                if(random < library.size()) {
                    String randomBookFromLibrary = library.get(random);

                    if(readBooksArray.size() < Library.booksToRead){
                        if(!readBooksArray.contains(randomBookFromLibrary)){
                            sleep(300);
                            readBooksArray.add(randomBookFromLibrary);
                            logger.log(Level.INFO, "Reader: " + readerName + " read book: " + randomBookFromLibrary);
                            System.out.println("----" + readerName + " a citit cartea " + randomBookFromLibrary + "----");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                readerLock.unlock();
            }
        }
        System.out.println("----" + readerName + " a finalizat citirea cu lista ----\n" + readBooksArray);

    }
}