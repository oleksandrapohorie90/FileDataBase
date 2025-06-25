package Repositoty;

import Entities.*;

import java.io.Serializable;
import java.util.*;

public class DbSet implements Serializable {

    private Map<Integer, Academy> academies;
    private Map<Integer, Group> groups;
    private Map<Integer, Mentor> mentors;
    private Map<Integer, Course> courses;
    private Map<Integer, MentorsToCourses> mentorsToCourses;
    //for no clustered index
    private Map<Integer, Set<Group>> academyNCIndexInGroupTable;

    public DbSet() {
        //we add all the entities in one place
        academies = new HashMap<>();
        groups = new HashMap<>();
        courses = new HashMap<>();
        mentors = new HashMap<>();
        mentorsToCourses = new HashMap<>();
        academyNCIndexInGroupTable = new HashMap<>();
    }

    public Map<Integer, Set<Group>> getAcademyNCIndexInGroupTable() {
        return academyNCIndexInGroupTable;
    }

    public void setAcademyNCIndexInGroupTable(Map<Integer, Set<Group>> academyNCIndexInGroupTable) {
        this.academyNCIndexInGroupTable = academyNCIndexInGroupTable;
    }

    public Map<Integer, Academy> getAcademies() {
        return academies;
    }

    public void setAcademies(Map<Integer, Academy> academies) {
        this.academies = academies;
    }

    public Map<Integer, Group> getGroups() {
        return groups;
    }

    public void setGroups(Map<Integer, Group> groups) {
        this.groups = groups;
    }

    public Map<Integer, Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(Map<Integer, Mentor> mentors) {
        this.mentors = mentors;
    }

    public Map<Integer, Course> getCourses() {
        return courses;
    }

    public void setCourses(Map<Integer, Course> courses) {
        this.courses = courses;
    }

    public Map<Integer, MentorsToCourses> getMentorsToCourses() {
        return mentorsToCourses;
    }

    public void setMentorsToCourses(Map<Integer, MentorsToCourses> mentorsToCourses) {
        this.mentorsToCourses = mentorsToCourses;
    }
}
