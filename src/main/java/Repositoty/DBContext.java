package Repositoty;
import java.io.*;

public class DBContext {

    private final String FILENAME;
    private boolean containsNewChanges;
    private DbSet dbSet;

    public DBContext(String fileName) {
        containsNewChanges = true;
        if (fileName.isEmpty()) {
            FILENAME = "academyDb";
        } else {
            FILENAME = fileName;
        }

        createNewFileIfNew();
    }

    private void createNewFileIfNew() {
        File file = new File(FILENAME);
        try {
            if (!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("File created " + file.getAbsolutePath());
                } else {
                    System.out.println("Fail to create " + file.getAbsoluteFile());
                }
            }
        } catch (IOException e) {
            System.out.println("Error during creating a file " + e.getMessage());
        }
    }

    public void SaveChanges(DbSet dbSet) {
        containsNewChanges = true;
        //serialize the object
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            objectOutputStream.writeObject(dbSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DbSet GetDatabase() {
        //we try to always read from the file and if updated anywhere we update from GetAll() result
     if(containsNewChanges) {
         dbSet = new DbSet();
         try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
             dbSet = (DbSet) objectInputStream.readObject();
         } catch (IOException | ClassNotFoundException e) {
             e.printStackTrace();
         }
         containsNewChanges = false;
     }

        return dbSet;
    }

}
