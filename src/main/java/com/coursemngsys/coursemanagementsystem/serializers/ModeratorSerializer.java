package com.coursemngsys.coursemanagementsystem.serializers;

import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

public class ModeratorSerializer implements JsonSerializer<Moderator> {
    @Override
    public JsonElement serialize(Moderator moderator, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject moderatorJson = new JsonObject();
        moderatorJson.addProperty("id", moderator.getId());
        moderatorJson.addProperty("login", moderator.getLogin());
        moderatorJson.addProperty("password", moderator.getPassword());
        //moderatorJson.addProperty("dateCreated", moderator.getDateCreated().toString());
        //moderatorJson.addProperty("dateModified", moderator.getDateModified().toString());
        moderatorJson.addProperty("name", moderator.getName());
        moderatorJson.addProperty("surname", moderator.getSurname());
        moderatorJson.addProperty("email", moderator.getEmail());
//        moderatorJson.addProperty("moderatedCourses", moderator.getModeratedCourses());
//        moderatorJson.addProperty("myFiles", moderator.getMyFiles());
//        moderatorJson.addProperty("editableFolders", moderator.getEditableFolders());
        return moderatorJson;
    }
}
