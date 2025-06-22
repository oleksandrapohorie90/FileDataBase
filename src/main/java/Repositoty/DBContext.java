package Repositoty;
import java.io.*;

public class DBContext {

    private final String FILENAME;

    public DBContext(String fileName) {
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
        //serialize the object
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            objectOutputStream.writeObject(dbSet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DbSet GetDatabase() {
        //we try to always read from the file and if updated anywhere we update from GetAll() result
        DbSet dbSet = new DbSet();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME))) {
            dbSet = (DbSet) objectInputStream.readObject();
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return dbSet;
    }
}
