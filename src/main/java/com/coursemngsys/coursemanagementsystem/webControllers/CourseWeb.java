package com.coursemngsys.coursemanagementsystem.webControllers;

import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.coursemngsys.coursemanagementsystem.hibernateControllers.CourseHibernateController;
import com.coursemngsys.coursemanagementsystem.serializers.CourseSerializer;
import com.coursemngsys.coursemanagementsystem.serializers.LocalDateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Controller
public class CourseWeb {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    CourseHibernateController courseHibControl = new CourseHibernateController(factory);

    @RequestMapping(value = "course/getCourse", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCourseById(@RequestParam("id") int id){
        Course course = courseHibControl.getCourseById(id);
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).registerTypeAdapter(Course.class, new CourseSerializer());
        Gson parser = gson.create();
        return parser.toJson(course);
    }

    @RequestMapping(value = "course/createCourse", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createCourse(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Course course = new Course(properties.getProperty("title"), properties.getProperty("description"), LocalDate.parse(properties.getProperty("startDate")),LocalDate.parse(properties.getProperty("endDate")));
        courseHibControl.createCourse(course);
        return "Success";
    }

    @RequestMapping(value = "course/getAllCourses", method = RequestMethod.GET)
    @ResponseBody
    public String getAllCourses(){
        List<Course> courses = courseHibControl.getAllCourses(true,1,1);
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Course.class, new CourseSerializer());
        Gson parser = gson.create();
        return parser.toJson(courses);
    }

    @RequestMapping(value = "course/deleteCourse", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCourse(@RequestParam("id") int id){
        courseHibControl.removeCourse(id);
        return "Success";
    }

    @RequestMapping(value = "course/updateCourse", method = RequestMethod.PUT)
    @ResponseBody
    public String updateCourse(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Course course = new Course(Integer.parseInt(properties.getProperty("id")), properties.getProperty("title"), properties.getProperty("description"));
        //, LocalDate.parse(properties.getProperty("startDate")),LocalDate.parse(properties.getProperty("endDate"))
        courseHibControl.editCourse(course);
        return "Success";
    }
}
