package com.coursemngsys.coursemanagementsystem.serializers;

import com.coursemngsys.coursemanagementsystem.Model.Folder;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class FoldersSerializer implements JsonSerializer<List<Folder>> {
    @Override
    public JsonElement serialize(List<Folder> folders, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Folder.class, new FoldersSerializer());
        Gson parser = gsonBuilder.create();
        for(Folder f: folders){
            jsonArray.add(parser.toJson(f));
        }
        return jsonArray;
    }
}
