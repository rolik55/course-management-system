package com.coursemngsys.coursemanagementsystem.webcontrollers;

import com.coursemngsys.coursemanagementsystem.Model.File;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.FileHibernateController;
import com.google.gson.Gson;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Properties;

import static com.coursemngsys.coursemanagementsystem.webcontrollers.UserWeb.STATUS_SUCCESS;

@Controller
public class FileWeb {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    FileHibernateController fileHibControl = new FileHibernateController(factory);

    @RequestMapping(value = "file/getFile", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getFileById(@RequestParam("id") int id){
        return fileHibControl.getFileById(id).toString();
    }

    @RequestMapping(value = "file/createFile", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createFile(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        File file = new File(properties.getProperty("name"),properties.getProperty("location"));
        fileHibControl.createFile(file);
        return STATUS_SUCCESS;
    }

    @RequestMapping(value = "file/getAllFiles", method = RequestMethod.GET)
    @ResponseBody
    public String getAllFiles(){
        List<File> files = fileHibControl.getAllFiles(true,1,1);
        return files.toString();
    }

    @RequestMapping(value = "file/deleteFile", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFile(@RequestParam("id") int id){
        fileHibControl.removeFile(id);
        return STATUS_SUCCESS;
    }

    @RequestMapping(value = "file/updateFile", method = RequestMethod.PUT)
    @ResponseBody
    public String updateFile(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        File file = new File(properties.getProperty("name"),properties.getProperty("location"));
        fileHibControl.editFile(file);
        return STATUS_SUCCESS;
    }
}
