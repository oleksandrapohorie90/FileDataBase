package Repositoty;

import Entities.AcademyGroup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AcademyGroupRepository implements IRepository<AcademyGroup> {
    private List<AcademyGroup> academyGroups;
    private final String FILENAME;

    public AcademyGroupRepository(String fileName) {
        academyGroups = new ArrayList<>();

        if (fileName.isEmpty()) {
            FILENAME = "academyGroup";
        } else {
            FILENAME = fileName;
        }

        createNewFileIfNew();
    }

    @Override
    public List<AcademyGroup> GetAll() {
        return academyGroups;
    }

    @Override
    public AcademyGroup GetById(int id) {
//        return academyGroups.stream().filter(group -> group.getId() == id).findFirst().orElse(null);

        for (AcademyGroup academyGroup : academyGroups) {
            if (academyGroup.getId() == id) {
                return academyGroup;
            }
        }
        return null;
    }

    @Override
    public void Update(AcademyGroup group) {
        //1 way
        AcademyGroup academyGroupToUpdate = this.GetById(group.getId());
        academyGroupToUpdate.setName(group.getName());
        academyGroupToUpdate.setDiscordLink(group.getDiscordLink());
    }

    @Override
    public void Remove(int id) {
        AcademyGroup academyGroupToRemove = this.GetById(id);
        academyGroups.remove(academyGroupToRemove);
    }

    @Override
    public void Add(AcademyGroup newGroup) {
        this.academyGroups.add(newGroup);
    }

    @Override
    public void SaveChanges() { //serialize the object
        try {
            FileOutputStream fOut = new FileOutputStream(FILENAME);
            ObjectOutput objectOut = new ObjectOutputStream(fOut);
            objectOut.writeObject(academyGroups);
            objectOut.close();
            fOut.close();
        } catch (IOException e) {
            System.out.println("Error when we save changes to the file: " + FILENAME);
        }
    }

    @Override
    public void LoadFromFile() {//deserialize the object
        try {
            FileInputStream fIn = new FileInputStream(FILENAME);
            ObjectInputStream objectIn = new ObjectInputStream(fIn);

            if (objectIn.available() > 0) {
                this.academyGroups = (List<AcademyGroup>) objectIn.readObject();
            }

            objectIn.close();
            fIn.close();
        } catch (IOException e) {
            System.out.println("Error when we load academy geoups from the file: " + FILENAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
