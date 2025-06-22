import Entities.AcademyGroup;
import Repositoty.AcademyGroupRepository;

public class main {

    public static void main(String[] args) {
        AcademyGroupRepository repo = new AcademyGroupRepository("group.file");
        repo.LoadFromFile();
        repo.Add(new AcademyGroup(1, "GrowthHungry batch 25", "")); //in the memory of the app at the moment
        repo.SaveChanges(); //it serialized and saved to the file "group.file"

        //next time someone loads the file and can see whats the content
        repo.LoadFromFile();
        for (AcademyGroup academyGroup : repo.GetAll()) {
            System.out.println(academyGroup.getName());
        }
    }
}
