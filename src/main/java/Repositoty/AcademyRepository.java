package Repositoty;

import Entities.Academy;
import Entities.Group;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AcademyRepository implements IRepository<Academy> {
    DBContext dbContext;

    public AcademyRepository(DBContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public Map<Integer, Academy> GetAll() {
        //we try to always read from the file and if updated anywhere we update from GetAll() result
        DbSet dbSet = dbContext.GetDatabase();
        return dbSet.getAcademies();
    }

    @Override
    public Academy GetById(int id) {
//return groups.stream().filter(group -> group.getId() == id).findFirst().orElse(null);
        Map<Integer, Academy> academies = GetAll();
        //full scan you need to go and check all academies
//        for (Academy academy : academies) {
//            if (academy.getId() == id) {
//                return academy;
//            }
//        }
        return academies.get(id); //very fast, we dont look at other rows in table
    }

    @Override
    public void Update(Academy academyToUpdate) {
        //1 way
        Map<Integer, Academy> academies = GetAll();
        academies.get(academyToUpdate.getId()).setDescription(academyToUpdate.getDescription());//we dont need full scan, as we can
        //full scan of all academies
//        for (Academy academy : academies) {
//            if (academy.getId() == academyToUpdate.getId()) {
//                academy.setDescription(academyToUpdate.getDescription());
//            }
//        }

        SaveChanges(academies);
    }

    @Override
    public void Remove(int id) {
        //Academy academyToRemove = this.GetById(id);
        Map<Integer, Academy> academies = GetAll();
        academies.remove(id);
        SaveChanges(academies);
    }

    @Override
    public void Add(Academy newGroup) {
        Map<Integer, Academy> academies = GetAll();
        //each time we take academy group we update it and save it to the file
        if (academies.containsKey(newGroup.getId())) {
            System.out.println("Academy with this id already exists.");
        } else {
            academies.put(newGroup.getId(), newGroup);
        }

        SaveChanges(academies);
    }

    public void SaveChanges(Map<Integer, Academy> academies) {
        //serialize the object
        DbSet dbSet = dbContext.GetDatabase();
        dbSet.setAcademies(academies);
        dbContext.SaveChanges(dbSet);
    }

//    private void createNewFileIfNew() {
//        File file = new File(FILENAME);
//        try {
//            if (!file.exists()) {
//                if (file.createNewFile()) {
//                    System.out.println("File created " + file.getAbsolutePath());
//                } else {
//                    System.out.println("Fail to create " + file.getAbsoluteFile());
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error during creating a file " + e.getMessage());
//        }
//    }
}
