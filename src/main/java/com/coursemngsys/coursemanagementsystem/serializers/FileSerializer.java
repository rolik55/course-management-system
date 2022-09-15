package com.coursemngsys.coursemanagementsystem.serializers;

import com.coursemngsys.coursemanagementsystem.Model.File;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class FileSerializer implements JsonSerializer<File> {
    @Override
    public JsonElement serialize(File file, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject fileJson = new JsonObject();
        fileJson.addProperty("id", file.getId());
        fileJson.addProperty("name", file.getName());
        fileJson.addProperty("location", file.getLocation());
        fileJson.addProperty("dateCreated", file.getDateCreated().toString());
//        fileJson.addProperty("parentFolder", file.getParentFolder());
//        fileJson.addProperty("creator", file.getCreator());
        return fileJson;
    }
}
