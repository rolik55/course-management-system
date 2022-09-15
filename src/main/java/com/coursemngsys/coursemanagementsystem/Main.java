package com.coursemngsys.coursemanagementsystem;

import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.coursemngsys.coursemanagementsystem.hibernateControllers.CourseHibernateController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.time.LocalDate;


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
