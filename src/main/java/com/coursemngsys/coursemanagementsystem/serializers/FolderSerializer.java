package com.coursemngsys.coursemanagementsystem.serializers;

import com.coursemngsys.coursemanagementsystem.Model.Folder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class FolderSerializer implements JsonSerializer<Folder> {
    @Override
    public JsonElement serialize(Folder folder, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject folderJson = new JsonObject();
        folderJson.addProperty("id",folder.getId());
        folderJson.addProperty("name",folder.getName());
        folderJson.addProperty("dateCreated",folder.getDateCreated().toString());
//        folderJson.addProperty("subFolders",folder.getSubFolders());
//        folderJson.addProperty("parentFolder",folder.getParentFolder());
//        folderJson.addProperty("editors",folder.getEditors());
//        folderJson.addProperty("files",folder.getFiles());
//        folderJson.addProperty("parentCourse",folder.getParentCourse());
        return folderJson;
    }
}
