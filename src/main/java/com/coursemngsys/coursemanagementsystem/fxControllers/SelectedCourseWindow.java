package com.coursemngsys.coursemanagementsystem.fxControllers;

import com.coursemngsys.coursemanagementsystem.Main;
import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.coursemngsys.coursemanagementsystem.Model.Folder;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.coursemngsys.coursemanagementsystem.hibernateControllers.CourseHibernateController;
import com.coursemngsys.coursemanagementsystem.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class SelectedCourseWindow {
    @FXML
    private TreeView<Folder> folderTree;
    private SessionFactory factory = null;
    private Course course;
    private List<Moderator> moderators;
    private Moderator user;
    private ModeratorCoursesWindow moderatorCoursesWindow;

    public void getData(Course course, Moderator user, ModeratorCoursesWindow moderatorCoursesWindow) {
        this.course = course;
        this.moderators = course.getModerators();
        this.user = user;
        this.moderatorCoursesWindow = moderatorCoursesWindow;
        fillTree();
    }

    public void editCourse(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("edit-course-window.fxml"));
        Parent root = fxmlLoader.load();

        EditCourseWindow editCourseWindow = fxmlLoader.getController();
        editCourseWindow.getData(course, moderatorCoursesWindow);
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void deleteCourse(ActionEvent actionEvent) {
        factory = new Configuration().configure().buildSessionFactory();
        CourseHibernateController courseHibControl = new CourseHibernateController(factory);
        UserHibernateController userHibControl = new UserHibernateController(factory);
        for(int i = 0; i < moderators.size(); i++) {
            moderators.get(i).removeModeratedCourse(course);
            userHibControl.editUser(moderators.get(i));
        }
        user.removeModeratedCourse(course);
        userHibControl.editUser(user);
        courseHibControl.removeCourse(course.getId());
        moderatorCoursesWindow.getModerator(user);
        Alert.alertMessage("Course deleted successfully.");
    }

    public void addFolder(ActionEvent actionEvent) throws IOException {
        Folder parentFolder;
        try {
            parentFolder = folderTree.getSelectionModel().getSelectedItem().getValue();
        } catch (Exception e){
            parentFolder = null;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("folder-form.fxml"));
        Parent root = fxmlLoader.load();

        FolderForm folderForm = fxmlLoader.getController();
        folderForm.getData(course, user, this, parentFolder);

        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void showFolderInfo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("folder-info.fxml"));
        Parent root = fxmlLoader.load();

        FolderInfo folderInfo = fxmlLoader.getController();
        Folder folder = folderTree.getSelectionModel().getSelectedItem().getValue();
        String parentF, fileCount;
        if(folder.getParentFolder() == null) {
            parentF = "-";
        } else parentF = folder.getParentFolder().getName();
        if(folder.getFiles() == null) {
            fileCount = "0";
        } else fileCount = String.valueOf(folder.getFiles().size());
        folderInfo.setInfo(folder.getName(), folder.getDateCreated().toString(), String.valueOf(folder.getSubFolders().size()), parentF, folder.getParentCourse().toString(), folder.getEditors().toString(), fileCount);
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void fillTree() {
        List<Folder> folders = null;
        folders = course.getFolders();
        TreeMap<Folder, List<Folder>> data = new TreeMap<>();
        List<Folder> rootFolders = new ArrayList<>();
        if(!folders.isEmpty()) {
        Folder[] rootKeys = new Folder[folders.size()+1];
            for(int i = 0; i < folders.size(); i++) {
                if(folders.get(i).getSubFolders() != null) {
                data.put(folders.get(i), folders.get(i).getSubFolders());
                } else {
                    data.put(folders.get(i), null);
                }
                if(folders.get(i).getParentFolder() == null) {
                    rootFolders.add(folders.get(i));
                }
            }
            rootKeys = rootFolders.toArray(rootKeys);
            createTreeView(data, rootKeys, folderTree);
        }
    }

    private void createTreeView(TreeMap<Folder, List<Folder>> data, Folder[] rootKeys, TreeView<Folder> tree) {
        TreeItem<Folder> root = new TreeItem<>();
        Arrays.stream(rootKeys).forEach(rootKey -> root.getChildren().add(createTreeItem(data, rootKey)));
        tree.setRoot(root);
        tree.setShowRoot(false);
    }

   private TreeItem<Folder> createTreeItem(TreeMap<Folder, List<Folder>> data, Folder rootKey) {
        if(rootKey != null) {
            TreeItem<Folder> item = new TreeItem<>();
            item.setValue(rootKey);
            item.setExpanded(true);
            List<Folder> childData = data.get(rootKey);
            if (childData != null) {
                childData.stream().sorted().map(child -> createTreeItem(data, child)).collect(Collectors.toCollection(item::getChildren));
            }
            return item;
        } else return null;
   }
}
