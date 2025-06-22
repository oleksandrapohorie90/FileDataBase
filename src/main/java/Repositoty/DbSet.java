package Repositoty;

import Entities.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DbSet implements Serializable {

    private List<Academy> academies;
    private List<Group> groups;
    private List<Mentor> mentors;
    private List<Course> courses;
    private List<MentorsToCourses> mentorsToCourses;

    public DbSet() {
        //we add all the enitites in one place
        academies = new ArrayList<>();
        groups = new ArrayList<>();
        courses = new ArrayList<>();
        mentors = new ArrayList<>();
        mentorsToCourses = new ArrayList<>();

    }
}
