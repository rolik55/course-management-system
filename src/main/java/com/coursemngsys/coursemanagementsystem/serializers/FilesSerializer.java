package com.coursemngsys.coursemanagementsystem.serializers;

import com.coursemngsys.coursemanagementsystem.Model.File;
import com.coursemngsys.coursemanagementsystem.Model.Folder;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class FilesSerializer implements JsonSerializer<List<File>> {
    @Override
    public JsonElement serialize(List<File> files, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(File.class, new FilesSerializer());
        Gson parser = gsonBuilder.create();
        for(File f: files){
            jsonArray.add(parser.toJson(f));
        }
        return jsonArray;
    }
}
