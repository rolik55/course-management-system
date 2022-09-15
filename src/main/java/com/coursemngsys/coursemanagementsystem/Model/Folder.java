package com.coursemngsys.coursemanagementsystem.Model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "folders")
public class Folder implements Comparable<Folder>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate dateCreated;
    @OneToMany(mappedBy = "parentFolder", cascade = {CascadeType.ALL})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Folder> subFolders;
    @ManyToOne
    private Folder parentFolder;
    @ManyToMany(mappedBy = "editableFolders", cascade = {CascadeType.PERSIST})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Moderator> editors;
    @OneToMany(mappedBy = "parentFolder", cascade = {CascadeType.ALL})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<File> files;
    @ManyToOne
    private Course parentCourse;

    public Folder(String name, LocalDate dateCreated, List<Folder> subFolders, Folder parentFolder, List<Moderator> editors, List<File> files, Course parentCourse) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.subFolders = subFolders;
        this.parentFolder = parentFolder;
        this.editors = editors;
        this.files = files;
        this.parentCourse = parentCourse;
    }

    public Folder(String name, List<Folder> subFolders, Folder parentFolder, List<Moderator> editors, List<File> files, Course parentCourse) {
        this.name = name;
        this.subFolders = subFolders;
        this.parentFolder = parentFolder;
        this.editors = editors;
        this.files = files;
        this.parentCourse = parentCourse;
        this.dateCreated = LocalDate.now();
    }

    public Folder(String name) {
        this.name = name;
        this.dateCreated = LocalDate.now();
    }

    public Folder(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Folder() {
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

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Folder> getSubFolders() {
        return subFolders;
    }

    public void setSubFolders(Folder folder) {
        if(subFolders != null) {
            subFolders.add(folder);
        }
        else {
            subFolders = List.of(folder);
        }
    }

    public List<Moderator> getEditors() {
        return editors;
    }

    public void setEditors(Moderator editor) {
        if(editors != null) {
            editors.add(editor);
        }
        else {
            editors = List.of(editor);
        }
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Folder getParentFolder() {
        return parentFolder;
    }

    public void setParentFolder(Folder parentFolder) {
        this.parentFolder = parentFolder;
    }

    public Course getParentCourse() {
        return parentCourse;
    }

    public void setParentCourse(Course parentCourse) {
        this.parentCourse = parentCourse;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Folder o) {
        return this.name.compareTo(o.name);
    }
}
