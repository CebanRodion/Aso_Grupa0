package lab4;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Library {
    static int booksToRead;
    Writer[] writers;
    Reader[] readers;

    static ArrayList<String> libraryStore = new ArrayList<>();
    static final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock(true);
    static final Lock writerLock = rwl.writeLock();
    static final Lock readerLock = rwl.readLock();


    private static final Logger logger = Logger.getLogger(Library.class.getName());

    public Library (int writers, int readers, int books) {
        this.writers = new Writer[writers];
        this.readers = new Reader[readers];
        Library.booksToRead = books;

        logger.log(Level.INFO, "Cream  Scriitorii");
        for (int i = 0; i < writers; i++) {
            this.writers[i] = new Writer("Scriitorul# " + (i+1));
        }

        logger.log(Level.INFO, "Cream  Cititorii");
        for (int i = 0; i < readers; i++) {
            this.readers[i] = new Reader("Cititorul# " + (i+1));
        }
    }

    public void start() {
        logger.log(Level.INFO, "Startam  Cititorii");
        for (Writer writer : this.writers) {
            writer.start();
        }
        logger.log(Level.INFO, "Startam Scriitorii");
        for (Reader reader : this.readers) {
            reader.start();
        }

    }

}
