package com.coursemngsys.coursemanagementsystem.fxControllers;

import com.coursemngsys.coursemanagementsystem.DbUtils;
import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.CourseHibernateController;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.UserHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public class NewCourseForm {
    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    private Moderator moderator;
    private ModeratorCoursesWindow moderatorCoursesWindow;

    public void createCourse(ActionEvent actionEvent) {
        Connection connection = DbUtils.connectToDb();
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        CourseHibernateController courseHibControl = new CourseHibernateController(factory);
        UserHibernateController userHibControl = new UserHibernateController(factory);
        Course course = new Course(title.getText(), description.getText(), startDate.getValue(), endDate.getValue());
        course.setModerators(moderator);
        courseHibControl.createCourse(course);
        moderator.addModeratedCourse(course);
        userHibControl.editUser(moderator);
        moderatorCoursesWindow.getModerator(moderator);
        Alert.alertMessage("Course created successfully.");
        DbUtils.disconnectFromDb(connection);
    }

    public void getData(Moderator mod, ModeratorCoursesWindow moderatorCoursesWindow) {
        this.moderator = mod;
        this.moderatorCoursesWindow = moderatorCoursesWindow;
    }
}
