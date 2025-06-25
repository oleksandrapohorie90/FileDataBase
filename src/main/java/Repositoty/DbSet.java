package Repositoty;

import Entities.*;

import java.io.Serializable;
import java.util.*;

public class DbSet implements Serializable {

    private List<Academy> academies;
    private List<Group> groups;
    private List<Mentor> mentors;
    private List<Course> courses;
    private List<MentorsToCourses> mentorsToCourses;
    //for no clustered index
    private Map<Integer, Set<Group>> academyNCIndexInGroupTable;

    public DbSet() {
        //we add all the entities in one place
        academies = new ArrayList<>();
        groups = new ArrayList<>();
        courses = new ArrayList<>();
        mentors = new ArrayList<>();
        mentorsToCourses = new ArrayList<>();
        academyNCIndexInGroupTable = new HashMap<>();
    }

    public List<Academy> getAcademies() {
        return academies;
    }

    public void setAcademies(List<Academy> academies) {
        this.academies = academies;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Map<Integer, Set<Group>> getAcademyNCIndexInGroupTable() {
        return academyNCIndexInGroupTable;
    }

    public void setAcademyNCIndexInGroupTable(Map<Integer, Set<Group>> academyNCIndexInGroupTable) {
        this.academyNCIndexInGroupTable = academyNCIndexInGroupTable;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<MentorsToCourses> getMentorsToCourses() {
        return mentorsToCourses;
    }

    public void setMentorsToCourses(List<MentorsToCourses> mentorsToCourses) {
        this.mentorsToCourses = mentorsToCourses;
    }
}
