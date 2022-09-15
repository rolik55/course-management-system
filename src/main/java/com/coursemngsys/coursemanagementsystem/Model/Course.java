package com.coursemngsys.coursemanagementsystem.Model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private LocalDate dateCreated;
    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "parentCourse", cascade = {CascadeType.ALL})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> folders;
    @ManyToMany(mappedBy = "moderatedCourses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Moderator> moderators;
    @ManyToMany(mappedBy = "enrolledCourses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Student> students;

    public Course(String title, String description, LocalDate startDate, LocalDate endDate, List<Folder> folders, List<Moderator> moderators, List<Student> students) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.folders = folders;
        this.moderators = moderators;
        this.students = students;
    }

    public Course(String title, String description, LocalDate startDate, LocalDate endDate) {
        this.title = title;
        this.description = description;
        this.dateCreated = LocalDate.now();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course(int id, String title, String description, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateCreated = LocalDate.now();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Course() {
    }

    public Course(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(Folder folder) {
        if(folders != null) {
            folders.add(folder);
        }
        else {
            folders = List.of(folder);
        }
    }

    public List<Moderator> getModerators() {
        return moderators;
    }

    public void setModerators(Moderator moderator) {
        if(moderators != null) {
            moderators.add(moderator);
        }
        else {
            moderators = List.of(moderator);
        }
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return title + " - " + description;
    }
}
