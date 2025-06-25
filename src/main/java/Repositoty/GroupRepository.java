package Repositoty;

import Entities.Group;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GroupRepository implements IRepository<Group> {
    DBContext dbContext;

    public GroupRepository(DBContext dbContext) {
        this.dbContext = dbContext;
    }

    @Override
    public List<Group> GetAll() {
        //we try to always read from the file and if updated anywhere we update from GetAll() result
        DbSet dbSet = dbContext.GetDatabase();
        return dbSet.getGroups();
    }

    public Map<Integer, Set<Group>> GetAcademyNCIndex() {
        DbSet dbSet = dbContext.GetDatabase();
        return dbSet.getAcademyNCIndexInGroupTable();
    }

    //Select groupNAME from Group where AcademyId=1
    public Set<Group> GetByAcademyId(Integer academyId) {
        Map<Integer, Set<Group>> index = GetAcademyNCIndex();
        return index.get(academyId);
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
        //update index, set to get some unique data
        Map<Integer, Set<Group>> index = GetAcademyNCIndex();
        Set<Group> academyGroups = index.get(groupToUpdate.getAcademyId());
        academyGroups.add(groupToUpdate);
        SaveChanges(groups, index);
    }

    @Override
    public void Remove(int id) {
        Group groupToRemove = this.GetById(id);
        List<Group> groups = GetAll();
        Map<Integer, Set<Group>> index = GetAcademyNCIndex();
        groups.remove(groupToRemove);
        index.get(groupToRemove.getAcademyId()).removeIf(group -> group.getId() == groupToRemove.getId());
        groups.remove(groupToRemove);
        SaveChanges(groups, index);
    }

    @Override
    public void Add(Group newGroup) {
        List<Group> groups = GetAll();
        Map<Integer, Set<Group>> index = GetAcademyNCIndex();
        //each time we take academy group we update it and save it to the file
        groups.add(newGroup);
        index.get(newGroup.getAcademyId()).add(newGroup);
        SaveChanges(groups, index);
    }

    public void SaveChanges(List<Group> groups, Map<Integer, Set<Group>> index) {
        //serialize the object
        DbSet dbSet = dbContext.GetDatabase();
        dbSet.setGroups(groups);
        dbSet.setAcademyNCIndexInGroupTable(index);
        dbContext.SaveChanges(dbSet);
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
