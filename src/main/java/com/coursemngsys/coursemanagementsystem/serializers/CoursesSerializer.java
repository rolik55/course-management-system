package com.coursemngsys.coursemanagementsystem.serializers;

import com.coursemngsys.coursemanagementsystem.Model.Course;
import com.coursemngsys.coursemanagementsystem.Model.Folder;
import com.coursemngsys.coursemanagementsystem.Model.Moderator;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.List;

public class CoursesSerializer implements JsonSerializer<List<Course>> {
    @Override
    public JsonElement serialize(List<Course> courseList, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).registerTypeAdapter(Course.class, new CoursesSerializer());
        Gson parser = gsonBuilder.create();
        for(Course c: courseList){
            jsonArray.add(parser.toJson(c));
        }
        return jsonArray;
    }
}
