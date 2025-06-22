import Entities.Academy;
import Entities.Group;
import Repositoty.AcademyRepository;
import Repositoty.DBContext;
import Repositoty.GroupRepository;

public class App {

    public static void main(String[] args) {
        DBContext dbContext = new DBContext("db.file");
        //created some data in memory
//        Academy newAcademy = new Academy(1, "GrowthHungry");
//        Group newGroup = new Group(1, "GrowthHungry batch 26", "discord.com");
//        newGroup.setAcademyId(newAcademy.getId());
//
//        //lets save it to the file
//        AcademyRepository academyRepository = new AcademyRepository(dbContext);
//        academyRepository.Add(newAcademy);
//        //academyRepository.SaveChanges();
//
//        GroupRepository groupRepository = new GroupRepository(dbContext);
//        groupRepository.Add(newGroup);

        System.out.println(dbContext.GetDatabase().toString());
    }
}
