package com.coursemngsys.coursemanagementsystem.Model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    private LocalDate dateCreated;
    private LocalDate dateModified;
    private String name;
    private String surname;
    private String email;
    @OneToMany(mappedBy = "creator", orphanRemoval = true, cascade = {CascadeType.ALL})
    @OrderBy("id ASC")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<File> myFiles;

    public User(int id, String login, String password, String name, String surname, String email) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateModified = LocalDate.now();
    }

    public User(String login, String password, String name, String surname, String email) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.dateModified = LocalDate.now();
        this.dateCreated = LocalDate.now();
    }

    public User(String login, String password, String name, String surname, String email, List<File> myFiles) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.myFiles = myFiles;
        this.dateModified = LocalDate.now();
        this.dateCreated = LocalDate.now();
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDate getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDate dateModified) {
        this.dateModified = dateModified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<File> getMyFiles() {
        return myFiles;
    }

    public void setMyFiles(List<File> myFiles) {
        this.myFiles = myFiles;
    }

    @Override
    public String toString() {
        return id + " - " + name + " " + surname;
    }
}
