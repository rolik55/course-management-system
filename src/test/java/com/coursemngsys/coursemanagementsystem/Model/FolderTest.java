package com.coursemngsys.coursemanagementsystem.Model;

import org.dom4j.rule.Mode;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FolderTest {

    Course testCourse = new Course("testCourse", "testDescription, ", LocalDate.of(2022, 12, 2), LocalDate.of(2022, 12, 2));
    List<Moderator> testEditors = new ArrayList<>();
    Folder folder = new Folder("testFolder", LocalDate.of(2022, 12, 2), null, null, testEditors, null, testCourse);

    @Test
    void setId() {
        folder.setId(1);
        assertEquals(1, folder.getId());
    }

    @Test
    void setName() {
        folder.setName("testFolderName");
        assertNotEquals("testFolder", folder.getName());
    }

    @Test
    void setDateCreated() {
        folder.setDateCreated(LocalDate.of(2023, 01, 01));
        assertNotEquals(LocalDate.of(2022, 12, 2), folder.getDateCreated());
    }

    @Test
    void setSubFolders() {
        Folder subFolder = new Folder();
        Folder subFolder1 = new Folder();
        folder.setSubFolders(subFolder);
        folder.setSubFolders(subFolder1);
        assertEquals(subFolder, folder.getSubFolders().get(0));
    }

    @Test
    void setEditors() {
        Moderator editor = new Moderator();
        Moderator editor1 = new Moderator();
        folder.setEditors(editor);
        folder.setEditors(editor1);
        assertEquals(editor, folder.getEditors().get(0));
    }

    @Test
    void setFiles() {
        List<File> files = new ArrayList<>();
        File file = new File();
        File file1 = new File();
        folder.setFiles(files);
        files.add(file);
        files.add(file1);
        folder.setFiles(files);
        assertEquals(2, folder.getFiles().size());
    }

    @Test
    void setParentFolder() {
        Folder parentFolder = new Folder();
        folder.setParentFolder(parentFolder);
        assertNotEquals(null, folder.getParentFolder());
    }

    @Test
    void setParentCourse() {
        Course course = new Course();
        folder.setParentCourse(course);
        assertNotEquals(testCourse, folder.getParentCourse());
    }

    @Test
    void testToString() {
        String testString = folder.getName();
        assertEquals(testString, folder.toString());
    }

    @Test
    void compareTo() {
        Folder compareFolder = new Folder(1, "1Name");
        folder.setName("2Name");
        assertEquals(1, folder.compareTo(compareFolder));
    }
}