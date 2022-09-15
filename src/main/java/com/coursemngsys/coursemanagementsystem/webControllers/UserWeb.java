package com.coursemngsys.coursemanagementsystem.webControllers;

import com.coursemngsys.coursemanagementsystem.DbUtils;
import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.coursemngsys.coursemanagementsystem.Model.Student;
import com.coursemngsys.coursemanagementsystem.Model.User;
import com.coursemngsys.coursemanagementsystem.hibernateControllers.UserHibernateController;
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
        User user = userHibControl.getAllUsers(true,0,0).stream().filter(c -> c.getLogin().equals(properties.getProperty("login"))).filter(c -> c.getPassword().equals(properties.getProperty("password"))).findFirst().orElse(null);
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
        Student student = new Student(properties.getProperty("login"), properties.getProperty("password"), properties.getProperty("name"), properties.getProperty("surname"), properties.getProperty("email"), properties.getProperty("studentNumber"));
        userHibControl.createUser(student);
        return "Success";
    }

    @RequestMapping(value = "user/getAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUsers(){
        List<User> users = userHibControl.getAllUsers(true,1,1);
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
        return "Success";
    }

    @RequestMapping(value = "user/createMod", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createMod(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Moderator mod = new Moderator(properties.getProperty("login"), properties.getProperty("password"), properties.getProperty("name"), properties.getProperty("surname"), properties.getProperty("email"));
        userHibControl.createUser(mod);
        return "Success";
    }

    @RequestMapping(value = "user/updateStudent", method = RequestMethod.PUT)
    @ResponseBody
    public String updateStudent(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Student student = new Student(properties.getProperty("login"), properties.getProperty("password"), properties.getProperty("name"), properties.getProperty("surname"), properties.getProperty("email"), properties.getProperty("studentNumber"));
        userHibControl.editUser(student);
        return "Success";
    }

    @RequestMapping(value = "user/updateMod", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateMod(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Moderator mod = new Moderator(properties.getProperty("login"), properties.getProperty("password"), properties.getProperty("name"), properties.getProperty("surname"), properties.getProperty("email"));
        userHibControl.editUser(mod);
        return "Success";
    }
}
