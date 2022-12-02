package com.coursemngsys.coursemanagementsystem.fxControllers;


import com.coursemngsys.coursemanagementsystem.DbUtils;
import com.coursemngsys.coursemanagementsystem.Main;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.coursemngsys.coursemanagementsystem.Model.User;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.sql.Connection;


public class LoginWindow{
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    private Connection connection;

    public void loadCourses(ActionEvent actionEvent) throws IOException {
        User user = getCurrentUser();
        if (user != null) {
            if(user instanceof Moderator) {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("moderator-courses-window.fxml"));
                Parent root = fxmlLoader.load();
                ModeratorCoursesWindow moderatorCoursesWindow = fxmlLoader.getController();
                moderatorCoursesWindow.getModerator((Moderator) user);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Course Management System");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            }
            else {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("student-courses-window.fxml"));
                Parent root = fxmlLoader.load();
                StudentCoursesWindow studentCoursesWindow = fxmlLoader.getController();
                //studentCoursesWindow.setCourseFormData(user);
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setTitle("Course Management System");
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            }
        } else {
            Alert.alertError("No such user found");
        }
    }

    private User getCurrentUser(){
        connection = DbUtils.connectToDb();
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        UserHibernateController userHibControl = new UserHibernateController(factory);
        User user = userHibControl.getAllUsers().stream().filter(c -> c.getLogin().equals(loginField.getText())).filter(c -> c.getPassword().equals(passwordField.getText())).findFirst().orElse(null);
        DbUtils.disconnectFromDb(connection);
        return user;
    }

    public void openNewUserForm(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("sign-up-form.fxml"));
        Parent root = fxmlLoader.load();

        SignUpForm signUpForm = fxmlLoader.getController();
        Scene scene = new Scene(root);

        Stage stage = (Stage) loginField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
