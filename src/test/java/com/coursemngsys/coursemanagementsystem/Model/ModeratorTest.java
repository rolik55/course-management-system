package com.coursemngsys.coursemanagementsystem.Model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ModeratorTest {
    private Moderator mod = new Moderator("login", "password", "name", "surname", "email");
    private Course course = new Course(1, "kursas", "test");

    @Test
    void getAndAddModeratedCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        mod.addModeratedCourse(course);
        assertEquals(courses.toString(), mod.getModeratedCourses().toString());
    }

    @Test
    void getAndSetModeratedCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(course);
        mod.setModeratedCourses(courses);
        assertEquals(courses.toString(), mod.getModeratedCourses().toString());
    }

    @Test
    void removeModeratedCourse() {
        List<Course> courses = new ArrayList<>();
        Course course1 = new Course(2, "z", "z");
        courses.add(course);
        courses.add(course1);
        mod.setModeratedCourses(courses);
        mod.removeModeratedCourse(course);
        courses.remove(course);
        assertEquals(courses.toString(), mod.getModeratedCourses().toString());
    }

    @Test
    void getAndSetEditableFolders() {
        Folder folder = new Folder("folder");
        mod.setEditableFolders(folder);
        List<Folder> folders = new ArrayList<>();
        folders.add(folder);
        assertEquals(folders.toString(), mod.getEditableFolders().toString());
    }
}