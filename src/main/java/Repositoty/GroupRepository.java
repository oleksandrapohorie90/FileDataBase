package Repositoty;

import Entities.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository implements IRepository<Group> {

    private final String FILENAME;

    public GroupRepository(String fileName) {
        if (fileName.isEmpty()) {
            FILENAME = "academyGroup";
        } else {
            FILENAME = fileName;
        }

        createNewFileIfNew();
    }

    @Override
    public List<Group> GetAll() {
        //we try to always read from the file and if updated anywhere we update from GetAll() result
        List<Group> groups = new ArrayList<>();
        try {
            FileInputStream fIn = new FileInputStream(FILENAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILENAME));
            groups = (List<Group>) objectInputStream.readObject();
        } catch (EOFException e) {

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error when we load academy geoups from the file: " + FILENAME);
        }

        return groups;
    }

    @Override
    public Group GetById(int id) {
//return groups.stream().filter(group -> group.getId() == id).findFirst().orElse(null);
        List<Group> groups = GetAll();
        for (Group group : groups) {
            if (group.getId() == id) {
                return group;
            }
        }
        return null;
    }

    @Override
    public void Update(Group groupToUpdate) {
        //1 way
        List<Group> groups = GetAll();
        for (Group group : groups) {
            if (group.getId() == groupToUpdate.getId()) {
                group.setName(groupToUpdate.getName());
                group.setDiscordLink(groupToUpdate.getDiscordLink());
                group.setAcademyId(groupToUpdate.getAcademyId());
            }
        }

        SaveChanges(groups);
    }

    @Override
    public void Remove(int id) {
        Group groupToRemove = this.GetById(id);
        List<Group> groups = GetAll();
        groups.remove(groupToRemove);
        SaveChanges(groups);
    }

    @Override
    public void Add(Group newGroup) {
        List<Group> groups = GetAll();
        //each time we take academy group we update it and save it to the file
        groups.add(newGroup);
        SaveChanges(groups);
    }

    public void SaveChanges(List<Group> groups) {
        //serialize the object
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            objectOutputStream.writeObject(groups);
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
//                this.academyGroups = (List<Group>) objectIn.readObject();
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
