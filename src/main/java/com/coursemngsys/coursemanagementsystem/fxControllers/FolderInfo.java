package com.coursemngsys.coursemanagementsystem.fxControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FolderInfo {
    @FXML
    private TextField name;
    @FXML
    private TextField dateCreated;
    @FXML
    private TextField subfolders;
    @FXML
    private TextField parentFolder;
    @FXML
    private TextField parentCourse;
    @FXML
    private TextField editors;
    @FXML
    private TextField files;

    public void setInfo(String name, String date, String subfolders, String parentFolder, String parentCourse, String editors, String files) {
        this.name.setText(name);
        dateCreated.setText(date);
        this.subfolders.setText(subfolders);
        this.parentFolder.setText(parentFolder);
        this.parentCourse.setText(parentCourse);
        this.editors.setText(editors);
        this.files.setText(files);
    }
}
