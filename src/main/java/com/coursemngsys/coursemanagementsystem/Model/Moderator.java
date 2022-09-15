package com.coursemngsys.coursemanagementsystem.Model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Moderator extends User{

    @ManyToMany
    @JoinTable(
            name = "moderator_courses",
            joinColumns = { @JoinColumn(name = "moderator_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private List<Course> moderatedCourses;
    @ManyToMany
    private List<Folder> editableFolders;

    public Moderator() {
    }

    public Moderator(String login, String password, String name, String surname, String email) {
        super(login, password, name, surname, email);
    }

    public Moderator(int id, String login, String password, String name, String surname, String email) {
        super(id, login, password, name, surname, email);
    }

    public Moderator(String login, String password, String name, String surname, String email, List<Course> moderatedCourses, List<Folder> editableFolders) {
        super(login, password, name, surname, email);
        this.moderatedCourses = moderatedCourses;
        this.editableFolders = editableFolders;
    }

    public Moderator(String login, String password, String name, String surname, String email, List<File> myFiles, List<Course> moderatedCourses, List<Folder> editableFolders) {
        super(login, password, name, surname, email, myFiles);
        this.moderatedCourses = moderatedCourses;
        this.editableFolders = editableFolders;
    }

    public List<Course> getModeratedCourses() {
        return moderatedCourses;
    }

    public void setModeratedCourses(Course moderatedCourse) {
        if(moderatedCourses != null) {
            moderatedCourses.add(moderatedCourse);
        }
        else {
            moderatedCourses = List.of(moderatedCourse);
        }
    }

    public void removeModeratedCourse(Course course) {
        moderatedCourses.remove(course);
    }

    public List<Folder> getEditableFolders() {
        return editableFolders;
    }

    public void setEditableFolders(Folder folder) {
        if(editableFolders != null) {
            editableFolders.add(folder);
        }
        else {
            editableFolders = List.of(folder);
        }
    }
}
