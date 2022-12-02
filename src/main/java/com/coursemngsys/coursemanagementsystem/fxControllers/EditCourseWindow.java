package com.coursemngsys.coursemanagementsystem.fxControllers;

import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.CourseHibernateController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EditCourseWindow {
    @FXML
    private TextField title;
    @FXML
    private TextArea description;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;
    private Course course;
    private ModeratorCoursesWindow moderatorCoursesWindow;

    public void getData(Course course, ModeratorCoursesWindow moderatorCoursesWindow) {
        this.course = course;
        title.setText(course.getTitle());
        description.setText(course.getDescription());
        startDate.setValue(course.getStartDate());
        endDate.setValue(course.getEndDate());
        this.moderatorCoursesWindow = moderatorCoursesWindow;
    }

    public void update(ActionEvent actionEvent) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        CourseHibernateController courseHibControl = new CourseHibernateController(factory);
        course.setTitle(title.getText());
        course.setDescription(description.getText());
        course.setStartDate(startDate.getValue());
        course.setEndDate(endDate.getValue());
        courseHibControl.editCourse(course);
        moderatorCoursesWindow.fillList();
        Alert.alertMessage("Course updated successfully.");
    }
}
