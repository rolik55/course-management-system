package com.coursemngsys.coursemanagementsystem.serializers;

import com.coursemngsys.coursemanagementsystem.Model.Folder;
import com.coursemngsys.coursemanagementsystem.Model.Student;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.List;

public class StudentsSerializer implements JsonSerializer<List<Student>> {
    @Override
    public JsonElement serialize(List<Student> studentList, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Student.class, new StudentsSerializer());
        Gson parser = gsonBuilder.create();
        for(Student s: studentList){
            jsonArray.add(parser.toJson(s));
        }
        return jsonArray;
    }
}
