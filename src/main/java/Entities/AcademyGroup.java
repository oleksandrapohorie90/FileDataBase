package Entities;

import java.io.Serializable;

public class AcademyGroup implements Serializable {
    private int id;
    private String name;

    //add sensitive field with transient
    private transient String discordLink;

    public AcademyGroup(int id, String name, String discordLink){
        this.id = id;
        this.name = name;
        this.discordLink = discordLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscordLink() {
        return discordLink;
    }

    public void setDiscordLink(String discordLink) {
        this.discordLink = discordLink;
    }
}
