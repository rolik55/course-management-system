package com.coursemngsys.coursemanagementsystem.webcontrollers;

import com.coursemngsys.coursemanagementsystem.Model.Folder;
import com.coursemngsys.coursemanagementsystem.hibernatecontrollers.FolderHibernateController;
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
public class FolderWeb {
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    FolderHibernateController folderHibControl = new FolderHibernateController(factory);

    @RequestMapping(value = "folder/getFolder", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCourseById(@RequestParam("id") int id){
        return folderHibControl.getFolderById(id).toString();
    }

    @RequestMapping(value = "folder/createFolder", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String createFolder(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Folder folder = new Folder(properties.getProperty("name"));
        folderHibControl.createFolder(folder);
        return STATUS_SUCCESS;
    }

    @RequestMapping(value = "folder/getAllFolders", method = RequestMethod.GET)
    @ResponseBody
    public String getAllFolders(){
        List<Folder> folders = folderHibControl.getAllFolders();
        return folders.toString();
    }

    @RequestMapping(value = "folder/deleteFolder", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteFolder(@RequestParam("id") int id){
        folderHibControl.removeFolder(id);
        return STATUS_SUCCESS;
    }

    @RequestMapping(value = "folder/updateFolder", method = RequestMethod.PUT)
    @ResponseBody
    public String updateFolder(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Folder folder = new Folder(properties.getProperty("name"));
        folderHibControl.editFolder(folder);
        return STATUS_SUCCESS;
    }
}
