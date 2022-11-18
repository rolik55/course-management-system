package com.coursemngsys.coursemanagementsystem.temp;

import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 56130
 */
public class User {

    private final int id;
    private String firstName;
    private String lastName;
    private int age;
    private String profession;
    private ArrayList<User> children;

    public User(int id, String firstName, String lastName, int age, String profession, ArrayList<User> children) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.profession = profession;
        this.children = children;
    }

    /**
     * This function applies changes to User's name and returns it if the given User is a child of current User
     * @param child Any other User class instance
     * @return name Text value of User's name
     * @throws IllegalArgumentException If given User is not a child of current User
     */
    public String getChildName(User child) {
        if (!this.children.contains(child)) {
            throw new IllegalArgumentException("Invalid argument!");
        } else {
            String name = null;
            if (child.getFirstName() != null) {
                name = child.getFirstName();
            }
            if (name.equals("Harry")) {
                name = name.replace('r', 'p');
            }
            if (name != null && name.length() > 0) {
                name = name.concat(child.getLastName());
            }
            return name;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
