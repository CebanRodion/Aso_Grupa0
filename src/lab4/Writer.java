package lab4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Writer extends Thread{
    String writerName;
    String[] array = {"Padurea Spanzuratilor", "Clopotnita", "Baltagul", "Tema pentru acasa"};
    ArrayList<String> bookArray = new ArrayList<>(Arrays.asList(array));

    public final Lock writerLock = Library.writerLock;
    ArrayList<String> library = Library.libraryStore;
    ArrayList<String> writtenBooksArray = new ArrayList<>();
    static int count = 0;

    private static final Logger logger = Logger.getLogger(Writer.class.getName());

    public Writer (String name) {
        this.writerName = name;
    }

    @Override
    public void run() {

        while(library.size() < Library.booksToRead){
            try {
                writerLock.lock();
                if (library.size() < Library.booksToRead){
                    String randomBookFromLibrary = bookArray.get(count);
                    if (!library.contains(randomBookFromLibrary)){
                        sleep(1000);
                        library.add(randomBookFromLibrary);
                        writtenBooksArray.add(randomBookFromLibrary);
                        System.out.println("-------" + writerName+ " a scris " + randomBookFromLibrary + "-------");
                        logger.log(Level.INFO, "Scriitorul: " + writerName + " a scris cartea: " + randomBookFromLibrary);
                        System.out.println("Scriitorul " + writerName + " a scris cartea ");
                        count++;
                        sleep(100);
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                writerLock.unlock();
            }
            if (library.size() == Library.booksToRead){
                System.out.println("-------" + writerName + " a scris lista de carti: -------\n" + writtenBooksArray);
            }
        }

    }
}
