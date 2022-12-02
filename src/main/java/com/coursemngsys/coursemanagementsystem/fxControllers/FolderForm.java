package com.coursemngsys.coursemanagementsystem.fxControllers;

import com.coursemngsys.coursemanagementsystem.DbUtils;
import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.coursemngsys.coursemanagementsystem.Model.Folder;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.CourseHibernateController;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.FolderHibernateController;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public class FolderForm {
    @FXML
    private TextField name;
    private Course course;
    private Moderator moderator;
    private SelectedCourseWindow courseWindow;
    private Folder parentFolder;

    public void getData(Course course, Moderator moderator, SelectedCourseWindow courseWindow, Folder parentFolder) {
        this.course = course;
        this.moderator = moderator;
        this.courseWindow = courseWindow;
        this.parentFolder = parentFolder;
    }


    public void createFolder(ActionEvent actionEvent) {
        Connection connection = DbUtils.connectToDb();
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        FolderHibernateController folderHibControl = new FolderHibernateController(factory);
        UserHibernateController userHibControl = new UserHibernateController(factory);
        CourseHibernateController courseHibControl = new CourseHibernateController(factory);
        Folder folder = new Folder(name.getText());
        folder.setEditors(moderator);
        folder.setParentCourse(course);
        if(parentFolder != null) {
            folder.setParentFolder(parentFolder);
            parentFolder.setSubFolders(folder);
        }
        moderator.setEditableFolders(folder);
        course.setFolders(folder);
        folderHibControl.createFolder(folder);
        if(parentFolder != null) {
            folderHibControl.editFolder(parentFolder);
        }
        courseHibControl.editCourse(course);
        userHibControl.editUser(moderator);
        courseWindow.fillTree();
        Alert.alertMessage("Folder created successfully.");
        DbUtils.disconnectFromDb(connection);
    }
}
