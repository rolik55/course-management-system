package com.coursemngsys.coursemanagementsystem.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String location;
    private LocalDate dateCreated;
    @ManyToOne
    private Folder parentFolder;
    @ManyToOne
    private User creator;

    public File(String name, String location, LocalDate dateCreated, Folder parentFolder, User creator) {
        this.name = name;
        this.location = location;
        this.dateCreated = dateCreated;
        this.parentFolder = parentFolder;
        this.creator = creator;
    }

    public File(String name, String location, Folder parentFolder, User creator) {
        this.name = name;
        this.location = location;
        this.parentFolder = parentFolder;
        this.creator = creator;
        this.dateCreated = LocalDate.now();
    }

    public File(String name, String location) {
        this.name = name;
        this.location = location;
        this.dateCreated = LocalDate.now();
    }

    public File(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.dateCreated = LocalDate.now();
    }

    public File() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }
}
