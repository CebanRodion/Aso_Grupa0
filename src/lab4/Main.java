package lab4;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static final Logger logger = Logger.getLogger("");
    public static FileHandler fileHandler;

    public static void createFile() {
        try{
            File readerLogFile = new File("Laborator4.txt");
            if(readerLogFile.exists())
            {
                readerLogFile.delete();
            }
            readerLogFile.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            fileHandler = new FileHandler("Laborator4.txt", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void main (String[] args) {
        final int writers = 3;
        final int readers = 2;
        final int books = 4;

        createFile();
        Library libraryStore = new Library(writers, readers, books);
        libraryStore.start();
    }
}
