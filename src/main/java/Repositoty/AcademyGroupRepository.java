package Repositoty;

import Entities.AcademyGroup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AcademyGroupRepository implements IRepository<AcademyGroup> {

    private final String FILENAME;

    public AcademyGroupRepository(String fileName) {
        if (fileName.isEmpty()) {
            FILENAME = "academyGroup";
        } else {
            FILENAME = fileName;
        }

        createNewFileIfNew();
    }

    @Override
    public List<AcademyGroup> GetAll() {
        //we try to always read from the file and if updated anywhere we update from GetAll() result
        List<AcademyGroup> academyGroups = new ArrayList<>();
        try {
            FileInputStream fIn = new FileInputStream(FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME));
            academyGroups = (List<AcademyGroup>) objectInputStream.readObject();
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error when we load academy geoups from the file: " + FILENAME);
        }

        return academyGroups;
    }

    @Override
    public AcademyGroup GetById(int id) {
//return academyGroups.stream().filter(group -> group.getId() == id).findFirst().orElse(null);
        List<AcademyGroup> academyGroups = GetAll();
        for (AcademyGroup academyGroup : academyGroups) {
            if (academyGroup.getId() == id) {
                return academyGroup;
            }
        }
        return null;
    }

    @Override
    public void Update(AcademyGroup groupToUpdate) {
        //1 way
        List<AcademyGroup> academyGroups = GetAll();
        for (AcademyGroup academyGroup : academyGroups) {
            if (academyGroup.getId() == groupToUpdate.getId()) {
                academyGroup.setName(groupToUpdate.getName());
                academyGroup.setDiscordLink(groupToUpdate.getDiscordLink());
            }
        }

        SaveChanges(academyGroups);
    }

    @Override
    public void Remove(int id) {
        AcademyGroup academyGroupToRemove = this.GetById(id);
        List<AcademyGroup> academyGroups = GetAll();
        academyGroups.remove(academyGroupToRemove);
        SaveChanges(academyGroups);
    }

    @Override
    public void Add(AcademyGroup newGroup) {
        List<AcademyGroup> academyGroups = GetAll();
        //each time we take academy group we update it and save it to the file
        academyGroups.add(newGroup);
        SaveChanges(academyGroups);
    }

    public void SaveChanges(List<AcademyGroup> academyGroups) {
        //serialize the object
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            objectOutputStream.writeObject(academyGroups);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    @Override
//    public void LoadFromFile() {//deserialize the object
//        try {
//            FileInputStream fIn = new FileInputStream(FILENAME);
//            ObjectInputStream objectIn = new ObjectInputStream(fIn);
//
//            if (objectIn.available() > 0) {
//                this.academyGroups = (List<AcademyGroup>) objectIn.readObject();
//            }
//
//            objectIn.close();
//            fIn.close();
//        } catch (IOException e) {
//            System.out.println("Error when we load academy geoups from the file: " + FILENAME);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }

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
