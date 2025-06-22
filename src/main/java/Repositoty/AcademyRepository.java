package Repositoty;

import Entities.Academy;
import Entities.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AcademyRepository implements IRepository<Academy> {
    private final String FILENAME;

    public AcademyRepository(String fileName) {
        if (fileName.isEmpty()) {
            FILENAME = "academyGroup";
        } else {
            FILENAME = fileName;
        }

        createNewFileIfNew();
    }

    @Override
    public List<Academy> GetAll() {
        //we try to always read from the file and if updated anywhere we update from GetAll() result
        List<Academy> academies = new ArrayList<>();
        try {
            FileInputStream fIn = new FileInputStream(FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME));
            academies = (List<Academy>) objectInputStream.readObject();
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error when we load academy geoups from the file: " + FILENAME);
        }

        return academies;
    }

    @Override
    public Academy GetById(int id) {
//return groups.stream().filter(group -> group.getId() == id).findFirst().orElse(null);
        List<Academy> academies = GetAll();
        for (Academy academy : academies) {
            if (academy.getId() == id) {
                return academy;
            }
        }
        return null;
    }

    @Override
    public void Update(Academy academyToUpdate) {
        //1 way
        List<Academy> academies = GetAll();
        for (Academy academy : academies) {
            if (academy.getId() == academyToUpdate.getId()) {
                academy.setDescription(academyToUpdate.getDescription());
            }
        }

        SaveChanges(academies);
    }

    @Override
    public void Remove(int id) {
        Academy academyToRemove = this.GetById(id);
        List<Academy> academies = GetAll();
        academies.remove(academyToRemove);
        SaveChanges(academies);
    }

    @Override
    public void Add(Academy newGroup) {
        List<Academy> academies = GetAll();
        //each time we take academy group we update it and save it to the file
        academies.add(newGroup);
        SaveChanges(academies);
    }

    public void SaveChanges(List<Academy> academies) {
        //serialize the object
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            objectOutputStream.writeObject(academies);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
