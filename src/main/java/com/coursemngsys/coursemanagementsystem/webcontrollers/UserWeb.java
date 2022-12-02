package com.coursemngsys.coursemanagementsystem.webcontrollers;

import com.coursemngsys.coursemanagementsystem.DbUtils;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.coursemngsys.coursemanagementsystem.Model.Student;
import com.coursemngsys.coursemanagementsystem.Model.User;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.UserHibernateController;
import com.coursemngsys.coursemanagementsystem.serializers.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

@Controller
public class UserWeb {
    private static final String PROPERTY_PASSWORD = "password";
    private static final String PROPERTY_LOGIN = "login";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_SURNAME = "surname";
    private static final String PROPERTY_EMAIL = "email";
    private static final String PROPERTY_STUDENT_NUMBER = "studentNumber";
    protected static final String STATUS_SUCCESS = "Success";
    private static final
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    UserHibernateController userHibControl = new UserHibernateController(factory);

    @RequestMapping(value = "user/getUser", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getUserById(@RequestParam("id") int id){
        return userHibControl.getUserById(id).toString();
    }

    @RequestMapping(value = "user/userLogin", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    private String getCurrentUser(@RequestBody String request){
        Connection connection = DbUtils.connectToDb();
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        User user = userHibControl.getAllUsers().stream().filter(c -> c.getLogin().equals(properties.getProperty(PROPERTY_LOGIN))).filter(c -> c.getPassword().equals(properties.getProperty(PROPERTY_PASSWORD))).findFirst().orElse(null);
        GsonBuilder gsonBuilder = new GsonBuilder();
        if(user instanceof Moderator){
            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).registerTypeAdapter(Moderator.class, new ModeratorSerializer());
        }
        else gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).registerTypeAdapter(Student.class, new StudentSerializer());
        Gson parser = gsonBuilder.create();
        DbUtils.disconnectFromDb(connection);
        if(user != null)
            return parser.toJson(user);
        else return null;
    }

    @RequestMapping(value = "user/createStudent", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createStudent(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Student student = new Student(properties.getProperty(PROPERTY_LOGIN), properties.getProperty(PROPERTY_PASSWORD), properties.getProperty(PROPERTY_NAME), properties.getProperty(PROPERTY_SURNAME), properties.getProperty(PROPERTY_EMAIL), properties.getProperty(PROPERTY_STUDENT_NUMBER));
        userHibControl.createUser(student);
        return STATUS_SUCCESS;
    }

    @RequestMapping(value = "user/getAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUsers(){
        List<User> users = userHibControl.getAllUsers();
        return users.toString();
    }

    @RequestMapping(value = "user/getAllStudents", method = RequestMethod.GET)
    @ResponseBody
    public String getAllStudents(){
        List<Student> students = userHibControl.getAllStudents();
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Student.class, new StudentSerializer());
        Gson parser = gson.create();
        return parser.toJson(students);
    }

    @RequestMapping(value = "user/getAllModerators", method = RequestMethod.GET)
    @ResponseBody
    public String getAllModerators(){
        List<Moderator> moderators = userHibControl.getAllModerators();
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(Moderator.class, new ModeratorSerializer());
        Gson parser = gson.create();
        return parser.toJson(moderators);
    }

    @RequestMapping(value = "user/deleteUser", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteUser(@RequestParam("id") int id){
        userHibControl.removeUser(id);
        return STATUS_SUCCESS;
    }

    @RequestMapping(value = "user/createMod", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createMod(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Moderator mod = new Moderator(properties.getProperty(PROPERTY_LOGIN), properties.getProperty(PROPERTY_PASSWORD), properties.getProperty(PROPERTY_NAME), properties.getProperty(PROPERTY_SURNAME), properties.getProperty(PROPERTY_EMAIL));
        userHibControl.createUser(mod);
        return STATUS_SUCCESS;
    }

    @RequestMapping(value = "user/updateStudent", method = RequestMethod.PUT)
    @ResponseBody
    public String updateStudent(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Student student = new Student(properties.getProperty(PROPERTY_LOGIN), properties.getProperty(PROPERTY_PASSWORD), properties.getProperty(PROPERTY_NAME), properties.getProperty(PROPERTY_SURNAME), properties.getProperty(PROPERTY_EMAIL), properties.getProperty(PROPERTY_STUDENT_NUMBER));
        userHibControl.editUser(student);
        return STATUS_SUCCESS;
    }

    @RequestMapping(value = "user/updateMod", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateMod(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Moderator mod = new Moderator(properties.getProperty(PROPERTY_LOGIN), properties.getProperty(PROPERTY_PASSWORD), properties.getProperty(PROPERTY_NAME), properties.getProperty(PROPERTY_SURNAME), properties.getProperty(PROPERTY_EMAIL));
        userHibControl.editUser(mod);
        return STATUS_SUCCESS;
    }
}
