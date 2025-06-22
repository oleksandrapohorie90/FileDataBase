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
