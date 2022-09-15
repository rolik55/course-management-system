package com.coursemngsys.coursemanagementsystem.fxControllers;

import com.coursemngsys.coursemanagementsystem.DbUtils;
import com.coursemngsys.coursemanagementsystem.Main;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.coursemngsys.coursemanagementsystem.Model.Student;
import com.coursemngsys.coursemanagementsystem.hibernateControllers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.time.Year;
import java.util.Objects;
import java.util.ResourceBundle;

public class SignUpForm implements Initializable {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private RadioButton studentRadioButton;
    @FXML
    private RadioButton moderatorRadioButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField repeatedPasswordField;
    @FXML
    private ToggleGroup userType;
    private Connection connection;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void createUser(ActionEvent actionEvent) throws IOException{
        DbUtils.createDb();
        connection = DbUtils.connectToDb();
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        UserHibernateController userHibControl = new UserHibernateController(factory);

        if(loginField.getText() == "" || passwordField.getText() == "" || repeatedPasswordField.getText() == "" || nameField.getText() == "" || surnameField.getText() == "" || emailField.getText() == "") {
            Alert.alertError("Some fields are empty.");
        } else if(arePasswordsMatching(passwordField.getText(), repeatedPasswordField.getText())){
            if (studentRadioButton.isSelected()) {
                String studentNumber = generateStudentNumber();
                Student student = new Student(loginField.getText(),passwordField.getText(),nameField.getText(),surnameField.getText(),emailField.getText(),studentNumber);
                userHibControl.createUser(student);
            } else {
                Moderator moderator = new Moderator(loginField.getText(),passwordField.getText(),nameField.getText(),surnameField.getText(),emailField.getText());
                userHibControl.createUser(moderator);
            }
            Alert.alertMessage("User created successfully.");
            returnToPrevious();
        }
        else {
            Alert.alertError("Passwords do not match.");
        }
        DbUtils.disconnectFromDb(connection);
    }

    private void returnToPrevious() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-window.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        Stage stage = (Stage) loginField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private String generateStudentNumber(){
        int min = 1000;
        int max = 9999;
        int randomStudentNr = (int)Math.floor(Math.random()*(max-min+1)+min);
        return Year.now().getValue() + String.valueOf(randomStudentNr);
    }

    private boolean arePasswordsMatching(String password, String repeatedPassword){
        if(Objects.equals(password, repeatedPassword)){
            return true;
        }
        return false;
    }

}
