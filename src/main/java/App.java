import Entities.Group;
import Repositoty.GroupRepository;

public class App {

    public static void main(String[] args) {
        GroupRepository repo = new GroupRepository("group.file");
        //repo.LoadFromFile();
        repo.Add(new Group(2, "GrowthHungry batch 26", "discord.com")); //in the memory of the app at the moment
        //repo.SaveChanges(); //it serialized and saved to the file "group.file"

        //next time someone loads the file and can see whats the content
        //repo.LoadFromFile();
        //writing to the file after file is created
        for (Group group : repo.GetAll()) {
            System.out.println(group.getName());
            System.out.println(group.getDiscordLink());
        }
    }
}
