package com.coursemngsys.coursemanagementsystem.fxControllers;

import com.coursemngsys.coursemanagementsystem.Main;
import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.UserHibernateController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModeratorCoursesWindow implements Initializable {
    @FXML
    private ListView<Course> coursesList;
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private PasswordField password;
    @FXML
    private TextField email;
    private Moderator moderator;
    private SessionFactory factory = null;

    public void getModerator(Moderator mod) {
        this.moderator = mod;
        fillList();
    }

    public void fillList() {
        ObservableList<Course> courses = FXCollections.observableArrayList(moderator.getModeratedCourses());
        coursesList.setItems(courses);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void openNewCourseForm(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("new-course-form.fxml"));
        Parent root = fxmlLoader.load();

        NewCourseForm newCourseForm = fxmlLoader.getController();
        newCourseForm.getData(moderator, this);
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void setUserInfo(Event event) {
        name.setText(moderator.getName());
        surname.setText(moderator.getSurname());
        email.setText(moderator.getEmail());
    }

    public void updateUser(ActionEvent actionEvent) {
        factory = new Configuration().configure().buildSessionFactory();
        UserHibernateController userHibControl = new UserHibernateController(factory);
        moderator.setEmail(email.getText());
        if(!password.getText().equals("")) {
            moderator.setPassword(password.getText());
        }
        userHibControl.editUser(moderator);
        Alert.alertMessage("User updated successfully.");
    }

    public void deleteUser(ActionEvent actionEvent) {
        factory = new Configuration().configure().buildSessionFactory();
        UserHibernateController userHibControl = new UserHibernateController(factory);
        userHibControl.removeUser(moderator.getId());
        Alert.alertMessage("User deleted successfully.");
    }

    public void selectCourse(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("selected-course-window.fxml"));
        Parent root = fxmlLoader.load();

        SelectedCourseWindow selectedCourseWindow = fxmlLoader.getController();
        selectedCourseWindow.getData(coursesList.getSelectionModel().getSelectedItem(), moderator, this);
        Scene scene = new Scene(root);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
