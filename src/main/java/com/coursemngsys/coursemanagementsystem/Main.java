package com.coursemngsys.coursemanagementsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Course Management System");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();

//        DbUtils.createDb();
//
//        SessionFactory factory = new Configuration().configure().buildSessionFactory();
//        CourseHibernateController w = new CourseHibernateController(factory);
//        LocalDate date = LocalDate.now();
//        Course c1 = new Course("test","test",date,date);
//        w.createCourse(c1);
    }
}
